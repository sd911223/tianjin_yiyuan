package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.BusinessReserve;
import com.ruoyi.common.core.domain.entity.BusinessReserveContent;
import com.ruoyi.common.core.domain.entity.BusinessReservePersonnel;
import com.ruoyi.common.core.domain.entity.req.ReserveCancelReq;
import com.ruoyi.common.core.domain.entity.req.WxMpTemplateMessage;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.mapper.SysReserveContentMapper;
import com.ruoyi.system.mapper.SysReserveMapper;
import com.ruoyi.system.mapper.SysReservePersonnelMapper;
import com.ruoyi.system.service.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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
    private static final Logger log = LoggerFactory.getLogger(WechatServiceImpl.class);
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
    @Value("${wechat.template_id}")
    private String templateId;
    @Value("${wechat.template_url}")
    private String templateUrl;
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
            Date appointmentDate = businessReservePersonnel1.getAppointmentDate();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate);
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String appointmentPeriod = businessReservePersonnel1.getAppointmentPeriod();
            String[] split = appointmentPeriod.split("-");
            String noonTime = currentDate + " " + "12:00:00";
            String nightTime = currentDate + " " + "23:59:59";
            Date night = DateUtils.parseDate(nightTime);
            Date noon = DateUtils.parseDate(noonTime);
            String startTime = date + " " + split[0];
            Date parseDateStart = DateUtils.parseDate(startTime);
            if (businessReservePersonnel1.getStatus().equals("1")) {
                log.info("进入已签到人员再次签到,签到人员{}", businessReservePersonnel1.getName());
                businessReservePersonnel1.setStatus("1");
                businessReservePersonnel1.setReserveNumber(orderSn);
                businessReservePersonnel1.setSignTime(new Date());
                sysReservePersonnelMapper.updatePersonnelStatus(businessReservePersonnel1);
                return AjaxResult.success("签到成功!签到码为: " + orderSn);
            }
            if (!currentDate.equals(date)) {
                log.info("签到时间不在同一天,签到时间{},当前时间{}", date, currentDate);
                return AjaxResult.error("不在签到时间内");
            }
            if (new Date().getTime() - parseDateStart.getTime() < 1) {
                return AjaxResult.error("不在签到时间内");
            }
            if (Double.valueOf(split[1]) <= 12) {
                log.info("进入12点以前签到,签到人员{}", businessReservePersonnel1.getName());
                if (noon.getTime() - new Date().getTime() > 1) {
                    businessReservePersonnel1.setStatus("1");
                    businessReservePersonnel1.setReserveNumber(orderSn);
                    businessReservePersonnel1.setSignTime(new Date());
                    sysReservePersonnelMapper.updatePersonnelStatus(businessReservePersonnel1);
                    return AjaxResult.success("签到成功!签到码为: " + orderSn);
                } else {
                    return AjaxResult.error("不在签到时间内");
                }
            }
            if (Double.valueOf(split[1]) <= 24) {
                log.info("进入12点以后签到,签到人员{}", businessReservePersonnel1.getName());
                if (night.getTime() - new Date().getTime() > 1) {
                    businessReservePersonnel1.setStatus("1");
                    businessReservePersonnel1.setReserveNumber(orderSn);
                    businessReservePersonnel1.setSignTime(new Date());
                    sysReservePersonnelMapper.updatePersonnelStatus(businessReservePersonnel1);
                    return AjaxResult.success("签到成功!签到码为: " + orderSn);
                } else {
                    return AjaxResult.error("不在签到时间内");
                }
            }
        }
        return AjaxResult.success("签到成功!签到码为: " + orderSn);
    }

    @Override
    @Transactional
    public synchronized AjaxResult insertPersonnel(BusinessReservePersonnel businessReservePersonnel) {
        try {
            Date appointmentDate = businessReservePersonnel.getAppointmentDate();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate);
            String appointmentPeriod = businessReservePersonnel.getAppointmentPeriod();
            BusinessReservePersonnel personnel = new BusinessReservePersonnel();
            personnel.setIdCard(businessReservePersonnel.getIdCard());
            personnel.setReserveId(businessReservePersonnel.getReserveId());
            personnel.setCanceType("0");
            List<BusinessReservePersonnel> businessReservePersonnels = sysReservePersonnelMapper.selectPersonneList(personnel);
            if (!businessReservePersonnels.isEmpty()) {
                return AjaxResult.error("你已预约'" + DateUtils.parseDateToStr("yyyy-MM-dd", businessReservePersonnel.getAppointmentDate()) + " " + businessReservePersonnel.getAppointmentPeriod() + "时间内的项目");
            }
            String[] split = appointmentPeriod.split("-");
//        String startTime = date + " " + split[0];
            String endTime = date + " " + split[1];
//        Date parseDateStart = DateUtils.parseDate(startTime);
            Date parseDateEnd = DateUtils.parseDate(endTime);
//                if (new Date().getTime() - parseDateStart.getTime() < 1) {
//                    return AjaxResult.error("不在预约时间内");
//                }
            if (new Date().getTime() - parseDateEnd.getTime() > 0) {
                return AjaxResult.error("不在预约时间内");
            }
            BusinessReserve businessReserve = sysReserveMapper.selectReserveById(businessReservePersonnel.getReserveId());

            if (businessReserve == null) {
                return AjaxResult.error("我要预约-预约号'" + businessReservePersonnel.getReserveId() + "'失败，预约项目不存在");
            }
            if ("1".equals(businessReserve.getReserveType())) {
                String idCard = businessReserve.getIdCard();
                if (!idCard.contains(businessReservePersonnel.getIdCard())) {
                    return AjaxResult.error("我要预约-预约号'" + businessReservePersonnel.getReserveName() + "'失败，不在特定人群");
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
            sendTemplate(businessReservePersonnel);
            return AjaxResult.success("预约成功!");
        } catch (Exception e) {
            log.info("预约失败{}", e);
        }
        return AjaxResult.error("预约失败!");
    }

    @Override
    public List<BusinessReservePersonnel> myReserve(String openId) {
        BusinessReservePersonnel businessReservePersonnel = new BusinessReservePersonnel();
        businessReservePersonnel.setOpenId(openId);
        return sysReservePersonnelMapper.selectPersonneList(businessReservePersonnel);
    }


    @Override
    @Transactional
    public synchronized AjaxResult reserveCancel(ReserveCancelReq reserveCancelReq) {
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
        BusinessReserve businessReserve = sysReserveMapper.selectReserveById(reserveCancelReq.getReserveId());
        if (businessReserve.getReserveNum() < 1) {
            throw new BaseException("取消预约失败!");
        }
        businessReserve.setReserveNum(businessReserve.getReserveNum() - 1);
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


    public String getCacheToken() {
        Object object = redisCache.getCacheObject(Constants.ACCESS_TOKEN);
        if (object != null) {
            return object.toString();
        } else {
            return getaccToken();
        }
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

    /**
     * 发送模板
     *
     * @param businessReservePersonnel
     */
    @Async
    public void sendTemplate(BusinessReservePersonnel businessReservePersonnel) {
        String accToken = getCacheToken();
        log.info("获取：getAccessToken()->{}", accToken);
        TreeMap<String, TreeMap<String, String>> params = new TreeMap<String, TreeMap<String, String>>();
        //3,发送消息，，这里需要配置你的信息
        params.put("first", WxMpTemplateMessage.item("尊敬的" + businessReservePersonnel.getName() + ",您已经成功预约", "#173177"));
        params.put("keyword1", WxMpTemplateMessage.item(businessReservePersonnel.getReserveName(), "#173177"));
        params.put("keyword2", WxMpTemplateMessage.item("现场审核确认", "#173177"));
        Date appointmentDate = businessReservePersonnel.getAppointmentDate();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(appointmentDate);
        String keyword3 = date + " " + businessReservePersonnel.getAppointmentPeriod();
        params.put("keyword3", WxMpTemplateMessage.item("请于" + keyword3 + "办理", "#173177"));
        params.put("keyword4", WxMpTemplateMessage.item(businessReservePersonnel.getName(), "#173177"));
        params.put("keyword5", WxMpTemplateMessage.item("如需取消,请在【我的预约】中取消预约", "#173177"));
        params.put("remark", WxMpTemplateMessage.item("感谢您的使用", "#173177"));
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplate_id(templateId);
        wxMpTemplateMessage.setTouser(businessReservePersonnel.getOpenId());
        wxMpTemplateMessage.setUrl("");
        wxMpTemplateMessage.setData(params);
        //将java对象转换为json对象
        String sendData = JSONObject.toJSONString(wxMpTemplateMessage);
        log.info("模板板参数组装{}", sendData);
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
//        treeMap.put("access_token", businessReservePersonnel.getAccessToken());
        treeMap.put("access_token", accToken);
        String retInfo = HttpUtils.doPost(templateUrl, treeMap, sendData);
        log.info("消息模板返回{}", retInfo);
    }

}
