package com.ruoyi.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.common.core.domain.entity.resp.BusinessReserveResp;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.SysReserveContentService;
import com.ruoyi.system.service.SysReserveService;
import com.ruoyi.system.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.ruoyi.common.constant.UserConstants.MEDICINE_API;

/**
 * 微信公众号调用
 *
 * @author shitou
 */
@RestController
@RequestMapping(MEDICINE_API + "/wechat/reserve")
@Api(tags = "微信公众号调用")
public class WechatController extends BaseController {
    @Autowired
    SysReserveService sysReserveService;
    @Autowired
    WechatService wechatService;
    @Autowired
    SysReserveContentService sysReserveContentService;

    @ApiOperation("预约列表-查询已发布的")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        startPage();
        BusinessReserve businessReserve = new BusinessReserve();
        //查询状态为发布的 1:已发布
        businessReserve.setStatus("1");
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);
        return getDataTable(list);
    }

    @ApiOperation("预约查询")
    @GetMapping("/queryReserve")
    public TableDataInfo queryReserve(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        startPage();
        BusinessReserve businessReserve = new BusinessReserve();
        ArrayList<BusinessReserveResp> reserveRespArrayList = new ArrayList<>();
        //查询状态为发布的 1:已发布
        businessReserve.setStatus("1");
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);
        if (!list.isEmpty()) {
            list.forEach(e -> {
                BusinessReserveResp businessReserveResp = new BusinessReserveResp();
                List<BusinessReserveContent> businessReserveContents = sysReserveContentService.selectContentByRId(e.getId());
                businessReserveResp.setBusinessReserveContentList(businessReserveContents);
                businessReserveResp.setId(e.getId());
                businessReserveResp.setReserveName(e.getReserveName());
                businessReserveResp.setReserveRegister(e.getReserveRegister());
                reserveRespArrayList.add(businessReserveResp);
            });
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(reserveRespArrayList);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

}
