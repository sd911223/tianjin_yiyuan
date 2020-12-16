package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.req.ReserveCancelReq;
import com.ruoyi.common.core.domain.entity.resp.WechatAccessTokenResp;
import com.ruoyi.common.core.domain.entity.resp.WechatResp;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.mapper.SysReserveContentMapper;
import com.ruoyi.system.mapper.SysReserveMapper;
import com.ruoyi.system.mapper.SysReservePersonnelMapper;
import com.ruoyi.system.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${wechat.appid}")
    private String appid;
    @Value("{${wechat.secret}}")
    private String secret;

    @Value("${wechat.base_access_token_url}")
    private String baseAccessTokenUrl;
    @Value("${wechat.oauth2_url}")
    private String oauth2Url;
    @Value("${wechat.access_token_url}")
    private String accessTokenUrl;


    public AjaxResult getAccessToken() {
        StringBuffer sb = new StringBuffer();
        sb.append("grant_type=client_credential&");
        sb.append("appid=");
        sb.append(appid);
        sb.append("&secret=");
        sb.append(secret);
        String s = HttpUtils.sendGet(baseAccessTokenUrl, sb.toString());
        WechatResp wechatResp = JSON.parseObject(JSON.toJSONString(s), WechatResp.class);
        if (!"".equals(wechatResp.getAccess_token())) {
            return AjaxResult.success(wechatResp.getAccess_token());
        } else {
            return AjaxResult.error("获取accessToken失败");
        }
    }

    @Override
    public AjaxResult getCode(String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=");
        sb.append(appid);
        sb.append("&redirect_uri=");
        sb.append(url);
        sb.append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        String s = HttpUtils.sendGet(accessTokenUrl, sb.toString());
        return AjaxResult.success(s);
    }

    @Override
    public AjaxResult accessToken(String code) {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=");
        sb.append(appid);
        sb.append("&secret=");
        sb.append(secret);
        sb.append("&code=");
        sb.append(code);
        sb.append("&grant_type=authorization_code");
        String s = HttpUtils.sendGet(oauth2Url, sb.toString());
        if (s.contains("access_token")) {
            return AjaxResult.success(JSON.parseObject(JSON.toJSONString(s), WechatAccessTokenResp.class));
        } else {
            return AjaxResult.error(s);
        }
    }

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
