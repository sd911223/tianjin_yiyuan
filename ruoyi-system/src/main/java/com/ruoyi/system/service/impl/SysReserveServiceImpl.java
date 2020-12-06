package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.system.mapper.SysReserveMapper;
import com.ruoyi.system.service.SysReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public List<BusinessReserve> selectReserveList(BusinessReserve businessReserve) {
        return sysReserveMapper.selectReserveList(businessReserve);
    }
}
