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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 承诺填写Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-28
 */
@Service
public class SysPromiseSignServiceImpl implements ISysPromiseSignService {
    private static final Logger log = LoggerFactory.getLogger(SysPromiseSignServiceImpl.class);
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
        //判断所属人群
        SysStudentPromise sysStudentPromise = sysStudentPromiseMapper.selectSysStudentPromiseById(sysPromiseSign.getPromiseId());
        if (null == sysStudentPromise) {
            throw new CustomException("找不到承诺ID！");
        }

        SysPromiseSign sign = new SysPromiseSign();
//        sign.setOpenId(sysPromiseSign.getOpenId());
        sign.setIdCard(sysPromiseSign.getIdCard());
        sign.setPromiseId(sysPromiseSign.getPromiseId());
        //查询是否提交过
        List<SysPromiseSign> promiseSign = sysPromiseSignMapper.selectSysPromiseSignList(sign);
        if (!promiseSign.isEmpty() && sysStudentPromise.getBeauType().equals(PromiseType.ALL.getCode())) {
            sysPromiseSign.setId(null);
            SysPromiseSign sign1 = promiseSign.get(0);
            BeanUtils.copyProperties(sysPromiseSign, sign1, getNullPropertyNames(sysPromiseSign));

            //增加报名人数
            if (sign1.getCodeColor().equals("3")) {
                sysStudentPromise.setWriteNumber(sysStudentPromise.getWriteNumber() + 1);
                sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
            }
            return sysPromiseSignMapper.updateSysPromiseSign(promiseSign.get(0));
        }

        log.info("承诺ID:{},人群类型:{}", sysPromiseSign.getPromiseId(), sysStudentPromise.getBeauType());
        if (sysStudentPromise.getBeauType().equals(PromiseType.SPECIFY.getCode())) {
            log.info("=============================进入特定人群================================");

            SysPromiseSpecify sysPromiseSpecify = new SysPromiseSpecify();
            sysPromiseSpecify.setPromiseId(sysPromiseSign.getPromiseId());
            sysPromiseSpecify.setIdCard(sysPromiseSign.getEstimate2());
            sysPromiseSpecify.setName(sysPromiseSign.getName());
            List<SysPromiseSpecify> specifyList = sysPromiseSpecifyMapper.selectSysPromiseSpecifyList(sysPromiseSpecify);
            if (specifyList.isEmpty()) {
                log.info("没有查到人群:身份证号:{},姓名:{}", sysPromiseSign.getEstimate2(), sysPromiseSign.getName());
                throw new CustomException("不在规定人群内！");
            }
            SysPromiseSign sysPromiseSign1 = new SysPromiseSign();
            sysPromiseSign1.setId(null);
            sysPromiseSign1.setIdCard(sysPromiseSign.getEstimate2());
            sysPromiseSign1.setUserName(sysPromiseSign.getName());
            List<SysPromiseSign> sysPromiseSigns = sysPromiseSignMapper.selectSysPromiseSignList(sysPromiseSign1);
            SysPromiseSign sysPromiseSign2 = sysPromiseSigns.get(0);
            BeanUtils.copyProperties(sysPromiseSign, sysPromiseSign2, getNullPropertyNames(sysPromiseSign));
            log.info("=============================进入特定人群结束================================");
            //增加报名人数
            if (sysPromiseSign2.getCodeColor().equals("3")) {
                sysStudentPromise.setWriteNumber(sysStudentPromise.getWriteNumber() + 1);
                sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
            }
            sysPromiseSign.setCreateTime(DateUtils.getNowDate());
            sysPromiseSign.setUpdateTime(DateUtils.getNowDate());
            return sysPromiseSignMapper.updateSysPromiseSign(sysPromiseSign2);
        } else {
            //增加报名人数
            sysStudentPromise.setWriteNumber(sysStudentPromise.getWriteNumber() + 1);
            sysStudentPromiseMapper.updateSysStudentPromise(sysStudentPromise);
            sysPromiseSign.setCreateTime(DateUtils.getNowDate());
            sysPromiseSign.setUpdateTime(DateUtils.getNowDate());
            return sysPromiseSignMapper.insertSysPromiseSign(sysPromiseSign);
        }

    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
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
