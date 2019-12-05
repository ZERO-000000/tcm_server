package com.role.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.base.entity.AbstractEntity;
import lombok.Data;

@TableName("test_roles")
@KeySequence(value = "S_TEST_ROLES", clazz = Long.class)
@Data
public class RoleEntity extends AbstractEntity {
    @TableField("ROLE_ID")
    public String roleId;
    @TableField("ROLE_NAME")
    public String roleName;
}
