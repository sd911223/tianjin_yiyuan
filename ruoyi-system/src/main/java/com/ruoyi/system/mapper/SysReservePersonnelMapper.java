package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;

import java.util.List;

public interface SysReservePersonnelMapper {
    BusinessReservePersonnel selectPersonnelById(Integer id);

    List<BusinessReservePersonnel> selectPersonnelByRId(Integer reserveId);

    List<BusinessReservePersonnel> selectPersonneList(BusinessReservePersonnel businessReservePersonnel);

    int updatePersonnelStatus(BusinessReservePersonnel businessReservePersonnel);
}
