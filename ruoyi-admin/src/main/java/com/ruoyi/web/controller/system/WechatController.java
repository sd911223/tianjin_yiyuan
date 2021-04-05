package com.ruoyi.web.controller.system;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.req.ReserveCancelReq;
import com.ruoyi.common.core.domain.entity.req.TicketReq;
import com.ruoyi.common.core.domain.entity.resp.BusinessReserveResp;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.PromiseStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.SysPromiseSign;
import com.ruoyi.system.domain.SysPromiseSpecify;
import com.ruoyi.system.domain.SysStudentPromise;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    @Autowired
    SysReserveService sysReserveService;
    @Autowired
    WechatService wechatService;
    @Autowired
    SysReserveContentService sysReserveContentService;
    @Autowired
    ISysStudentPromiseService sysStudentPromiseService;
    @Autowired
    ISysPromiseSignService sysPromiseSignService;
    @Autowired
    ISysPromiseSpecifyService sysPromiseSpecifyService;

    /**
     * 通过身份号查询
     */
    @ApiOperation("微信-通过身份号查询")
    @GetMapping("/byIdCard")
    public AjaxResult byIdCard(@RequestParam String idCard,
                               @RequestParam String name,
                               @RequestParam Long promiseId
    ) {
        SysPromiseSpecify sysPromiseSpecify = new SysPromiseSpecify();
        sysPromiseSpecify.setIdCard(idCard);
        sysPromiseSpecify.setPromiseId(promiseId);
        sysPromiseSpecify.setName(name);
        List<SysPromiseSpecify> specifyList = sysPromiseSpecifyService.selectSysPromiseSpecifyList(sysPromiseSpecify);
        return AjaxResult.success(specifyList);
    }

    /**
     * 查询承诺详情
     */
    @ApiOperation("微信-查询承诺详情")
    @PostMapping("/queryPromise")
    public AjaxResult queryPromise(@RequestBody SysPromiseSign sysPromiseSign) {
        List<SysPromiseSign> sysPromiseSigns = sysPromiseSignService.selectSysPromiseSignList(sysPromiseSign);
        if (!sysPromiseSigns.isEmpty()) {
            sysPromiseSigns.forEach(e -> {
                SysStudentPromise sysStudentPromise = sysStudentPromiseService.selectSysStudentPromiseById(e.getPromiseId());
                long between = DateUtil.between(DateUtils.getNowDate(), e.getCreateTime(), DateUnit.DAY);
                if (between >= sysStudentPromise.getHealthCode()) {
                    log.info("二维码过期--------------------openID:{}", e.getOpenId());
                    e.setEstimate1("1");
                }
            });

        }
        return AjaxResult.success(sysPromiseSigns);
    }

    /**
     * 新增承诺填写
     */
    @ApiOperation("微信-新增承诺信息")
    @Log(title = "承诺填写", businessType = BusinessType.INSERT)
    @PostMapping("/promise/add")
    @RepeatSubmit
    public AjaxResult promiseAdd(@RequestBody SysPromiseSign sysPromiseSign) {
        return toAjax(sysPromiseSignService.insertSysPromiseSign(sysPromiseSign));
    }

    /**
     * 承诺列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("微信-承诺列表")
    @GetMapping("/promise/list")
    public TableDataInfo promiseList(@RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("pageSize") Integer pageSize) {
        startPage();
        SysStudentPromise sysStudentPromise = new SysStudentPromise();
        sysStudentPromise.setModuleStatus(PromiseStatus.ISSUE.getCode());
        List<SysStudentPromise> promiseList = sysStudentPromiseService.selectSysStudentPromiseList(sysStudentPromise);
        //遍历删除,除去不在时间范围内的
        Iterator<SysStudentPromise> iterator = promiseList.iterator();
        while (iterator.hasNext()) {
            SysStudentPromise studentPromise = iterator.next();
            if (DateUtils.getNowDate().getTime() - studentPromise.getValidStartTime().getTime() < 1) {
                //使用迭代器的删除方法删除
                iterator.remove();
            }
            if (DateUtils.getNowDate().getTime() - studentPromise.getValidEndTime().getTime() > 1) {
                //使用迭代器的删除方法删除
                iterator.remove();
            }
        }
        return getDataTable(promiseList);
    }


    @ApiOperation("预约列表-查询已发布的")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        startPage();
        BusinessReserve businessReserve = new BusinessReserve();
        //查询状态为進行中的 1
        businessReserve.setStatus("1");
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);

        BusinessReserve reserve = new BusinessReserve();
        //查询状态为已发布
        reserve.setStatus("2");
        List<BusinessReserve> reList = sysReserveService.selectReserveList(reserve);
        if (!reList.isEmpty()) {
            reList.forEach(e -> {
                list.add(e);
            });
        }
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
        businessReserveContents.forEach(e -> {
            Date appointmentDate = e.getAppointmentDate();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate);
            String[] split = e.getAppointmentPeriod().split("-");
            String startTime = date + " " + split[0];
            String endTime = date + " " + split[1];
            Date parseDateStart = DateUtils.parseDate(startTime);
            Date parseDateEnd = DateUtils.parseDate(endTime);
            if ((new Date().getTime() - parseDateEnd.getTime() > 0) || (e.getSurplusNumber() < 1)) {
                e.setStatus("1");
            } else {
                e.setStatus("0");
            }
        });
        BeanUtils.copyProperties(businessReserve, businessReserveResp);
        if (!businessReserveContents.isEmpty()) {
            businessReserveResp.setBusinessReserveContentList(businessReserveContents);
        }
        return AjaxResult.success(businessReserveResp);

    }

    /**
     * 我要预约-保存
     */
    @ApiOperation("我要预约-保存")
    @Log(title = "微信公众号", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@Validated @RequestBody BusinessReservePersonnel businessReservePersonnel) {

        return wechatService.insertPersonnel(businessReservePersonnel);
    }

    /**
     * 我的预约
     */
    @ApiOperation("我的预约")
    @GetMapping("/myReserve")
    public TableDataInfo myReserve(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam("openId") String openId) {
        startPage();
        List<BusinessReservePersonnel> list = wechatService.myReserve(openId);
        return getDataTable(list);
    }

    /**
     * 我要预约-取消
     */
    @ApiOperation("我要预约-取消")
    @Log(title = "微信公众号", businessType = BusinessType.INSERT)
    @PostMapping("/cancel")
    @RepeatSubmit
    public AjaxResult cancel(@Validated @RequestBody ReserveCancelReq reserveCancelReq) {

        return wechatService.reserveCancel(reserveCancelReq);
    }

    @ApiOperation("获取基础设置access_token")
    @GetMapping("/base/accessToken")
    public AjaxResult getAccessToken() {

        return wechatService.getAccessToken();
    }

    @ApiOperation("获取微信code")
    @GetMapping("/code/{url}")
    public AjaxResult getCode(@PathVariable String url) {

        return wechatService.getCode(url);
    }

    @ApiOperation("code换取access_token")
    @GetMapping("/accessToken/{code}")
    public AjaxResult accessToken(@PathVariable String code) {

        return wechatService.accessToken(code);
    }

    @ApiOperation("签到")
    @PostMapping("/sign")
    public AjaxResult sign(@Validated @RequestBody ReserveCancelReq reserveCancelReq) {

        return wechatService.sign(reserveCancelReq);
    }

    @ApiOperation("获取ticket")
    @PostMapping("/scan")
    public AjaxResult scan(@Validated @RequestBody TicketReq ticketReq) {

        return wechatService.scan(ticketReq.getUrl());
    }

}
