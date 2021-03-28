package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 特定人群对象 sys_promise_specify
 * 
 * @author ruoyi
 * @date 2021-03-27
 */
public class SysPromiseSpecify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 承诺ID */
    private Long promiseId;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 预留1 */
    private String estimate1;

    /** 预留2 */
    private String estimate2;

    /** 预留3 */
    private String estimate3;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPromiseId(Long promiseId) 
    {
        this.promiseId = promiseId;
    }

    public Long getPromiseId() 
    {
        return promiseId;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setEstimate1(String estimate1) 
    {
        this.estimate1 = estimate1;
    }

    public String getEstimate1() 
    {
        return estimate1;
    }
    public void setEstimate2(String estimate2) 
    {
        this.estimate2 = estimate2;
    }

    public String getEstimate2() 
    {
        return estimate2;
    }
    public void setEstimate3(String estimate3) 
    {
        this.estimate3 = estimate3;
    }

    public String getEstimate3() 
    {
        return estimate3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("promiseId", getPromiseId())
            .append("idCard", getIdCard())
            .append("name", getName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("estimate1", getEstimate1())
            .append("estimate2", getEstimate2())
            .append("estimate3", getEstimate3())
            .toString();
    }
}
