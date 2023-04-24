package com.example.demo.pojo.comPdfKit;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * webhook消息队列发送消息信息
 *
 * @author SongFuQiang
 * @description saas回调参数
 */
@Data
@ToString
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
     * webhook发送地址
     */
    private String webhookUrl;
    /**
     * 事件位置名称
     */
    private String eventName;
    /**
     * webhook发送token标识
     */
    private String webhookToken;
    /**
     * 发生时间
     */
    private Date sendTime;
    /**
     * 产生本次事件的对象
     */
    private Object eventObject;

}
