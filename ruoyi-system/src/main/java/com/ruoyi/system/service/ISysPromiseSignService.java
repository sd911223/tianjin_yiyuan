package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPromiseSign;

/**
 * 承诺填写Service接口
 * 
 * @author ruoyi
 * @date 2021-03-28
 */
public interface ISysPromiseSignService 
{
    /**
     * 查询承诺填写
     * 
     * @param id 承诺填写ID
     * @return 承诺填写
     */
    public SysPromiseSign selectSysPromiseSignById(Long id);

    /**
     * 查询承诺填写列表
     * 
     * @param sysPromiseSign 承诺填写
     * @return 承诺填写集合
     */
    public List<SysPromiseSign> selectSysPromiseSignList(SysPromiseSign sysPromiseSign);

    /**
     * 新增承诺填写
     * 
     * @param sysPromiseSign 承诺填写
     * @return 结果
     */
    public int insertSysPromiseSign(SysPromiseSign sysPromiseSign);

    /**
     * 修改承诺填写
     * 
     * @param sysPromiseSign 承诺填写
     * @return 结果
     */
    public int updateSysPromiseSign(SysPromiseSign sysPromiseSign);

    /**
     * 批量删除承诺填写
     * 
     * @param ids 需要删除的承诺填写ID
     * @return 结果
     */
    public int deleteSysPromiseSignByIds(Long[] ids);

    /**
     * 删除承诺填写信息
     * 
     * @param id 承诺填写ID
     * @return 结果
     */
    public int deleteSysPromiseSignById(Long id);

    int selectSysPromiseSignCount(Long id);
}
