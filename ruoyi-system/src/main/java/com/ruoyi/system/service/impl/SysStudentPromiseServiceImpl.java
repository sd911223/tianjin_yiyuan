package com.ruoyi.system.service.impl;

import com.ruoyi.common.enums.PromiseStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPromiseSpecify;
import com.ruoyi.system.domain.SysStudentPromise;
import com.ruoyi.system.mapper.SysPromiseSpecifyMapper;
import com.ruoyi.system.mapper.SysStudentPromiseMapper;
import com.ruoyi.system.service.ISysStudentPromiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 承诺管理Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-27
 */
@Service
public class SysStudentPromiseServiceImpl implements ISysStudentPromiseService {
    @Autowired
    private SysStudentPromiseMapper sysStudentPromiseMapper;
    @Autowired
    SysPromiseSpecifyMapper sysPromiseSpecifyMapper;

    /**
     * 查询承诺管理
     *
     * @param id 承诺管理ID
     * @return 承诺管理
     */
    @Override
    public SysStudentPromise selectSysStudentPromiseById(Long id) {
        return sysStudentPromiseMapper.selectSysStudentPromiseById(id);
    }

    /**
     * 查询承诺管理列表
     *
     * @param sysStudentPromise 承诺管理
     * @return 承诺管理
     */
    @Override
    public List<SysStudentPromise> selectSysStudentPromiseList(SysStudentPromise sysStudentPromise) {
        return sysStudentPromiseMapper.selectSysStudentPromiseList(sysStudentPromise);
    }

    /**
     * 新增承诺管理
     *
     * @param sysStudentPromise 承诺管理
     * @return 结果
     */
    @Override
    public int insertSysStudentPromise(SysStudentPromise sysStudentPromise) {
        sysStudentPromise.setCreateTime(DateUtils.getNowDate());
        sysStudentPromise.setModuleStatus(PromiseStatus.NO_ISSUE.getCode());
        return sysStudentPromiseMapper.insertSysStudentPromise(sysStudentPromise);
    }

    /**
     * 修改承诺管理
     *
     * @param sysStudentPromise 承诺管理
     * @return 结果
     */
    @Override
    public int updateSysStudentPromise(SysStudentPromise sysStudentPromise) {
        sysStudentPromise.setUpdateTime(DateUtils.getNowDate());
        return sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
    }

    /**
     * 批量删除承诺管理
     *
     * @param ids 需要删除的承诺管理ID
     * @return 结果
     */
    @Override
    public int deleteSysStudentPromiseByIds(Long[] ids) {
        List<Long> longList = Arrays.asList(ids);
        longList.forEach(e -> {
            sysStudentPromiseMapper.updateSysStudentPromiseById(e);
        });
        return longList.size();
    }

    /**
     * 删除承诺管理信息
     *
     * @param id 承诺管理ID
     * @return 结果
     */
    @Override
    public int deleteSysStudentPromiseById(Long id) {
        return sysStudentPromiseMapper.deleteSysStudentPromiseById(id);
    }

    @Override
    @Transactional
    public void importUser(List<SysPromiseSpecify> userList, Long promiseId) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        SysPromiseSpecify sysPromiseSpecify = new SysPromiseSpecify();
        sysPromiseSpecify.setPromiseId(promiseId);
        List<SysPromiseSpecify> sysPromiseSpecifyList = sysPromiseSpecifyMapper.selectSysPromiseSpecifyList(sysPromiseSpecify);
        if (!sysPromiseSpecifyList.isEmpty()) {
            sysPromiseSpecifyMapper.updateSysPromiseSpecifyByPId(promiseId);
        }
        long successNum = 0;
        for (SysPromiseSpecify specify : userList) {
            specify.setDelFlag("0");
            specify.setPromiseId(promiseId);
            List<SysPromiseSpecify> specifyList = sysPromiseSpecifyMapper.selectSysPromiseSpecifyList(specify);
            if (!specifyList.isEmpty()) {
                SysPromiseSpecify promiseSpecify = specifyList.get(0);
                promiseSpecify.setIdCard(promiseSpecify.getIdCard());
                promiseSpecify.setName(promiseSpecify.getName());
                sysPromiseSpecifyMapper.updateSysPromiseSpecify(promiseSpecify);
                continue;
            }
            specify.setPromiseId(promiseId);
            specify.setCreateTime(DateUtils.getNowDate());
            sysPromiseSpecifyMapper.insertSysPromiseSpecify(specify);
            successNum++;
        }
        SysStudentPromise sysStudentPromise = sysStudentPromiseMapper.selectSysStudentPromiseById(promiseId);
        sysStudentPromise.setImportStu(successNum);
        sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
    }

    /**
     * 发布承诺
     *
     * @param ids
     * @return
     */
    @Override
    public int releaseSysStudentPromiseByIds(Long[] ids) {
        List<Long> longList = Arrays.asList(ids);
        longList.forEach(e -> {
            SysStudentPromise sysStudentPromise = sysStudentPromiseMapper.selectSysStudentPromiseById(e);
            sysStudentPromise.setModuleStatus(PromiseStatus.ISSUE.getCode());
            sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
        });
        return longList.size();
    }

    /**
     * 关闭承诺
     *
     * @param ids
     * @return
     */
    @Override
    public int turnOffSysStudentPromiseByIds(Long[] ids) {
        List<Long> longList = Arrays.asList(ids);
        longList.forEach(e -> {
            SysStudentPromise sysStudentPromise = sysStudentPromiseMapper.selectSysStudentPromiseById(e);
            sysStudentPromise.setModuleStatus(PromiseStatus.CLOSE_ISSUE.getCode());
            sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
        });
        return longList.size();
    }

    /**
     * 撤销承诺
     *
     * @param ids
     * @return
     */
    @Override
    public int dismantleSysStudentPromiseByIds(Long[] ids) {
        List<Long> longList = Arrays.asList(ids);
        longList.forEach(e -> {
            SysStudentPromise sysStudentPromise = sysStudentPromiseMapper.selectSysStudentPromiseById(e);
            sysStudentPromise.setModuleStatus(PromiseStatus.NO_ISSUE.getCode());
            sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
        });
        return longList.size();
    }
}
