package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysPromiseSpecify;

import java.util.List;

/**
 * 特定人群Service接口
 *
 * @author ruoyi
 * @date 2021-03-27
 */
public interface ISysPromiseSpecifyService {
    /**
     * 查询特定人群
     *
     * @param id 特定人群ID
     * @return 特定人群
     */
    public SysPromiseSpecify selectSysPromiseSpecifyById(Long id);

    /**
     * 查询特定人群列表
     *
     * @param sysPromiseSpecify 特定人群
     * @return 特定人群集合
     */
    public List<SysPromiseSpecify> selectSysPromiseSpecifyList(SysPromiseSpecify sysPromiseSpecify);

    /**
     * 新增特定人群
     *
     * @param sysPromiseSpecify 特定人群
     * @return 结果
     */
    public int insertSysPromiseSpecify(SysPromiseSpecify sysPromiseSpecify);

    /**
     * 修改特定人群
     *
     * @param sysPromiseSpecify 特定人群
     * @return 结果
     */
    public int updateSysPromiseSpecify(SysPromiseSpecify sysPromiseSpecify);

    /**
     * 批量删除特定人群
     *
     * @param ids 需要删除的特定人群ID
     * @return 结果
     */
    public int deleteSysPromiseSpecifyByIds(Long[] ids);

    /**
     * 删除特定人群信息
     *
     * @param id 特定人群ID
     * @return 结果
     */
    public int deleteSysPromiseSpecifyById(Long id);

}
