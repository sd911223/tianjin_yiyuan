package com.ruoyi.common.core.domain.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel("字典类型表")
public class SysDictTypeReq {
    /**
     * 字典主键
     */
    @ApiModelProperty("字典主键")
    private Long dictId;
    /**
     * 字典名称
     */
    @ApiModelProperty("字典名称")
    @NotBlank(message = "字典名称不能为空")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty("字典类型")
    @NotBlank(message = "字典类型不能为空")
    private String dictType;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public String getDictType() {
        return dictType;
    }

    public String getRemark() {
        return remark;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
