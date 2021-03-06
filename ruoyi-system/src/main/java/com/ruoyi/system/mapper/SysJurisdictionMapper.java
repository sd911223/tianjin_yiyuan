package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysJurisdiction;

import java.util.List;

/**
 * 角色_权限 数据层
 *
 * @author hsitou
 */
public interface SysJurisdictionMapper {

    List<SysJurisdiction> selectJurisdictionList();

    SysJurisdiction checkJurNameUnique(SysJurisdiction sysJurisdiction);

    int insertJurisdiction(SysJurisdiction sysJurisdiction);

    SysJurisdiction selectJurisdictionById(Integer id);
}
