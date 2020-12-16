package com.ruoyi.common.core.domain.entity.resp;

import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class BusinessReserveResp {
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;
    /**
     * 预约名称
     */
    @ApiModelProperty("预约名称")
    private String reserveName;
    /**
     * 预约登记信息
     */
    @ApiModelProperty("预约登记信息")
    private String reserveRegister;
    private Integer dictionaryId;
    private String submitName;
    /**
     * 预约内容
     */
    @ApiModelProperty("预约内容")
    private List<BusinessReserveContent> businessReserveContentList;

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }


    public String getSubmitName() {
        return submitName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public void setReserveRegister(String reserveRegister) {
        this.reserveRegister = reserveRegister;
    }

    public void setBusinessReserveContentList(List<BusinessReserveContent> businessReserveContentList) {
        this.businessReserveContentList = businessReserveContentList;
    }

    public Integer getId() {
        return id;
    }

    public String getReserveName() {
        return reserveName;
    }

    public String getReserveRegister() {
        return reserveRegister;
    }

    public List<BusinessReserveContent> getBusinessReserveContentList() {
        return businessReserveContentList;
    }
}
