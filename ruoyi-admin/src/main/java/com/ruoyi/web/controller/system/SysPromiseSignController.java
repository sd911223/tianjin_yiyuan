package com.ruoyi.web.controller.system;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysPromiseSign;
import com.ruoyi.system.service.ISysPromiseSignService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 承诺填写Controller
 * 
 * @author ruoyi
 * @date 2021-03-28
 */
@RestController
@RequestMapping("/system/sign")
public class SysPromiseSignController extends BaseController
{
    @Autowired
    private ISysPromiseSignService sysPromiseSignService;

    /**
     * 查询承诺填写列表
     */
    @PreAuthorize("@ss.hasPermi('system:sign:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPromiseSign sysPromiseSign)
    {
        startPage();
        List<SysPromiseSign> list = sysPromiseSignService.selectSysPromiseSignList(sysPromiseSign);
        return getDataTable(list);
    }

    /**
     * 导出承诺填写列表
     */
    @PreAuthorize("@ss.hasPermi('system:sign:export')")
    @Log(title = "承诺填写", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysPromiseSign sysPromiseSign)
    {
        List<SysPromiseSign> list = sysPromiseSignService.selectSysPromiseSignList(sysPromiseSign);
        ExcelUtil<SysPromiseSign> util = new ExcelUtil<SysPromiseSign>(SysPromiseSign.class);
        return util.exportExcel(list, "sign");
    }

    /**
     * 获取承诺填写详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sign:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysPromiseSignService.selectSysPromiseSignById(id));
    }

    /**
     * 新增承诺填写
     */
    @PreAuthorize("@ss.hasPermi('system:sign:add')")
    @Log(title = "承诺填写", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPromiseSign sysPromiseSign)
    {
        return toAjax(sysPromiseSignService.insertSysPromiseSign(sysPromiseSign));
    }

    /**
     * 修改承诺填写
     */
    @PreAuthorize("@ss.hasPermi('system:sign:edit')")
    @Log(title = "承诺填写", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPromiseSign sysPromiseSign)
    {
        return toAjax(sysPromiseSignService.updateSysPromiseSign(sysPromiseSign));
    }

    /**
     * 删除承诺填写
     */
    @PreAuthorize("@ss.hasPermi('system:sign:remove')")
    @Log(title = "承诺填写", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysPromiseSignService.deleteSysPromiseSignByIds(ids));
    }
}
