package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 承诺管理对象 sys_student_promise
 * 
 * @author ruoyi
 * @date 2021-03-27
 */
@ApiModel("承诺管理对象")
public class SysStudentPromise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 考试名称 */
    @Excel(name = "考试名称")
    @ApiModelProperty("考试名称")
    private String examName;

    /** 有效开始时间 */
    @ApiModelProperty("有效开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "有效开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validStartTime;

    /** 有效结束时间 */
    @ApiModelProperty("有效结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "有效结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validEndTime;

    /** 健康码时间 */
    @ApiModelProperty("健康码时间")
    @Excel(name = "健康码时间")
    private Long healthCode;

    /** 人群 */
    @ApiModelProperty("人群")
    @Excel(name = "人群")
    private String beauType;

    /** 基本信息 */
    @ApiModelProperty("基本信息")
    @Excel(name = "基本信息")
    private String basicInfo;

    /** 承诺内容 */
    @ApiModelProperty("承诺内容")
    @Excel(name = "承诺内容")
    private String promiseContent;

    /** 内容选择 */
    @ApiModelProperty("内容选择")
    @Excel(name = "内容选择")
    private String contentSelect;

    /** 预留1 */
    @ApiModelProperty("预留1")
    @Excel(name = "预留1")
    private String estimate1;

    /** 预留2 */
    @ApiModelProperty("预留2")
    @Excel(name = "预留2")
    private String estimate2;

    /** 预留3 */
    @ApiModelProperty("预留3")
    @Excel(name = "预留3")
    private String estimate3;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 导入考生 */
    @ApiModelProperty("导入考生")
    @Excel(name = "导入考生")
    private Long importStu;

    /** 当前状态 */
    @ApiModelProperty("当前状态")
    @Excel(name = "当前状态")
    private String moduleStatus;

    /** 考生填写情况 */
    @ApiModelProperty("考生填写情况")
    @Excel(name = "考生填写情况")
    private Long writeNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setExamName(String examName) 
    {
        this.examName = examName;
    }

    public String getExamName() 
    {
        return examName;
    }
    public void setValidStartTime(Date validStartTime) 
    {
        this.validStartTime = validStartTime;
    }

    public Date getValidStartTime() 
    {
        return validStartTime;
    }
    public void setValidEndTime(Date validEndTime) 
    {
        this.validEndTime = validEndTime;
    }

    public Date getValidEndTime() 
    {
        return validEndTime;
    }
    public void setHealthCode(Long healthCode) 
    {
        this.healthCode = healthCode;
    }

    public Long getHealthCode() 
    {
        return healthCode;
    }
    public void setBeauType(String beauType) 
    {
        this.beauType = beauType;
    }

    public String getBeauType() 
    {
        return beauType;
    }
    public void setBasicInfo(String basicInfo) 
    {
        this.basicInfo = basicInfo;
    }

    public String getBasicInfo() 
    {
        return basicInfo;
    }
    public void setPromiseContent(String promiseContent) 
    {
        this.promiseContent = promiseContent;
    }

    public String getPromiseContent() 
    {
        return promiseContent;
    }
    public void setContentSelect(String contentSelect) 
    {
        this.contentSelect = contentSelect;
    }

    public String getContentSelect() 
    {
        return contentSelect;
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
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setImportStu(Long importStu) 
    {
        this.importStu = importStu;
    }

    public Long getImportStu() 
    {
        return importStu;
    }
    public void setModuleStatus(String moduleStatus) 
    {
        this.moduleStatus = moduleStatus;
    }

    public String getModuleStatus() 
    {
        return moduleStatus;
    }
    public void setWriteNumber(Long writeNumber) 
    {
        this.writeNumber = writeNumber;
    }

    public Long getWriteNumber() 
    {
        return writeNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("examName", getExamName())
            .append("validStartTime", getValidStartTime())
            .append("validEndTime", getValidEndTime())
            .append("healthCode", getHealthCode())
            .append("beauType", getBeauType())
            .append("basicInfo", getBasicInfo())
            .append("promiseContent", getPromiseContent())
            .append("contentSelect", getContentSelect())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("estimate1", getEstimate1())
            .append("estimate2", getEstimate2())
            .append("estimate3", getEstimate3())
            .append("delFlag", getDelFlag())
            .append("importStu", getImportStu())
            .append("moduleStatus", getModuleStatus())
            .append("writeNumber", getWriteNumber())
            .toString();
    }
}
