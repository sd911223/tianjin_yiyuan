package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.req.ReserveCancelReq;

import java.util.List;

public interface WechatService {
    AjaxResult insertPersonnel(BusinessReservePersonnel businessReservePersonnel);

    List<BusinessReservePersonnel> myReserve(String openId);

    AjaxResult reserveCancel(ReserveCancelReq reserveCancelReq);

    AjaxResult getAccessToken();
}
