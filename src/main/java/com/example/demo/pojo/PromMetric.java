package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PromMetric {
    /**
     * metric name和描述当前样本特征的labelsets
     */
    private PromMetricInfo metric;

    /**
     * 一个float64的浮点型数据表示当前样本的值。
     */
    private String[] value;

    private  List<String[]> values;

}
