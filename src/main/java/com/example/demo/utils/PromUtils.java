package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.PromConstant;
import com.example.demo.pojo.MonitorDataVO;
import com.example.demo.pojo.PromCommonResponse;
import com.example.demo.pojo.PromDataInfo;
import com.example.demo.pojo.PromMetric;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author tangxiangan
 */
@Slf4j
@Component
public class PromUtils {
    public static PromDataInfo getDateInfo(String promURL, String type ) {
        promURL = promURL + PromConstant.QUERY ;
        log.info("请求地址：{}，请求QL：{}", promURL, type);
        Map<String,String> param = new HashMap<>();
        param.put(PromConstant.QUERY_PARAM, type);
        param.put(PromConstant.TIME, String.valueOf(System.currentTimeMillis()/1000));
        return sendRequest(promURL, type, param);
    }

    public static PromDataInfo getDiskInfo(String promURL) {
        promURL = promURL + PromConstant.QUERY_URL + "?query=" +PromConstant.PROCESS_DISK_COUNT_URL + "&time=" + String.valueOf(System.currentTimeMillis()/1000);
        log.info("请求地址：{}，请求QL：{}", promURL);
        return sendRequest(promURL, null, null);
    }

    private static void handleEncodeQuery(String promQL, JSONObject param) {
        try {
            param.put(PromConstant.QUERY_PARAM, URLEncoder.encode(promQL,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    private static PromDataInfo sendRequest(String promURL, String type, Map<String,String> param) {
        JSONObject http = null;
        try {
            http = RestTemplateUtils.getHttp(promURL, param);
        } catch (Exception e) {
            log.error("请求地址：{}，请求QL：{}，异常信息：{}", promURL, type, e);
        }
        PromCommonResponse responceInfo = http.toJavaObject(PromCommonResponse.class);
        log.info("请求地址：{}，请求QL：{}，返回信息：{}", promURL, type, responceInfo);
        if (Objects.isNull(responceInfo)) {
            return null;
        }
        String status = responceInfo.getStatus();
        if (StringUtils.isEmpty(status)
                || !PromConstant.SUCCESS.equals(status)
        ) {
            return null;
        }
        PromDataInfo data = responceInfo.getData();

        return data;
    }

    public static PromDataInfo getRangeDateInfo(String promURL, String promQL, Integer hoursRange) {
        promURL = promURL+PromConstant.QUERY_RANGE;
        log.info("请求地址：{}，请求QL：{}", promURL, promQL);
        Map<String,String> param = new HashMap<>();
        param.put(PromConstant.QUERY_PARAM, promQL);
        if(ObjectUtils.isEmpty(hoursRange)){
            hoursRange = 1;
        }
        Date endDate = new Date();
        Date startDate = DateUtils.handleHours(endDate,hoursRange,-1);
        param.put(PromConstant.START, String.valueOf(startDate.getTime()/1000));
        param.put(PromConstant.END, String.valueOf(endDate.getTime()/1000));
        param.put(PromConstant.STEP, String.valueOf(hoursRange*14));
        return sendRequest(promURL, promQL, param);
    }


    public static List<MonitorDataVO> getAllInfo() {
        PromDataInfo cpuInfo =  PromUtils.getDateInfo("http://124.223.7.184:9090",PromConstant.PROCESS_CPU_COUNT);
        PromDataInfo diskInfo = PromUtils.getDiskInfo("http://124.223.7.184:9090");
        PromDataInfo memoryInfo = PromUtils.getDateInfo("http://124.223.7.184:9090",PromConstant.PROCESS_MEMORY_COUNT);
        Map<String, MonitorDataVO> hostMap = new HashMap<>();
        MonitorDataVO monitorDataVO;
        for (PromMetric promMetric : cpuInfo.getResult()) {
            if (!hostMap.containsKey(promMetric.getMetric().getInstance())) {
                monitorDataVO = new MonitorDataVO();
                monitorDataVO.setHost(promMetric.getMetric().getInstance());
                hostMap.put(promMetric.getMetric().getInstance(),monitorDataVO);
            } else {
                monitorDataVO = hostMap.get(promMetric.getMetric().getInstance());
            }
            monitorDataVO.setCpu(promMetric.getValue()[1]);
        }
        for (PromMetric promMetric : diskInfo.getResult()) {
            if (!hostMap.containsKey(promMetric.getMetric().getInstance())) {
                monitorDataVO = new MonitorDataVO();
                monitorDataVO.setHost(promMetric.getMetric().getInstance());
                hostMap.put(promMetric.getMetric().getInstance(),monitorDataVO);
            } else {
                monitorDataVO = hostMap.get(promMetric.getMetric().getInstance());
            }
            monitorDataVO.setDisk(promMetric.getValue()[1]);
        }
        for (PromMetric promMetric : memoryInfo.getResult()) {
            if (!hostMap.containsKey(promMetric.getMetric().getInstance())) {
                monitorDataVO = new MonitorDataVO();
                monitorDataVO.setHost(promMetric.getMetric().getInstance());
                hostMap.put(promMetric.getMetric().getInstance(),monitorDataVO);
            } else {
                monitorDataVO = hostMap.get(promMetric.getMetric().getInstance());
            }
            monitorDataVO.setMemory(promMetric.getValue()[1]);
        }
        return new ArrayList<>(hostMap.values());
    }



}
