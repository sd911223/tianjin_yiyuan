package com.ruoyi.common.core.domain.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("预约登记信息")
public class ReserveRegisterReq {
    @ApiModelProperty("是否必填")
    private String isRequired;
    @ApiModelProperty("信息名称")
    private String informationName;
    @ApiModelProperty("文本类型")
    private String inputType;
    private String contentCcope;
    @ApiModelProperty("是否可编辑")
    private String canEdit;
    private String dictionaryId;
    private String submitName;

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public String getDictionaryId() {
        return dictionaryId;
    }

    public String getSubmitName() {
        return submitName;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public String getInformationName() {
        return informationName;
    }

    public String getInputType() {
        return inputType;
    }

    public String getContentCcope() {
        return contentCcope;
    }

    public String getCanEdit() {
        return canEdit;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public void setContentCcope(String contentCcope) {
        this.contentCcope = contentCcope;
    }

    public void setCanEdit(String canEdit) {
        this.canEdit = canEdit;
    }
}
