package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.BusinessReserveContent;

import java.util.List;

/**
 * 预约内容管理 数据层
 *
 * @author hsitou
 */
public interface SysReserveContentMapper {

    int insertReserveContent(BusinessReserveContent businessReserveContent);

    List<BusinessReserveContent> selectContentByRId(Integer reserveId);

    int delectReserveContent(Integer id);
}
