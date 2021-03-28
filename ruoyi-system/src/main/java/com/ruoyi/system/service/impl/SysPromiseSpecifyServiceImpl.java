package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysPromiseSpecify;
import com.ruoyi.system.mapper.SysPromiseSpecifyMapper;
import com.ruoyi.system.service.ISysPromiseSpecifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 特定人群Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-27
 */
@Service
public class SysPromiseSpecifyServiceImpl implements ISysPromiseSpecifyService {
    @Autowired
    private SysPromiseSpecifyMapper sysPromiseSpecifyMapper;

    /**
     * 查询特定人群
     *
     * @param id 特定人群ID
     * @return 特定人群
     */
    @Override
    public SysPromiseSpecify selectSysPromiseSpecifyById(Long id) {
        return sysPromiseSpecifyMapper.selectSysPromiseSpecifyById(id);
    }

    /**
     * 查询特定人群列表
     *
     * @param sysPromiseSpecify 特定人群
     * @return 特定人群
     */
    @Override
    public List<SysPromiseSpecify> selectSysPromiseSpecifyList(SysPromiseSpecify sysPromiseSpecify) {
        return sysPromiseSpecifyMapper.selectSysPromiseSpecifyList(sysPromiseSpecify);
    }

    /**
     * 新增特定人群
     *
     * @param sysPromiseSpecify 特定人群
     * @return 结果
     */
    @Override
    public int insertSysPromiseSpecify(SysPromiseSpecify sysPromiseSpecify) {
        sysPromiseSpecify.setCreateTime(DateUtils.getNowDate());
        return sysPromiseSpecifyMapper.insertSysPromiseSpecify(sysPromiseSpecify);
    }

    /**
     * 修改特定人群
     *
     * @param sysPromiseSpecify 特定人群
     * @return 结果
     */
    @Override
    public int updateSysPromiseSpecify(SysPromiseSpecify sysPromiseSpecify) {
        sysPromiseSpecify.setUpdateTime(DateUtils.getNowDate());
        return sysPromiseSpecifyMapper.updateSysPromiseSpecify(sysPromiseSpecify);
    }

    /**
     * 批量删除特定人群
     *
     * @param ids 需要删除的特定人群ID
     * @return 结果
     */
    @Override
    public int deleteSysPromiseSpecifyByIds(Long[] ids) {
        return sysPromiseSpecifyMapper.deleteSysPromiseSpecifyByIds(ids);
    }

    /**
     * 删除特定人群信息
     *
     * @param id 特定人群ID
     * @return 结果
     */
    @Override
    public int deleteSysPromiseSpecifyById(Long id) {
        return sysPromiseSpecifyMapper.deleteSysPromiseSpecifyById(id);
    }

}
