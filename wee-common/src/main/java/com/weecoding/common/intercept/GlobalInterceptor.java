package com.weecoding.common.intercept;

import com.weecoding.common.constant.RedisConstants;
import com.weecoding.common.exception.GlobalException;
import com.weecoding.common.properties.GlobalProperties;
import com.weecoding.common.util.V;
import com.weecoding.common.util.response.enumerate.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 全局拦截器
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  17:01
 */
@Slf4j
public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private GlobalProperties globalProperties;

    /**
     * 拦截所有非匿名访问的url
     *
     * <ul>
     *     <li>1、判断请求头是否包含Token || redis中是否存在缓存数据 任意之一不满足，那么直接抛出{@link ErrorEnum#LOGIN_VISIT}异常</li>
     *     <li>2、检查redis中缓存是否过期，默认过期时间{@link RedisConstants#EXPIRED_TIME}秒，剩余时间小于{@link RedisConstants#REMAIN_TIME}重新设置过期时间</li>
     * </ul>
     * ,
     * 判断
     * 如果上述两个条件，
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //没全局认证，不做拦截处理
        if (!globalProperties.isEnableTokenFilter()) {
            return true;
        }
        log.debug("【请求】<== url: {}，", request.getRequestURI());
        //1、校验是否认证
        String token = request.getHeader(RedisConstants.TOKEN);
        if (V.isEmpty(token)
            || V.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstants.TOKEN_KEY_TEMPLATE, token)))
        ){
            throw new GlobalException(ErrorEnum.LOGIN_VISIT);
        }
        //2、校验剩余时间 ： 少于默认剩余时间，重新设置时间
        String redisKey = String.format(RedisConstants.TOKEN_KEY_TEMPLATE, token);
        //获取剩余时间
        Long remainTime = stringRedisTemplate.getExpire(redisKey);
        if (remainTime <= RedisConstants.REMAIN_TIME) {
            stringRedisTemplate.expire(redisKey, RedisConstants.EXPIRED_TIME, TimeUnit.SECONDS);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
