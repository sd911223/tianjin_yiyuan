package com.ruoyi.common.core.domain.entity.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@ApiModel("预约实体")
public class BusinessReserveReq {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 预约名称
     */
    @ApiModelProperty("预约名称")
    @NotBlank(message = "预约名称不能为空")
    private String reserveName;
    /**
     * 公告开始时间
     */
    @ApiModelProperty("公告开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date announcementStartTime;
    /**
     * 公告结束时间
     */
    @ApiModelProperty("公告结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date announcementEndTime;
    /**
     * 预约开始时间
     */
    @ApiModelProperty("预约开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reserveStartTime;
    /**
     * 预约结束时间
     */
    @ApiModelProperty("预约结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reserveEndTime;
    /**
     * 撤回时间
     */
    @ApiModelProperty("撤回时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date withdrawTime;
    /**
     * 预约类型 0:所有人;1:特定人群
     */
    @ApiModelProperty("预约类型 0:所有人;1:特定人群")
    private String reserveType;
    /**
     * 预约内容
     */
    @ApiModelProperty("预约内容")
    @NotBlank(message = "预约内容不能为空")
    private String reserveContent;
    /**
     * 预约公共内容
     */
    @ApiModelProperty("预约公共内容")
    private List<ReserveAmContentReq> reserveAmContentList;
    /**
     * 预约登记信息
     */
    @ApiModelProperty("预约登记信息")
    private List<ReserveRegisterReq> reserveRegisterList;
    /**
     * 附件地址
     */
    @ApiModelProperty("附件地址")
    private String annexUrl;
    @ApiModelProperty("字典Id")
    private Integer dictionaryId;
    @ApiModelProperty("提交人")
    private String submitName;
    /**
     * 特殊人群身份证
     */
    @ApiModelProperty("特殊人群身份证")
    private List<String> idCardList;

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public String getSubmitName() {
        return submitName;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public void setIdCardList(List<String> idCardList) {
        this.idCardList = idCardList;
    }

    public List<String> getIdCardList() {
        return idCardList;
    }

    public void setReserveAmContentList(List<ReserveAmContentReq> reserveAmContentList) {
        this.reserveAmContentList = reserveAmContentList;
    }

    public void setReserveRegisterList(List<ReserveRegisterReq> reserveRegisterList) {
        this.reserveRegisterList = reserveRegisterList;
    }

    public List<ReserveAmContentReq> getReserveAmContentList() {
        return reserveAmContentList;
    }

    public List<ReserveRegisterReq> getReserveRegisterList() {
        return reserveRegisterList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public void setAnnouncementStartTime(Date announcementStartTime) {
        this.announcementStartTime = announcementStartTime;
    }

    public void setAnnouncementEndTime(Date announcementEndTime) {
        this.announcementEndTime = announcementEndTime;
    }

    public void setReserveStartTime(Date reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    public void setReserveEndTime(Date reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }

    public void setWithdrawTime(Date withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public void setReserveType(String reserveType) {
        this.reserveType = reserveType;
    }

    public void setReserveContent(String reserveContent) {
        this.reserveContent = reserveContent;
    }


    public void setAnnexUrl(String annexUrl) {
        this.annexUrl = annexUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getReserveName() {
        return reserveName;
    }

    public Date getAnnouncementStartTime() {
        return announcementStartTime;
    }

    public Date getAnnouncementEndTime() {
        return announcementEndTime;
    }

    public Date getReserveStartTime() {
        return reserveStartTime;
    }

    public Date getReserveEndTime() {
        return reserveEndTime;
    }

    public Date getWithdrawTime() {
        return withdrawTime;
    }

    public String getReserveType() {
        return reserveType;
    }

    public String getReserveContent() {
        return reserveContent;
    }

    public String getAnnexUrl() {
        return annexUrl;
    }

}
