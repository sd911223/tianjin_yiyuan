package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.req.ReserveAmContentReq;

import java.util.List;

/**
 * 预约管理业务层
 *
 * @author shitou
 */
public interface SysReserveService {
    /**
     * 根据条件分页查询预约管理列表
     *
     * @param businessReserve
     * @return
     */
    List<BusinessReserve> selectReserveList(BusinessReserve businessReserve);

    int insertReserve(BusinessReserve businessReserve, List<ReserveAmContentReq> reserveAmContentList);

    int updateReserveStatus(BusinessReserve businessReserve);

    int deleteReserveById(Integer id);

    BusinessReserve selectReserveById(Integer id);

    int updateReserve(BusinessReserve upReserve, List<ReserveAmContentReq> reserveAmContentList);
}
