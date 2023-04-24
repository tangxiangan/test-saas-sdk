package com.example.demo.pojo.comPdfKit;

import lombok.Data;

import java.util.List;

@Data
public class QueryTaskInfoResult {

    /**
     * 任务id
     */
    private String taskId;
    /**
     * 任务文件个数
     */
    private Integer taskFileNum;
    /**
     * 成功个数
     */
    private String taskSuccessNum;
    /**
     * 失败个数
     */
    private String taskFailNum;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 使用资产类型
     */
    private String assetTypeId;
    /**
     * 任务费用
     */
    private String taskCost;
    /**
     * 任务持续时间
     */
    private Long taskTime;
    /**
     * 源文件格式
     */
    private String sourceType;
    /**
     * 目标文件格式
     */
    private String targetType;
    /**
     * 回调地址
     */
    private String callbackUrl;
    /**
     * 任务文件信息
     */
    private List<FileInfoDto> fileInfoDTOList;

}
