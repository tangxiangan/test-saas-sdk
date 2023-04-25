package com.example.demo.pojo.comPdfKit;


import java.util.List;


/**
 * @author tangxiangan
 */
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
    private List<FileInfoDTO> fileInfoDTOList;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskFileNum() {
        return taskFileNum;
    }

    public void setTaskFileNum(Integer taskFileNum) {
        this.taskFileNum = taskFileNum;
    }

    public String getTaskSuccessNum() {
        return taskSuccessNum;
    }

    public void setTaskSuccessNum(String taskSuccessNum) {
        this.taskSuccessNum = taskSuccessNum;
    }

    public String getTaskFailNum() {
        return taskFailNum;
    }

    public void setTaskFailNum(String taskFailNum) {
        this.taskFailNum = taskFailNum;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getTaskCost() {
        return taskCost;
    }

    public void setTaskCost(String taskCost) {
        this.taskCost = taskCost;
    }

    public Long getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Long taskTime) {
        this.taskTime = taskTime;
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

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public List<FileInfoDTO> getFileInfoDTOList() {
        return fileInfoDTOList;
    }

    public void setFileInfoDTOList(List<FileInfoDTO> fileInfoDTOList) {
        this.fileInfoDTOList = fileInfoDTOList;
    }
}
