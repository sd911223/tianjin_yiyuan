package com.ruoyi.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;
import java.util.TreeMap;

/**
 * 承诺填写对象 sys_promise_sign
 *
 * @author ruoyi
 * @date 2021-03-28
 */
@ApiModel("承诺填写对象")
public class SysPromiseSign extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 承诺ID
     */
    @ApiModelProperty("承诺ID")
    @Excel(name = "承诺ID")
    private Long promiseId;

    /**
     * openId
     */
    @ApiModelProperty("openId")
    @Excel(name = "openId")
    private String openId;

    /**
     * 基础信息
     */
    @ApiModelProperty("基础信息")
    @Excel(name = "基础信息")
    private String basicInfo;

    /**
     * 承诺内容
     */
    @ApiModelProperty("承诺内容")
    @Excel(name = "承诺内容")
    private String promiseContent;

    /**
     * 选填信息
     */
    @ApiModelProperty("选填信息")
    @Excel(name = "选填信息")
    private String contentSelect;

    /**
     * 码颜色
     */
    @ApiModelProperty("码颜色")
    @Excel(name = "码颜色")
    private String codeColor;
    /**
     * 逻辑删除
     */
    private String delFlag;
    /**
     * 预留字段1
     */
    @ApiModelProperty("预留字段1")
    @Excel(name = "预留字段1")
    private String estimate1;

    /**
     * 预留字段2
     */
    @ApiModelProperty("预留字段2")
    @Excel(name = "预留字段2")
    private String estimate2;

    /**
     * 预留字段3
     */
    @ApiModelProperty("预留字段3")
    @Excel(name = "预留字段3")
    private String estimate3;

    @ApiModelProperty("基础信息-排列后的")
    private TreeMap treeMap;

    public TreeMap getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(TreeMap treeMap) {
        this.treeMap = treeMap;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPromiseId(Long promiseId) {
        this.promiseId = promiseId;
    }

    public Long getPromiseId() {
        return promiseId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setPromiseContent(String promiseContent) {
        this.promiseContent = promiseContent;
    }

    public String getPromiseContent() {
        return promiseContent;
    }

    public void setContentSelect(String contentSelect) {
        this.contentSelect = contentSelect;
    }

    public String getContentSelect() {
        return contentSelect;
    }

    public void setCodeColor(String codeColor) {
        this.codeColor = codeColor;
    }

    public String getCodeColor() {
        return codeColor;
    }

    public void setEstimate1(String estimate1) {
        this.estimate1 = estimate1;
    }

    public String getEstimate1() {
        return estimate1;
    }

    public void setEstimate2(String estimate2) {
        this.estimate2 = estimate2;
    }

    public String getEstimate2() {
        return estimate2;
    }

    public void setEstimate3(String estimate3) {
        this.estimate3 = estimate3;
    }

    public String getEstimate3() {
        return estimate3;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("promiseId", getPromiseId())
                .append("openId", getOpenId())
                .append("basicInfo", getBasicInfo())
                .append("promiseContent", getPromiseContent())
                .append("contentSelect", getContentSelect())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("codeColor", getCodeColor())
                .append("estimate1", getEstimate1())
                .append("estimate2", getEstimate2())
                .append("estimate3", getEstimate3())
                .toString();
    }
}
