package com.example.demo.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ComPDFKit-WPH 2023/1/16
 * <p>
 * PaypalProperties
 */
@ConfigurationProperties(prefix = "client.paddle")
@Data
@ToString
public class PaddleProperties {
    /**
     * 结账接口使用host
     */
    private String checkoutHost;

    /**
     * 产品、订阅、警报接口使用host
     */
    private String vendorsHost;

    /**
     * vendorId
     */
    private Integer vendorId;
    /**
     * vendorAuthCode
     */
    private String vendorAuthCode;

    private Integer readTimeOut;
    /**
     * //单位为ms
     */
    private Integer connectTimeout;

    private String publicKey;

}
