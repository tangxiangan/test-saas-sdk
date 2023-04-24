package com.example.demo.pojo.comPdfKit;

import lombok.Data;

/**
 * @author tangxiangan
 */
@Data
public class ComPdfKitOauthResult {
    private String expiresIn;
    private String scope;
    private String tenantId;
    private String accessToken;
    private String tokenType;
    private String projectName;
    private String projectId;
    private String refreshToken;
}
