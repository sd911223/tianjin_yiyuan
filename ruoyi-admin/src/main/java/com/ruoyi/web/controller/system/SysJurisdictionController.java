package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysJurisdiction;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.ISysJurisdictionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限管理
 *
 * @author shitou
 */
@RestController
@RequestMapping("/system/jurisdiction")
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

}
