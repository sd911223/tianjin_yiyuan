package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysJurisdiction;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ISysJurisdictionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.constant.UserConstants.MEDICINE_API;

/**
 * 权限管理
 *
 * @author shitou
 */
@RestController
@RequestMapping(MEDICINE_API + "/system/jurisdiction")
@Api(tags = "权限管理")
public class SysJurisdictionController extends BaseController {

    @Autowired
    private ISysJurisdictionService iSysJurisdictionService;


    @ApiOperation("角色权限列表")
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<SysJurisdiction> list = iSysJurisdictionService.selectJurisdictionList();
        return getDataTable(list);
    }

    @ApiOperation("新增角色权限")
    @Log(title = "权限管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysJurisdiction sysJurisdiction) {
        if (UserConstants.NOT_UNIQUE.equals(iSysJurisdictionService.checkJurNameUnique(sysJurisdiction))) {
            return AjaxResult.error("新增角色权限'" + sysJurisdiction.getJurisdictionName() + "'失败，角色权限名称已存在");
        }
        return toAjax(iSysJurisdictionService.insertJurisdiction(sysJurisdiction));
    }
}
