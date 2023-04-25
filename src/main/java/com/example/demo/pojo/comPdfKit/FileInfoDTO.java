package com.example.demo.pojo.comPdfKit;



public class FileInfoDTO {

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


    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getConvertSize() {
        return convertSize;
    }

    public void setConvertSize(String convertSize) {
        this.convertSize = convertSize;
    }

    public String getConvertTime() {
        return convertTime;
    }

    public void setConvertTime(String convertTime) {
        this.convertTime = convertTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getDownFileName() {
        return downFileName;
    }

    public void setDownFileName(String downFileName) {
        this.downFileName = downFileName;
    }
}
