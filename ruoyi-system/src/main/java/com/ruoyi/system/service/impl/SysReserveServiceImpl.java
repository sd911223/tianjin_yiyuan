package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysReserveMapper;
import com.ruoyi.system.service.SysReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 预约管理 服务层实现
 *
 * @author shitou
 */
@Service
public class SysReserveServiceImpl implements SysReserveService {
    @Autowired
    SysReserveMapper sysReserveMapper;
    @Autowired
    SysDeptMapper sysDeptMapper;

    @Override
    public List<BusinessReserve> selectReserveList(BusinessReserve businessReserve) {
        return sysReserveMapper.selectReserveList(businessReserve);
    }

    @Override
    public int insertReserve(BusinessReserve businessReserve) {
        businessReserve.setCreateTime(new Date());
        businessReserve.setCreateBy(SecurityUtils.getUsername());
        Long deptId = SecurityUtils.getLoginUser().getUser().getDeptId();
        businessReserve.setDeptId(deptId.intValue());
        SysDept sysDept = sysDeptMapper.selectDeptById(deptId);
        businessReserve.setDeptName(sysDept.getDeptName());
        return sysReserveMapper.insertReserve(businessReserve);
    }

    @Override
    public int updateReserveStatus(BusinessReserve businessReserve) {
        return sysReserveMapper.updateReserveStatus(businessReserve);
    }

    @Override
    public int deleteReserveById(Long id) {
        return sysReserveMapper.deleteReserveById(id);
    }

    @Override
    public BusinessReserve selectReserveById(Integer id) {
        return sysReserveMapper.selectReserveById(id);
    }
}
