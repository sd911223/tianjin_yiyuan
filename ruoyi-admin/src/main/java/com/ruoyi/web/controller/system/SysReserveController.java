package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.SysReserveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 预约管理
 *
 * @author shitou
 */
@RestController
@RequestMapping("/system/reserve")
@Api(tags = "预约管理")
public class SysReserveController extends BaseController {
    @Autowired
    SysReserveService sysReserveService;

    @GetMapping("/list")
    public TableDataInfo list(BusinessReserve businessReserve)
    {
        startPage();
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);
        return getDataTable(list);
    }
}
