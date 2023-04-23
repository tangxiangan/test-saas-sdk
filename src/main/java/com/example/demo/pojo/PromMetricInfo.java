package com.example.demo.pojo;

import lombok.Data;

@Data
public class PromMetricInfo {
    /**
     * prometheus指标名称
     */
    private String __name__;

    /**
     * prometheus实例名称
     */
    private String instance;

    /**
     * prometheus任务名称
     */
    private String job;


    private String application;
    private String exception;

    private String method;
    private String outcome;
    private String status;
    private String url;

}
