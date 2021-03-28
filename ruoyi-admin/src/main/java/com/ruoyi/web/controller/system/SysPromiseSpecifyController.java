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
import com.ruoyi.system.domain.SysPromiseSpecify;
import com.ruoyi.system.service.ISysPromiseSpecifyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import static com.ruoyi.common.constant.UserConstants.MEDICINE_API;

/**
 * 特定人群Controller
 * 
 * @author ruoyi
 * @date 2021-03-27
 */
@RestController
@RequestMapping(MEDICINE_API+"/system/specify")
public class SysPromiseSpecifyController extends BaseController
{
    @Autowired
    private ISysPromiseSpecifyService sysPromiseSpecifyService;

    /**
     * 查询特定人群列表
     */
    @PreAuthorize("@ss.hasPermi('system:specify:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPromiseSpecify sysPromiseSpecify)
    {
        startPage();
        List<SysPromiseSpecify> list = sysPromiseSpecifyService.selectSysPromiseSpecifyList(sysPromiseSpecify);
        return getDataTable(list);
    }

    /**
     * 导出特定人群列表
     */
    @PreAuthorize("@ss.hasPermi('system:specify:export')")
    @Log(title = "特定人群", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysPromiseSpecify sysPromiseSpecify)
    {
        List<SysPromiseSpecify> list = sysPromiseSpecifyService.selectSysPromiseSpecifyList(sysPromiseSpecify);
        ExcelUtil<SysPromiseSpecify> util = new ExcelUtil<SysPromiseSpecify>(SysPromiseSpecify.class);
        return util.exportExcel(list, "specify");
    }

    /**
     * 获取特定人群详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:specify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysPromiseSpecifyService.selectSysPromiseSpecifyById(id));
    }

    /**
     * 新增特定人群
     */
    @PreAuthorize("@ss.hasPermi('system:specify:add')")
    @Log(title = "特定人群", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPromiseSpecify sysPromiseSpecify)
    {
        return toAjax(sysPromiseSpecifyService.insertSysPromiseSpecify(sysPromiseSpecify));
    }

    /**
     * 修改特定人群
     */
    @PreAuthorize("@ss.hasPermi('system:specify:edit')")
    @Log(title = "特定人群", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPromiseSpecify sysPromiseSpecify)
    {
        return toAjax(sysPromiseSpecifyService.updateSysPromiseSpecify(sysPromiseSpecify));
    }

    /**
     * 删除特定人群
     */
    @PreAuthorize("@ss.hasPermi('system:specify:remove')")
    @Log(title = "特定人群", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysPromiseSpecifyService.deleteSysPromiseSpecifyByIds(ids));
    }
}
