package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.req.BusinessReserveReq;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.SysReserveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.ruoyi.common.constant.UserConstants.MEDICINE_API;

/**
 * 预约管理
 *
 * @author shitou
 */
@RestController
@RequestMapping(MEDICINE_API + "/system/reserve")
@Api(tags = "预约管理")
public class SysReserveController extends BaseController {
    @Autowired
    SysReserveService sysReserveService;

    @ApiOperation("预约管理列表")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize,
                              @RequestParam("reserveName") String reserveName,
                              @RequestParam("status") String status,
                              @RequestParam("startTime") Date startTime,
                              @RequestParam("endTime") Date endTime) {
        startPage();
        BusinessReserve businessReserve = new BusinessReserve();
        if (!StringUtils.isEmpty(reserveName)) {
            businessReserve.setReserveName(reserveName);
        }
        if (!StringUtils.isEmpty(status)) {
            businessReserve.setStatus(status);
        }
        List<BusinessReserve> list = sysReserveService.selectReserveList(businessReserve);
        return getDataTable(list);
    }

    /**
     * 新增预约
     */
    @ApiOperation("新增预约")
    @Log(title = "预约管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BusinessReserveReq businessReserveReq) {
        BusinessReserve businessReserve = new BusinessReserve();
        BeanUtils.copyProperties(businessReserveReq, businessReserve);
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
    public AjaxResult remove(@PathVariable Integer id) {
        BusinessReserve businessReserve = sysReserveService.selectReserveById(id);
        if (!"0".equals(businessReserve.getStatus())) {
            return AjaxResult.error("删除活动'" + id + "'失败，只有未发布活动可删除");
        }
        return toAjax(sysReserveService.deleteReserveById(id));
    }


    /**
     * 根据id获取预约信息
     */
    @ApiOperation("根据id获取预约信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Integer id) {
        return AjaxResult.success(sysReserveService.selectReserveById(id));
    }

    /**
     * 查看链接
     */
    @ApiOperation("查看链接")
    @GetMapping(value = "/{id}")
    public AjaxResult getUrl(@PathVariable Integer id) {
        return AjaxResult.success("www.baidu.com");
    }

    /**
     * 签到码
     */
    @ApiOperation("签到码")
    @GetMapping(value = "/{id}")
    public AjaxResult getSignUrl(@PathVariable Integer id) {
        return AjaxResult.success("www.baidu.com");
    }
}
