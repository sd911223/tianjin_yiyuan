package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysPromiseSpecify;
import com.ruoyi.system.domain.SysStudentPromise;
import com.ruoyi.system.service.ISysPromiseSpecifyService;
import com.ruoyi.system.service.ISysStudentPromiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.ruoyi.common.constant.UserConstants.MEDICINE_API;

/**
 * 承诺管理Controller
 *
 * @author ruoyi
 * @date 2021-03-27
 */
@Api(tags = "承诺管理")
@RestController
@RequestMapping(MEDICINE_API+"/system/promise")
public class SysStudentPromiseController extends BaseController {
    @Autowired
    private ISysStudentPromiseService sysStudentPromiseService;
    @Autowired
    private ISysPromiseSpecifyService sysPromiseSpecifyService;


    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @ApiOperation("导入特殊人群")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, Long promiseId) throws Exception {
        ExcelUtil<SysPromiseSpecify> util = new ExcelUtil<SysPromiseSpecify>(SysPromiseSpecify.class);
        List<SysPromiseSpecify> userList = util.importExcel(file.getInputStream());
        sysStudentPromiseService.importUser(userList, promiseId);
        return AjaxResult.success();
    }

    /**
     * 查询承诺管理列表
     */
    @ApiOperation("承诺管理列表")
    @GetMapping("/list")
    public TableDataInfo list(SysStudentPromise sysStudentPromise) {
        startPage();
        List<SysStudentPromise> list = sysStudentPromiseService.selectSysStudentPromiseList(sysStudentPromise);
        return getDataTable(list);
    }

    /**
     * 导出承诺管理列表
     */
    @Log(title = "承诺管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysStudentPromise sysStudentPromise) {
        List<SysStudentPromise> list = sysStudentPromiseService.selectSysStudentPromiseList(sysStudentPromise);
        ExcelUtil<SysStudentPromise> util = new ExcelUtil<SysStudentPromise>(SysStudentPromise.class);
        return util.exportExcel(list, "promise");
    }

    /**
     * 获取承诺管理详细信息
     */
    @ApiOperation("获取承诺管理详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(sysStudentPromiseService.selectSysStudentPromiseById(id));
    }

    /**
     * 新增承诺管理
     */
    @ApiOperation("新增承诺管理")
    @Log(title = "承诺管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStudentPromise sysStudentPromise) {
        return toAjax(sysStudentPromiseService.insertSysStudentPromise(sysStudentPromise));
    }

    /**
     * 修改承诺管理
     */
    @ApiOperation("修改承诺管理")
    @Log(title = "承诺管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStudentPromise sysStudentPromise) {
        return toAjax(sysStudentPromiseService.updateSysStudentPromise(sysStudentPromise));
    }

    /**
     * 删除承诺管理
     */
    @ApiOperation("删除承诺管理")
    @Log(title = "承诺管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysStudentPromiseService.deleteSysStudentPromiseByIds(ids));
    }

    /**
     * 发布承诺
     */
    @ApiOperation("发布承诺")
    @Log(title = "发布承诺", businessType = BusinessType.UPDATE)
    @DeleteMapping("/release/{ids}")
    public AjaxResult release(@PathVariable Long[] ids) {
        return toAjax(sysStudentPromiseService.releaseSysStudentPromiseByIds(ids));
    }

    /**
     * 关闭承诺
     */
    @ApiOperation("关闭承诺")
    @Log(title = "关闭承诺", businessType = BusinessType.UPDATE)
    @DeleteMapping("/turnOff/{ids}")
    public AjaxResult turnOff(@PathVariable Long[] ids) {
        return toAjax(sysStudentPromiseService.turnOffSysStudentPromiseByIds(ids));
    }

    /**
     * 撤销承诺
     */
    @ApiOperation("撤销承诺")
    @Log(title = "撤销承诺", businessType = BusinessType.UPDATE)
    @DeleteMapping("/dismantle/{ids}")
    public AjaxResult dismantle(@PathVariable Long[] ids) {
        return toAjax(sysStudentPromiseService.dismantleSysStudentPromiseByIds(ids));
    }
}
