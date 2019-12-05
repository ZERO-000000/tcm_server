package com.user.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.base.entity.AbstractEntity;
import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author smalldeng
 * @since 2019-05-27
 */
@TableName("test_users")
@KeySequence(value = "S_TEST_USERS", clazz = Long.class)
@Data
public class Users extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    @TableField("ID")
    private String id;
    @TableField("NAME")
    private String name;
    @TableField("PHONE")
    private String phone;
    @TableField("ADDRESS")
    private String address;
    @TableField("SEX")
    private String sex;
    @TableField("PASS_WORD")
    private String passWord;
    @TableField("SALT")
    private String salt;
    @TableField("STATE")
    private String state;
    @TableField("ORG_SID")
    private Long orgSid;
    @TableField(exist = false)
    private Long roleUserSid;
}
