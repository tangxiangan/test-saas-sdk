package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PromDataInfo {

    /**
     * prometheus监控样本指标参数
     */
    private String resultType;
    private List<PromMetric> result;
}
