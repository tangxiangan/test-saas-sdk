package com.example.demo.controller;

import com.example.demo.constant.PromConstant;
import com.example.demo.pojo.MonitorDataVO;
import com.example.demo.pojo.PromDataInfo;
import com.example.demo.utils.PromUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author tangxiangan
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public PromDataInfo pay() {
        return PromUtils.getDateInfo("http://124.223.7.184:9090",PromConstant.PROCESS_DISK_COUNT);
    }

    @GetMapping("/get111")
    public PromDataInfo get111() {
        return PromUtils.getDiskInfo("http://124.223.7.184:9090");
    }

    @GetMapping("/getRange")
    public PromDataInfo getRange() {
        return PromUtils.getRangeDateInfo("http://124.223.7.184:9090",PromConstant.PROCESS_CPU_COUNT,1);
    }

    @GetMapping("/getAllInfo")
    public List<MonitorDataVO> getAllInfo() {
        return PromUtils.getAllInfo();
    }



}
