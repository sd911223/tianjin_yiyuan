package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysBase;
import com.ruoyi.system.mapper.SysBaseMapper;
import com.ruoyi.system.service.SysBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysBaseServiceImpl implements SysBaseService {
    @Autowired
    SysBaseMapper sysBaseMapper;

    @Override
    public int updateBase(SysBase sysBase) {
        return sysBaseMapper.updateBase(sysBase);
    }
}
