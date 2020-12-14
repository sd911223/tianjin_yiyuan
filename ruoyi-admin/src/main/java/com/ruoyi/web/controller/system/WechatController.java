package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.*;
import com.ruoyi.common.core.domain.entity.resp.BusinessReserveResp;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.SysReserveContentService;
import com.ruoyi.system.service.SysReserveService;
import com.ruoyi.system.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 我要预约-查询
     *
     * @param id
     * @return
     */
    @ApiOperation("我要预约-查询")
    @GetMapping("/queryReserve/{id}")
    public AjaxResult queryReserve(@PathVariable Integer id) {
        BusinessReserve businessReserve = sysReserveService.selectReserveById(id);
        BusinessReserveResp businessReserveResp = new BusinessReserveResp();
        List<BusinessReserveContent> businessReserveContents = sysReserveContentService.selectContentByRId(businessReserve.getId());
        businessReserveResp.setBusinessReserveContentList(businessReserveContents);
        businessReserveResp.setId(businessReserve.getId());
        businessReserveResp.setReserveName(businessReserve.getReserveName());
        businessReserveResp.setReserveRegister(businessReserve.getReserveRegister());
        return AjaxResult.success(businessReserveResp);

    }

    /**
     * 我要预约-保存
     */
    @ApiOperation("我要预约-保存")
    @Log(title = "微信公众号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BusinessReservePersonnel businessReservePersonnel) {
        BusinessReserve businessReserve = sysReserveService.selectReserveById(businessReservePersonnel.getReserveId());
        if (businessReserve == null) {
            return AjaxResult.error("我要预约-预约号'" + businessReservePersonnel.getReserveId() + "'失败，预约项目不存在");
        }
        return wechatService.insertPersonnel(businessReservePersonnel);
    }

}
