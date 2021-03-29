package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysStudentPromise;

import java.util.List;

/**
 * 承诺管理Mapper接口
 *
 * @author ruoyi
 * @date 2021-03-27
 */
public interface SysStudentPromiseMapper {
    /**
     * 查询承诺管理
     *
     * @param id 承诺管理ID
     * @return 承诺管理
     */
    public SysStudentPromise selectSysStudentPromiseById(Long id);

    /**
     * 查询承诺管理列表
     *
     * @param sysStudentPromise 承诺管理
     * @return 承诺管理集合
     */
    public List<SysStudentPromise> selectSysStudentPromiseList(SysStudentPromise sysStudentPromise);

    /**
     * 新增承诺管理
     *
     * @param sysStudentPromise 承诺管理
     * @return 结果
     */
    public int insertSysStudentPromise(SysStudentPromise sysStudentPromise);

    /**
     * 修改承诺管理
     *
     * @param sysStudentPromise 承诺管理
     * @return 结果
     */
    public int updateSysStudentPromise(SysStudentPromise sysStudentPromise);

    /**
     * 删除承诺管理
     *
     * @param id 承诺管理ID
     * @return 结果
     */
    public int deleteSysStudentPromiseById(Long id);

    /**
     * 批量删除承诺管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysStudentPromiseByIds(Long[] ids);

    int updateSysStudentPromiseById(Long id);
}
