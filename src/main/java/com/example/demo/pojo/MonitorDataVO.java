package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author tangxiangan
 */
@Data
public class MonitorDataVO {

    private String host;
    private String hostName;
    private String cpu;
    private String disk;
    private String memory;
    private String requestCount;
}
