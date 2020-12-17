package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.BusinessReserve;

import java.util.List;

/**
 * 预约管理 数据层
 *
 * @author hsitou
 */
public interface SysReserveMapper {
    /**
     * 查询活动列表
     *
     * @param businessReserve
     * @return
     */
    List<BusinessReserve> selectReserveList(BusinessReserve businessReserve);

    /**
     * 添加活动
     *
     * @param businessReserve
     * @return
     */
    int insertReserve(BusinessReserve businessReserve);

    /**
     * 修改活动状态
     *
     * @param businessReserve
     * @return
     */
    int updateReserveStatus(BusinessReserve businessReserve);

    /**
     * 删除活动信息
     *
     * @param id 活动ID
     * @return 结果
     */
    int deleteReserveById(Integer[] id);

    BusinessReserve selectReserveById(Integer id);

    int updateReserve(BusinessReserve upReserve);

    int updateReserveNum(BusinessReserve businessReserve);

}
