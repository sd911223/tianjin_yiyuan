package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysBase;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.SysBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ruoyi.common.constant.UserConstants.MEDICINE_API;

@Api(tags = "基础设置")
@RestController
@RequestMapping(MEDICINE_API + "/system/base")
public class SysBaseController extends BaseController {
    @Autowired
    SysBaseService sysBaseService;

    /**
     * 设置用户密码
     */
    @ApiOperation("设置用户密码")
    @Log(title = "基础设置", businessType = BusinessType.UPDATE)
    @PostMapping("/addPassWord")
    public AjaxResult addPassWord(@Validated @RequestBody SysBase sysBase) {
        if (StringUtils.isEmpty(sysBase.getPassWord())) {
            return AjaxResult.error("初始密码不能为空");
        }
        return toAjax(sysBaseService.updateBase(sysBase));
    }

    /**
     * 单位预约设置
     */
    @ApiOperation("单位预约设置")
    @Log(title = "基础设置", businessType = BusinessType.UPDATE)
    @PostMapping("/addReserveInstall")
    public AjaxResult addReserveInstall(@Validated @RequestBody SysBase sysBase) {
        if (StringUtils.isEmpty(sysBase.getReserveInstall())) {
            return AjaxResult.error("预约设置不能为空");
        }
        return toAjax(sysBaseService.updateBase(sysBase));
    }

    /**
     * 获取基本设置
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id获取设置详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(sysBaseService.selectBaseById(1L));
    }
}
