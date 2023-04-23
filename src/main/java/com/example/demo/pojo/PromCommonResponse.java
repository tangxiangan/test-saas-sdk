package com.example.demo.pojo;

import lombok.Data;

@Data
public class PromCommonResponse {
    /**
     * 状态
     * 成功-- success
     */
    private String status;

    /**
     * prometheus指标属性和值
     */
    private PromDataInfo data;
}
