package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysJurisdiction;
import com.ruoyi.system.mapper.SysJurisdictionMapper;
import com.ruoyi.system.service.ISysJurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限 服务层实现
 *
 * @author shitou
 */
@Service
public class ISysJurisdictionServiceImpl implements ISysJurisdictionService {
    @Autowired
    SysJurisdictionMapper sysJurisdictionMapper;

    /**
     * 查询角色_权限 实现
     *
     * @return
     */
    @Override
    public List<SysJurisdiction> selectJurisdictionList() {
        return sysJurisdictionMapper.selectJurisdictionList();
    }
}
