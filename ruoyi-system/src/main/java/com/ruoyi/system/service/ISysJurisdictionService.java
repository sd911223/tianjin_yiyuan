package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysJurisdiction;

import java.util.List;

/**
 * 角色权限业务层
 *
 * @author shitou
 */
public interface ISysJurisdictionService {
    /**
     * 查询角色_权限 接口
     *
     * @return
     */
    List<SysJurisdiction> selectJurisdictionList();
}
