package com.ruoyi.web.controller.system;

import com.ruoyi.system.service.SysReserveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预约管理
 *
 * @author shitou
 */
@RestController
@RequestMapping("/system/reserve")
@Api(tags = "预约管理")
public class SysReserveController {
    @Autowired
    SysReserveService sysReserveService;
}
