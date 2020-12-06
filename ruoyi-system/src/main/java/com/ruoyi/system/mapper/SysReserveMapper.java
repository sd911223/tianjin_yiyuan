package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.BusinessReserve;

import java.util.List;

/**
 * 预约管理 数据层
 *
 * @author hsitou
 */
public interface SysReserveMapper {

    List<BusinessReserve> selectReserveList(BusinessReserve businessReserve);
}
