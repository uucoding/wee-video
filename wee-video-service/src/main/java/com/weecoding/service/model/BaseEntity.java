package com.weecoding.service.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.weecoding.common.model.Bean;
import lombok.Data;

import java.util.Date;

/**
 * 当前项目基础实体
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-04  21:52
 */
@Data
public class BaseEntity extends Bean {
    private static final long serialVersionUID = 4318026767280545749L;

    /**id：uuid类型*/
    @TableId(type = IdType.UUID)
    private String id;

    @TableField
    private Date createTime;

    @TableLogic
    private boolean delete;

}
