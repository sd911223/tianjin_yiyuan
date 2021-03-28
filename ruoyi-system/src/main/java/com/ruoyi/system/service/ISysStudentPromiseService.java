package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysPromiseSpecify;
import com.ruoyi.system.domain.SysStudentPromise;

import java.util.List;

/**
 * 承诺管理Service接口
 *
 * @author ruoyi
 * @date 2021-03-27
 */
public interface ISysStudentPromiseService {
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
     * 批量删除承诺管理
     *
     * @param ids 需要删除的承诺管理ID
     * @return 结果
     */
    public int deleteSysStudentPromiseByIds(Long[] ids);

    /**
     * 删除承诺管理信息
     *
     * @param id 承诺管理ID
     * @return 结果
     */
    public int deleteSysStudentPromiseById(Long id);

    void importUser(List<SysPromiseSpecify> userList, Long promiseId);

    /**
     * 发布承诺
     *
     * @param ids
     * @return
     */
    int releaseSysStudentPromiseByIds(Long[] ids);

    /**
     * 关闭发布
     *
     * @param ids
     * @return
     */
    int turnOffSysStudentPromiseByIds(Long[] ids);

    /**
     * 撤销承诺
     *
     * @param ids
     * @return
     */
    int dismantleSysStudentPromiseByIds(Long[] ids);
}
