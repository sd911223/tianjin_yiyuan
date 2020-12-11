package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.SysReserveContentService;
import com.ruoyi.system.service.SysReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("预约管理列表")
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

}
