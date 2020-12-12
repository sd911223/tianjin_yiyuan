package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.req.SiteDetailedReq;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.SysReserveContentService;
import com.ruoyi.system.service.SysReservePersonnelService;
import com.ruoyi.system.service.SysReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(MEDICINE_API + "/system/site")
@Api(tags = "现场办理")
public class SysSiteController extends BaseController {
    @Autowired
    SysReserveService sysReserveService;
    @Autowired
    SysReserveContentService sysReserveContentService;
    @Autowired
    SysReservePersonnelService sysReservePersonnelService;

    @ApiOperation("现场办理列表")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize,
                              @RequestParam("reserveName") String reserveName,
                              @RequestParam("status") String status,
                              @RequestParam("startTime") String startTime,
                              @RequestParam("endTime") String endTime) {
        startPage();
        BusinessReserve businessReserve = new BusinessReserve();
        if (!StringUtils.isEmpty(reserveName)) {
            businessReserve.setReserveName(reserveName);
        }
        if (!StringUtils.isEmpty(status)) {
            businessReserve.setStatus(status);
        }
        if (!StringUtils.isEmpty(startTime)) {
            businessReserve.setAnnouncementStartTime(DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, startTime));
        }
        if (!StringUtils.isEmpty(endTime)) {
            businessReserve.setAnnouncementEndTime(DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, endTime));
        }
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);
        return getDataTable(list);
    }

    @ApiOperation("现场办理详细")
    @GetMapping("/reserveDetailed")
    public TableDataInfo reserveDetailed(@Validated @RequestBody SiteDetailedReq siteDetailedReq) {
        startPage();
        List<BusinessReservePersonnel> list = sysReservePersonnelService.selectPersonneList(siteDetailedReq);
        return getDataTable(list);
    }


    /**
     * 查看人员详情
     */
    @ApiOperation("查看人员详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Integer id) {

        return AjaxResult.success(sysReservePersonnelService.selectPersonnelById(id));
    }

    /**
     * 状态修改
     */
    @ApiOperation("状态修改--传id和status")
    @Log(title = "现场办理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody BusinessReservePersonnel businessReservePersonnel) {

        return toAjax(sysReservePersonnelService.updatePersonnelStatus(businessReservePersonnel));
    }
}
