package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.resp.SysSignInfo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.utils.JsonLoop;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysPromiseSign;
import com.ruoyi.system.service.ISysPromiseSignService;
import com.ruoyi.web.controller.common.CommonController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static com.ruoyi.common.constant.UserConstants.MEDICINE_API;

/**
 * 承诺填写Controller
 *
 * @author ruoyi
 * @date 2021-03-28
 */
@RestController
@RequestMapping(MEDICINE_API + "/system/sign")
@Api(tags = "承诺详细管理")
public class SysPromiseSignController extends BaseController {
    @Autowired
    private ISysPromiseSignService sysPromiseSignService;

    /**
     * 查询承诺填写列表
     */
    @ApiOperation("承诺详细列表")
    @GetMapping("/list")
    public TableDataInfo list(SysPromiseSign sysPromiseSign) {
        startPage();
        List<SysPromiseSign> list = sysPromiseSignService.selectSysPromiseSignList(sysPromiseSign);
        if (!list.isEmpty()) {
            list.forEach(e -> {
                TreeMap treeMap = JsonLoop.jsonLoop(e.getBasicInfo());
                e.setTreeMap(treeMap);
            });
        }
        return getDataTable(list);
    }

    /**
     * 导出承诺填写列表
     */
    @ApiOperation("导出承诺详细")
    @Log(title = "承诺填写", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response, @RequestParam("promiseId") Long promiseId) throws NoSuchFieldException, IllegalAccessException {
        SysPromiseSign sysPromiseSign = new SysPromiseSign();
        sysPromiseSign.setPromiseId(promiseId);
        ArrayList<SysSignInfo> sysSignInfos = new ArrayList<>();
        List<SysPromiseSign> list = sysPromiseSignService.selectSysPromiseSignList(sysPromiseSign);
        if (!list.isEmpty()) {
            list.forEach(e -> {
                try {
                    SysSignInfo sysSignInfo = JsonLoop.getSysSignInfo(e.getBasicInfo());
                    sysSignInfos.add(sysSignInfo);
                } catch (NoSuchFieldException noSuchFieldException) {
                    noSuchFieldException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            });
        } else {
            throw new BaseException("没有可导出数据");
        }
        ExcelUtil<SysSignInfo> util = new ExcelUtil<SysSignInfo>(SysSignInfo.class);
        AjaxResult ajaxResult = util.exportExcel(sysSignInfos, "承诺用户");
        if (ajaxResult.get("code").equals(200)) {
            CommonController commonController = new CommonController();
            commonController.fileDownload(ajaxResult.get("msg").toString(), true, response, request);
        }
    }

    /**
     * 获取承诺填写详细信息
     */
    @ApiOperation("获取承诺填写详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(sysPromiseSignService.selectSysPromiseSignById(id));
    }

    /**
     * 新增承诺填写
     */
    @Log(title = "承诺填写", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPromiseSign sysPromiseSign) {
        return toAjax(sysPromiseSignService.insertSysPromiseSign(sysPromiseSign));
    }

    /**
     * 修改承诺填写
     */
    @Log(title = "承诺填写", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPromiseSign sysPromiseSign) {
        return toAjax(sysPromiseSignService.updateSysPromiseSign(sysPromiseSign));
    }

    /**
     * 删除承诺填写
     */
    @Log(title = "承诺填写", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysPromiseSignService.deleteSysPromiseSignByIds(ids));
    }
}
