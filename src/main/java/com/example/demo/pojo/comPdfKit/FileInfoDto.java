package com.example.demo.pojo.comPdfKit;

import lombok.Data;

@Data
public class FileInfoDto {

    /**
     * 文件唯一标识
     */
    private String fileKey;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 源文件地址
     */
    private String fileUrl;

    /**
     * 转档下载地址
     */
    private String downloadUrl;

    /**
     * 源文件格式
     */
    private String sourceType;

    /**
     * 目标文件格式
     */
    private String targetType;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 转档后文件大小
     */
    private String convertSize;

    /**
     * 转换消耗时间
     */
    private String convertTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 失败原因编码
     */
    private String failureCode;

    /**
     * 失败原因
     */
    private String failureReason;

    /**
     * 下载后文件名
     */
    private String downFileName;

}
