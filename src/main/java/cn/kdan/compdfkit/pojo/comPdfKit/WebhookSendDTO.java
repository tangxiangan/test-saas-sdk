package cn.kdan.compdfkit.pojo.comPdfKit;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author tangxiangan
 */

public class WebhookSendDTO implements Serializable {
    /**
     * tenantID
     */
    private Long tenantId;
    /**
     * projectID
     */
    private Long projectId;
    /**
     * webhookUrl
     */
    private String webhookUrl;
    /**
     * eventName
     */
    private String eventName;
    /**
     * webhookToken
     */
    private String webhookToken;
    /**
     * sendTime
     */
    private Date sendTime;
    /**
     * eventObject
     */
    private Object eventObject;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getWebhookToken() {
        return webhookToken;
    }

    public void setWebhookToken(String webhookToken) {
        this.webhookToken = webhookToken;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Object getEventObject() {
        return eventObject;
    }

    public void setEventObject(Object eventObject) {
        this.eventObject = eventObject;
    }

    @Override
    public String toString() {
        return "WebhookSendDTO{" +
                "tenantId=" + tenantId +
                ", projectId=" + projectId +
                ", webhookUrl='" + webhookUrl + '\'' +
                ", eventName='" + eventName + '\'' +
                ", webhookToken='" + webhookToken + '\'' +
                ", sendTime=" + sendTime +
                ", eventObject=" + eventObject +
                '}';
    }
}