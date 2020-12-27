package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.req.SiteDetailedReq;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysReservePersonnelMapper;
import com.ruoyi.system.service.SysReservePersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysReservePersonnelServiceImpl implements SysReservePersonnelService {
    @Autowired
    SysReservePersonnelMapper sysReservePersonnelMapper;

    @Override
    public List<BusinessReservePersonnel> selectPersonneList(SiteDetailedReq siteDetailedReq) {
        BusinessReservePersonnel businessReservePersonnel = new BusinessReservePersonnel();
        if (null != siteDetailedReq.getReserveId()) {
            businessReservePersonnel.setReserveId(siteDetailedReq.getReserveId());
        }
        if (null != siteDetailedReq.getAppointmentDate()) {
            businessReservePersonnel.setAppointmentDate(siteDetailedReq.getAppointmentDate());
        }
        if (!StringUtils.isEmpty(siteDetailedReq.getAppointmentPeriod())) {
            businessReservePersonnel.setAppointmentPeriod(siteDetailedReq.getAppointmentPeriod());
        }
        if (!StringUtils.isEmpty(siteDetailedReq.getReserveNumber())) {
            businessReservePersonnel.setReserveNumber(siteDetailedReq.getReserveNumber());
        }
        if (!StringUtils.isEmpty(siteDetailedReq.getName())) {
            businessReservePersonnel.setName(siteDetailedReq.getName());
        }
        if (!StringUtils.isEmpty(siteDetailedReq.getIdCard())) {
            businessReservePersonnel.setIdCard(siteDetailedReq.getIdCard());
        }
        if (!StringUtils.isEmpty(siteDetailedReq.getPhoneNumber())) {
            businessReservePersonnel.setPhoneNumber(siteDetailedReq.getPhoneNumber());
        }
//        businessReservePersonnel.setCanceType("0");
        return sysReservePersonnelMapper.selectPersonneList(businessReservePersonnel);
    }

    @Override
    public BusinessReservePersonnel selectPersonnelById(Integer id) {
        return sysReservePersonnelMapper.selectPersonnelById(id);
    }

    @Override
    public int updatePersonnelStatus(BusinessReservePersonnel businessReservePersonnel) {
        return sysReservePersonnelMapper.updatePersonnelStatus(businessReservePersonnel);
    }

    @Override
    public int selectFinishCount(Integer id) {
        return sysReservePersonnelMapper.selectFinishCount(id);
    }
}
