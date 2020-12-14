package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.req.ReserveCancelReq;
import com.ruoyi.system.mapper.SysReserveContentMapper;
import com.ruoyi.system.mapper.SysReserveMapper;
import com.ruoyi.system.mapper.SysReservePersonnelMapper;
import com.ruoyi.system.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WechatServiceImpl implements WechatService {
    @Autowired
    SysReservePersonnelMapper sysReservePersonnelMapper;
    @Autowired
    SysReserveContentMapper sysReserveContentMapper;
    @Autowired
    SysReserveMapper sysReserveMapper;

    @Transactional
    @Override
    public AjaxResult insertPersonnel(BusinessReservePersonnel businessReservePersonnel) {
        BusinessReserve businessReserve = sysReserveMapper.selectReserveById(businessReservePersonnel.getReserveId());
        if (businessReserve == null) {
            return AjaxResult.error("我要预约-预约号'" + businessReservePersonnel.getReserveId() + "'失败，预约项目不存在");
        }
        if ("1".equals(businessReserve.getReserveType())) {
            String idCard = businessReserve.getIdCard();
            if (!idCard.contains(businessReservePersonnel.getIdCard())) {
                return AjaxResult.error("我要预约-预约号'" + businessReservePersonnel.getReserveId() + "'失败，不在特定人群");
            }
        }
        sysReservePersonnelMapper.insertPersonnel(businessReservePersonnel);
        BusinessReserveContent businessReserveContent = sysReserveContentMapper.selectContentById(businessReservePersonnel.getContentId());
        if (businessReserveContent == null) {
            return AjaxResult.error("我要预约-内容预约号'" + businessReservePersonnel.getReserveId() + "'失败，预约项目不存在");
        }
        if (businessReserveContent.getSurplusNumber() < 1) {
            return AjaxResult.error("我要预约-内容预约号'" + businessReservePersonnel.getReserveId() + "'失败，预约已满");
        }
        businessReserveContent.setSurplusNumber(businessReserveContent.getSurplusNumber() - 1);
        sysReserveContentMapper.updateSurplusNumber(businessReserveContent);
        businessReserve.setReserveNum(businessReserve.getReserveNum() + 1);
        sysReserveMapper.updateReserveNum(businessReserve);

        return AjaxResult.success("预约成功!");
    }

    @Override
    public List<BusinessReservePersonnel> myReserve(String openId) {
        BusinessReservePersonnel businessReservePersonnel = new BusinessReservePersonnel();
        businessReservePersonnel.setOpenId(openId);
        return sysReservePersonnelMapper.selectPersonneList(businessReservePersonnel);
    }

    @Transactional
    @Override
    public AjaxResult reserveCancel(ReserveCancelReq reserveCancelReq) {
        BusinessReservePersonnel businessReservePersonnel = new BusinessReservePersonnel();
        businessReservePersonnel.setOpenId(reserveCancelReq.getOpenId());
        businessReservePersonnel.setReserveId(reserveCancelReq.getReserveId());
        businessReservePersonnel.setContentId(reserveCancelReq.getContentId());
        List<BusinessReservePersonnel> list = sysReservePersonnelMapper.selectPersonneList(businessReservePersonnel);
        if (list.isEmpty()) {
            return AjaxResult.error("取消预约-内容预约号'" + reserveCancelReq.getReserveId() + "'失败，预约项目不存在");
        }
        sysReservePersonnelMapper.updatePersonnelCanceType(list.get(0));

        BusinessReserveContent businessReserveContent = sysReserveContentMapper.selectContentById(reserveCancelReq.getContentId());

        businessReserveContent.setSurplusNumber(businessReserveContent.getSurplusNumber() + 1);
        sysReserveContentMapper.updateSurplusNumber(businessReserveContent);
        BusinessReserve businessReserve = new BusinessReserve();
        businessReserve.setReserveNum(businessReserve.getReserveNum() - 1);
        sysReserveMapper.updateReserveNum(businessReserve);
        return AjaxResult.success("取消预约成功!");
    }
}
