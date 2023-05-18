package io.github.tangxiangan.pojo.comPdfKit;


import java.util.List;


/**
 * @author tangxiangan
 */
public class QueryTaskInfoResult {

    /**
     * taskId
     */
    private String taskId;
    /**
     * taskFileNum
     */
    private Integer taskFileNum;
    /**
     * taskSuccessNum
     */
    private String taskSuccessNum;
    /**
     * taskFailNum
     */
    private String taskFailNum;
    /**
     * taskStatus
     */
    private String taskStatus;
    /**
     * assetTypeId
     */
    private String assetTypeId;
    /**
     * taskCost
     */
    private String taskCost;
    /**
     * taskTime
     */
    private Long taskTime;
    /**
     * sourceType
     */
    private String sourceType;
    /**
     * targetType
     */
    private String targetType;
    /**
     * callbackUrl
     */
    private String callbackUrl;
    /**
     * fileInfoDTOList
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
