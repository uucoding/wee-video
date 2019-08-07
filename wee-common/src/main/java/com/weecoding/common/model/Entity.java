package com.weecoding.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 空实体，主要为了反射
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-04  21:53
 */
@Data
public class Entity implements Serializable {
    private static final long serialVersionUID = 6241923921685061999L;

    /**
     * 实例化后每个对象的唯一标记：如果对象存在id，那么值为对象的id，否则为空
     */
    @TableField(exist = false)
    private Object key;
}
