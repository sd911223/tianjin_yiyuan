package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.req.ReserveCancelReq;
import com.ruoyi.common.core.domain.entity.resp.WechatAccessTokenResp;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.mapper.SysReserveContentMapper;
import com.ruoyi.system.mapper.SysReserveMapper;
import com.ruoyi.system.mapper.SysReservePersonnelMapper;
import com.ruoyi.system.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Value("${wechat.secret}")
    private String secret;

    @Value("${wechat.base_access_token_url}")
    private String baseAccessTokenUrl;
    @Value("${wechat.oauth2_url}")
    private String oauth2Url;
    @Value("${wechat.access_token_url}")
    private String accessTokenUrl;
    @Value("${wechat.ticket_url}")
    private String ticketUrl;

    @Autowired
    private RedisCache redisCache;

    public AjaxResult getAccessToken() {
        Object object = redisCache.getCacheObject(Constants.ACCESS_TOKEN);
        if (object != null) {
            return AjaxResult.success(object);
        }
        String getaccToken = getaccToken();
        if ("".equals(getaccToken)) {
            return AjaxResult.error("获取access_token失败");
        }
        return AjaxResult.success(getaccToken);
    }

    @Override
    public AjaxResult getCode(String url) {
        if (!StringUtils.isEmpty(url)) {
            return AjaxResult.error("url不能为空");
        }
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
        String s = HttpUtils.sendGet(accessTokenUrl, sb.toString());
        if (s.contains("access_token")) {
            return AjaxResult.success(s);
        } else {
            return AjaxResult.error(s);
        }
    }

    @Override
    public AjaxResult sign(ReserveCancelReq reserveCancelReq) {
        BusinessReservePersonnel businessReservePersonnel = new BusinessReservePersonnel();
        businessReservePersonnel.setOpenId(reserveCancelReq.getOpenId());
        businessReservePersonnel.setReserveId(reserveCancelReq.getReserveId());
        businessReservePersonnel.setContentId(reserveCancelReq.getContentId());
        String orderSn = OrderSn(reserveCancelReq.getContentId());
        List<BusinessReservePersonnel> list = sysReservePersonnelMapper.selectPersonneList(businessReservePersonnel);
        if (!list.isEmpty()) {
            BusinessReservePersonnel businessReservePersonnel1 = list.get(0);
            if (businessReservePersonnel1.getStatus().equals("1")) {
                return AjaxResult.error("不可重复签到!");
            }
            businessReservePersonnel1.setStatus("1");
            businessReservePersonnel1.setReserveNumber(orderSn);
            businessReservePersonnel1.setSignTime(new Date());
            sysReservePersonnelMapper.updatePersonnelStatus(businessReservePersonnel1);
        }
        return AjaxResult.success("签到成功!签到码为: " + orderSn);
    }


    @Transactional
    @Override
    public AjaxResult insertPersonnel(BusinessReservePersonnel businessReservePersonnel) {
        Date appointmentDate = businessReservePersonnel.getAppointmentDate();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate);
        String appointmentPeriod = businessReservePersonnel.getAppointmentPeriod();
        String[] split = appointmentPeriod.split("-");
        String startTime = date + " " + split[0];
        String endTime = date + " " + split[1];
        Date parseDateStart = DateUtils.parseDate(startTime);
        Date parseDateEnd = DateUtils.parseDate(endTime);
        if (new Date().getTime()-parseDateStart.getTime()<1){
            return AjaxResult.error("不在预约时间内");
        }
        if (new Date().getTime()-parseDateEnd.getTime()>1){
            return AjaxResult.error("不在预约时间内");
        }
        BusinessReserve businessReserve = sysReserveMapper.selectReserveById(businessReservePersonnel.getReserveId());
        try {
            Thread.sleep(2000);
            synchronized (this) {
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
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        list.get(0).setCanceType("1");
        sysReservePersonnelMapper.updatePersonnelCanceType(list.get(0));

        BusinessReserveContent businessReserveContent = sysReserveContentMapper.selectContentById(reserveCancelReq.getContentId());

        businessReserveContent.setSurplusNumber(businessReserveContent.getSurplusNumber() + 1);
        sysReserveContentMapper.updateSurplusNumber(businessReserveContent);
        BusinessReserve businessReserve = new BusinessReserve();
        businessReserve.setReserveNum(businessReserve.getReserveNum() == null ? 0 : businessReserve.getReserveNum() - 1);
        sysReserveMapper.updateReserveNum(businessReserve);
        return AjaxResult.success("取消预约成功!");
    }

    @Override
    public AjaxResult scan(String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String accessToken = "";
        Object object = redisCache.getCacheObject(Constants.ACCESS_TOKEN);
        if (object != null) {
            accessToken = object.toString();
        } else {
            String getaccToken = getaccToken();
            accessToken = getaccToken;
        }
        String ticket = "";
        Object jsapiTicket = redisCache.getCacheObject(Constants.JSAPI_TICKET);
        if (jsapiTicket != null) {
            ticket = jsapiTicket.toString();
        } else {
            String getTicketn = getTicket();
            ticket = getTicketn;
        }
        String timestamp = create_timestamp();
        String nonce_str = create_nonce_str();
        String string1;
        String signature = "";
        string1 = "jsapi_ticket=" + ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("string1=" + string1);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ret.put("url", url);
        ret.put("jsapi_ticket", ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", appid);
        return AjaxResult.success(ret);
    }


    /**
     * 随机加密
     *
     * @param hash
     * @return
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 产生随机串--由程序自己随机产生
     *
     * @return
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 由程序自己获取当前时间
     *
     * @return
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public String getaccToken() {
        StringBuffer sb = new StringBuffer();
        sb.append("grant_type=client_credential&");
        sb.append("appid=");
        sb.append(appid);
        sb.append("&secret=");
        sb.append(secret);
        String s = HttpUtils.sendGet(baseAccessTokenUrl, sb.toString());
        JSONObject jsonObject = JSONObject.parseObject(s);
        if (!"".equals(jsonObject.get("access_token"))) {
            redisCache.setCacheObject(Constants.ACCESS_TOKEN, jsonObject.get("access_token"), 7200, TimeUnit.SECONDS);
            return jsonObject.get("access_token").toString();
        }
        return "";
    }

    public String getTicket() {
        String accessToken = "";
        Object object = redisCache.getCacheObject(Constants.ACCESS_TOKEN);
        if (object != null) {
            accessToken = object.toString();
        } else {
            String getaccToken = getaccToken();
            accessToken = getaccToken;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("access_token=");
        stringBuffer.append(accessToken);
        stringBuffer.append("&type=jsapi");
        String ticke = HttpUtils.sendGet(ticketUrl, stringBuffer.toString());
        JSONObject jsonObject = JSONObject.parseObject(ticke);
        String ticket = jsonObject.get("ticket").toString();
        if (!"".equals(ticket)) {
            redisCache.setCacheObject(Constants.JSAPI_TICKET, ticket, 7200, TimeUnit.SECONDS);
            return ticket;
        }
        return "";
    }

    /**
     * 生成签到号
     *
     * @param rId
     * @return
     */
    private String OrderSn(Integer rId) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = rId + date;
        //只要key相同，会一直加1，如果key变了从1开始
        Long increment = redisCache.increment(key, 1);
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 4) {//不足3位补0
            sb.append(String.format("%04d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }

}
