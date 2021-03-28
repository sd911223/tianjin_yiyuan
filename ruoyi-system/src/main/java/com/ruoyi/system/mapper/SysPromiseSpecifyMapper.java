package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPromiseSpecify;

/**
 * 特定人群Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-27
 */
public interface SysPromiseSpecifyMapper 
{
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
     * 删除特定人群
     * 
     * @param id 特定人群ID
     * @return 结果
     */
    public int deleteSysPromiseSpecifyById(Long id);

    /**
     * 批量删除特定人群
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysPromiseSpecifyByIds(Long[] ids);


    public int updateSysPromiseSpecifyByPId(Long promiseId);
}
