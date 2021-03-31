package com.ruoyi.system.service.impl;

import com.ruoyi.common.enums.PromiseType;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.domain.SysPromiseSign;
import com.ruoyi.system.domain.SysPromiseSpecify;
import com.ruoyi.system.domain.SysStudentPromise;
import com.ruoyi.system.mapper.SysPromiseSignMapper;
import com.ruoyi.system.mapper.SysPromiseSpecifyMapper;
import com.ruoyi.system.mapper.SysStudentPromiseMapper;
import com.ruoyi.system.service.ISysPromiseSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 承诺填写Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-28
 */
@Service
public class SysPromiseSignServiceImpl implements ISysPromiseSignService {
    @Autowired
    private SysPromiseSignMapper sysPromiseSignMapper;
    @Autowired
    private SysStudentPromiseMapper sysStudentPromiseMapper;
    @Autowired
    private SysPromiseSpecifyMapper sysPromiseSpecifyMapper;

    /**
     * 查询承诺填写
     *
     * @param id 承诺填写ID
     * @return 承诺填写
     */
    @Override
    public SysPromiseSign selectSysPromiseSignById(Long id) {
        return sysPromiseSignMapper.selectSysPromiseSignById(id);
    }

    /**
     * 查询承诺填写列表
     *
     * @param sysPromiseSign 承诺填写
     * @return 承诺填写
     */
    @Override
    public List<SysPromiseSign> selectSysPromiseSignList(SysPromiseSign sysPromiseSign) {
        return sysPromiseSignMapper.selectSysPromiseSignList(sysPromiseSign);
    }

    /**
     * 新增承诺填写
     *
     * @param sysPromiseSign 承诺填写
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysPromiseSign(SysPromiseSign sysPromiseSign) {
        if (StringUtils.isEmpty(sysPromiseSign.getOpenId())) {
            throw new CustomException("openId为空！");
        }
        //查询是否提交过
        SysPromiseSign promiseSign = sysPromiseSignMapper.selectSysPromiseSignByOpenId(sysPromiseSign.getOpenId());
        if (promiseSign != null) {
            BeanUtils.copyProperties(sysPromiseSign, promiseSign);
            return sysPromiseSignMapper.updateSysPromiseSign(promiseSign);
        }
        //判断所属人群
        SysStudentPromise sysStudentPromise = sysStudentPromiseMapper.selectSysStudentPromiseById(sysPromiseSign.getPromiseId());
        if (sysStudentPromise.getBeauType().equals(PromiseType.SPECIFY.getCode())) {
            SysPromiseSpecify sysPromiseSpecify = new SysPromiseSpecify();
            sysPromiseSpecify.setPromiseId(sysPromiseSign.getPromiseId());
            sysPromiseSpecify.setIdCard(sysPromiseSign.getEstimate2());
            List<SysPromiseSpecify> specifyList = sysPromiseSpecifyMapper.selectSysPromiseSpecifyList(sysPromiseSpecify);
            if (specifyList.isEmpty()) {
                throw new CustomException("不在规定人群内！");
            }
        }
        sysStudentPromise.setWriteNumber(sysStudentPromise.getWriteNumber() + 1);
        //增加报名人数
        sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
        sysPromiseSign.setCreateTime(DateUtils.getNowDate());
        return sysPromiseSignMapper.insertSysPromiseSign(sysPromiseSign);
    }

    /**
     * 修改承诺填写
     *
     * @param sysPromiseSign 承诺填写
     * @return 结果
     */
    @Override
    public int updateSysPromiseSign(SysPromiseSign sysPromiseSign) {
        sysPromiseSign.setUpdateTime(DateUtils.getNowDate());
        return sysPromiseSignMapper.updateSysPromiseSign(sysPromiseSign);
    }

    /**
     * 批量删除承诺填写
     *
     * @param ids 需要删除的承诺填写ID
     * @return 结果
     */
    @Override
    public int deleteSysPromiseSignByIds(Long[] ids) {
        return sysPromiseSignMapper.deleteSysPromiseSignByIds(ids);
    }

    /**
     * 删除承诺填写信息
     *
     * @param id 承诺填写ID
     * @return 结果
     */
    @Override
    public int deleteSysPromiseSignById(Long id) {
        return sysPromiseSignMapper.deleteSysPromiseSignById(id);
    }
}
