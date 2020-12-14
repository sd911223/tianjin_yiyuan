package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.system.mapper.SysReserveContentMapper;
import com.ruoyi.system.mapper.SysReservePersonnelMapper;
import com.ruoyi.system.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatServiceImpl implements WechatService {
    @Autowired
    SysReservePersonnelMapper sysReservePersonnelMapper;
    @Autowired
    SysReserveContentMapper sysReserveContentMapper;

    @Override
    public AjaxResult insertPersonnel(BusinessReservePersonnel businessReservePersonnel) {
        sysReservePersonnelMapper.insertPersonnel(businessReservePersonnel);
        BusinessReserveContent businessReserveContent = sysReserveContentMapper.selectContentById(businessReservePersonnel.getContentId());
        if (businessReserveContent == null) {
            return AjaxResult.error("我要预约-内容预约号'" + businessReservePersonnel.getReserveId() + "'失败，预约项目不存在");
        }
        if (businessReserveContent.getSurplusNumber() <1) {
            return AjaxResult.error("我要预约-内容预约号'" + businessReservePersonnel.getReserveId() + "'失败，预约已满");
        }

        return null;
    }
}
