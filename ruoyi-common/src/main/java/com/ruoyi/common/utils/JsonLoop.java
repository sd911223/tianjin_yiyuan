package com.ruoyi.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.resp.SysSignInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;

public class JsonLoop {
    private static final Logger log = LoggerFactory.getLogger(JsonLoop.class);

    public static TreeMap jsonLoop(Object object) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                Object o = entry.getValue();
                if (o instanceof String) {
                    log.info("key:{},value:{}", entry.getKey(), entry.getValue());
                    treeMap.put(entry.getKey(), entry.getValue());
                } else {
                    jsonLoop(o);
                }
            }
        }
        if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonLoop(jsonArray.get(i));
            }
        }
        return treeMap;
    }

    public static TreeMap jsonLoop(Object object, String type) {
        TreeMap<String, Object> treeMap = new TreeMap<>(new Comparator<String>() {
            /*
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            public int compare(String o1, String o2) {

                //指定排序器按照降序排列
                return o1.compareTo(o2);
            }
        });
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                Object o = entry.getValue();
                if (o instanceof String) {
                    log.info("key:{},value:{}", entry.getKey(), entry.getValue());
                    if (entry.getKey().equals("姓名")) {
                        treeMap.put("AAAAAA," + entry.getKey(), entry.getValue());
                        continue;
                    }
                    if (entry.getKey().equals("身份证号")) {
                        treeMap.put("BBBBBB," + entry.getKey(), entry.getValue());
                        continue;
                    } else {
                        treeMap.put(entry.getKey(), entry.getValue());
                    }
                } else {
                    jsonLoop(o);
                }
            }
        }
        if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonLoop(jsonArray.get(i));
            }
        }
        return treeMap;
    }


    public static List getTitle(Object object) {
        ArrayList<String> list = new ArrayList<>();
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                Object o = entry.getValue();
                if (o instanceof String) {
                    log.info("key:{},value:{}", entry.getKey(), entry.getValue());
                    list.add(entry.getKey());
                } else {
                    jsonLoop(o);
                }
            }
        }

        return list;
    }

    public static SysSignInfo getSysSignInfo(String json) throws NoSuchFieldException, IllegalAccessException {
        //解析json
        JSONObject jsonObject = JSON.parseObject(json);
        log.info("解析json:{}", jsonObject);

        TreeMap treeMap = jsonLoop(jsonObject,"");

        //获取SysSignInfo实例
        SysSignInfo sysSignInfo = new SysSignInfo();

        int i = 1;
        Iterator<String> it1 = treeMap.keySet().iterator();
        while (it1.hasNext()) {
            String key = it1.next();
            Object value = treeMap.get(key);

            System.out.println("key=" + key + " ; " + "value=" + value);
            //获取Bar的val字段
            Field field = SysSignInfo.class.getDeclaredField("expected_" + String.valueOf(i));
            //获取val字段上的Foo注解实例
            Excel excel = field.getAnnotation(Excel.class);
            //获取 foo 这个代理实例所持有的 InvocationHandler
            InvocationHandler h = Proxy.getInvocationHandler(excel);
            // 获取 AnnotationInvocationHandler 的 memberValues 字段
            Field hField = h.getClass().getDeclaredField("memberValues");
            // 因为这个字段事 private final 修饰，所以要打开权限
            hField.setAccessible(true);
            // 获取 memberValues
            Map memberValues = (Map) hField.get(h);
            // 修改 value 属性值
            memberValues.put("name", key);
            if (key.equals("AAAAAA,姓名")) {
                memberValues.put("name", key.split(",")[1]);
            }
            if (key.equals("BBBBBB,身份证号")) {
                memberValues.put("name", key.split(",")[1]);
            }
            if (i == 1) {
                sysSignInfo.setExpected_1(value.toString());
            }
            if (i == 2) {
                sysSignInfo.setExpected_2(value.toString());
            }
            if (i == 3) {
                sysSignInfo.setExpected_3(value.toString());
            }
            if (i == 4) {
                sysSignInfo.setExpected_4(value.toString());
            }
            if (i == 5) {
                sysSignInfo.setExpected_5(value.toString());
            }
            if (i == 6) {
                sysSignInfo.setExpected_6(value.toString());
            }
            if (i == 7) {
                sysSignInfo.setExpected_7(value.toString());
            }
            if (i == 8) {
                sysSignInfo.setExpected_8(value.toString());
            }
            if (i == 9) {
                sysSignInfo.setExpected_9(value.toString());
            }
            if (i == 10) {
                sysSignInfo.setExpected_10(value.toString());
            }
            if (i == 11) {
                sysSignInfo.setExpected_11(value.toString());
            }
            if (i == 12) {
                sysSignInfo.setExpected_12(value.toString());
            }
            if (i == 13) {
                sysSignInfo.setExpected_13(value.toString());
            }
            if (i == 14) {
                sysSignInfo.setExpected_14(value.toString());
            }
            if (i == 15) {
                sysSignInfo.setExpected_15(value.toString());
            }
            if (i == 16) {
                sysSignInfo.setExpected_16(value.toString());
            }
            if (i == 17) {
                sysSignInfo.setExpected_17(value.toString());
            }
            if (i == 18) {
                sysSignInfo.setExpected_18(value.toString());
            }
            if (i == 19) {
                sysSignInfo.setExpected_19(value.toString());
            }
            if (i == 20) {
                sysSignInfo.setExpected_20(value.toString());
            }
            i++;
            log.info("Excel.class,注解动态值:{}", excel.name());
        }
        return sysSignInfo;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String json = "{\"姓名\": \"杨梅\", \"身份证号\": \"610524198811285643\", \"手机号\": \"15022086467\", \"性别\": \"女\", \"考场名称\": \"天津医科大学\", \"准考证号\": \"12012136802003\", \"工作单位\": \"天津北辰北门医院\", \"健康状态\": \"黄码\"}";
        JSONObject jsonObject = JSON.parseObject(json);
        SysSignInfo sysSignInfo = getSysSignInfo("{\"姓名\": \"杨梅\", \"身份证号\": \"610524198811285643\", \"手机号\": \"15022086467\", \"性别\": \"女\", \"考场名称\": \"天津医科大学\", \"准考证号\": \"12012136802003\", \"工作单位\": \"天津北辰北门医院\", \"健康状态\": \"黄码\"}");
        TreeMap treeMap = jsonLoop(jsonObject);
        System.out.println(treeMap);
    }

}
