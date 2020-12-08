package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysBase;

public interface SysBaseMapper {
    int updateBase(SysBase sysBase);

    SysBase selectBaseById(Long id);
}
