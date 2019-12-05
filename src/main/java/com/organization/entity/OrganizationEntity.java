package com.organization.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.base.entity.AbstractEntity;
import lombok.Data;

@TableName("test_organization")
@KeySequence(value = "S_TEST_ORGANIZATION", clazz = Long.class)
@Data
public class OrganizationEntity extends AbstractEntity {
    @TableField("ORG_ID")
    public String orgId;
    @TableField("ORG_NAME")
    public String orgName;
    @TableField("PARENT_SID")
    public Long  parentSid;
}
