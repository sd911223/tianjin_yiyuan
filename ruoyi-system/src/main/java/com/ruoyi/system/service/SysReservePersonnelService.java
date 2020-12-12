package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.req.SiteDetailedReq;

import java.util.List;

public interface SysReservePersonnelService {
    List<BusinessReservePersonnel> selectPersonneList(SiteDetailedReq siteDetailedReq);

    BusinessReservePersonnel selectPersonnelById(Integer id);

    int updatePersonnelStatus(BusinessReservePersonnel businessReservePersonnel);
}
