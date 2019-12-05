package com.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
@Data
public abstract class AbstractEntity{
    @TableId(value = "SID", type = IdType.INPUT)
    public Long sid;
    @TableField("CREATED_BY")
    public String createdBy;
    @TableField("CREATED_DT")
    public Date createdDt;
    @TableField("UPDATED_BY")
    public String updatedBy;
    @TableField("UPDATED_DT")
    public Date updatedDt;
    @Version
    @TableField(value="VERSION", fill = FieldFill.INSERT_UPDATE, update="%s+1")
    public Integer version=0;
}
