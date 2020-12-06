package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.BusinessReserve;

import java.util.List;

/**
 * 预约管理业务层
 *
 * @author shitou
 */
public interface SysReserveService {
    List<BusinessReserve> selectReserveList(BusinessReserve businessReserve);
}
