package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.SysReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.constant.UserConstants.MEDICINE_API;

/**
 * 预约管理
 *
 * @author shitou
 */
@RestController
@RequestMapping(MEDICINE_API+"/system/reserve")
@Api(tags = "预约管理")
public class SysReserveController extends BaseController {
    @Autowired
    SysReserveService sysReserveService;

    @ApiOperation("预约管理列表")
    @GetMapping("/list")
    public TableDataInfo<BusinessReserve> list(BusinessReserve businessReserve) {
        startPage();
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);
        return getDataTable(list);
    }

    /**
     * 新增预约
     */
    @ApiOperation("新增预约")
    @Log(title = "预约管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BusinessReserve businessReserve) {


        return toAjax(sysReserveService.insertReserve(businessReserve));
    }

    /**
     * 发布活动
     */
    @ApiOperation("发布活动")
    @GetMapping(value = "/{id}")
    public AjaxResult add(@PathVariable Long id) {

        BusinessReserve businessReserve = new BusinessReserve();
        businessReserve.setId(id.intValue());
        businessReserve.setStatus("1");
        return toAjax(sysReserveService.updateReserveStatus(businessReserve));
    }
    /**
     * 删除活动
     */
    @ApiOperation("删除活动")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {

        return toAjax(sysReserveService.deleteReserveById(id));
    }
}
