package com.kdan.compdfkit.client;


import com.kdan.compdfkit.constant.ComPdfKitConstant;
import com.kdan.compdfkit.constant.CommonConstant;
import com.kdan.compdfkit.exception.BackendRuntimeException;
import com.example.demo.pojo.comPdfKit.*;
import com.kdan.compdfkit.pojo.comPdfKit.*;
import com.kdan.compdfkit.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.time.Duration.*;

/**
 * @author txa 2023/1/16
 * <p>
 * PaddleClient
 */


public class ComPdfKitClient {

    private Logger log = LoggerFactory.getLogger(ComPdfKitClient.class);

    private final String publicKey;

    private final String secretKey;

    private static String accessToken;

    private static long expireTime;

    /**
     * 地址
     */
    private final String address;
    private final RestTemplate restTemplate;


    public void setAccessToken(String token, long expiresIn) {
        accessToken = token;
        expireTime = System.currentTimeMillis() + expiresIn * 1000L;
    }

    public String getAccessToken() {
        if (ObjectUtils.isEmpty(expireTime) || System.currentTimeMillis() > expireTime) {
            refreshAccessToken();
        }
        return accessToken;
    }

    private void refreshAccessToken() {
        // 调用 API 刷新 Token
        ComPdfKitOauthResult newToken = getComPdfKitAuth(this.publicKey,this.secretKey);
        setAccessToken(newToken.getAccessToken(), Long.parseLong(newToken.getExpiresIn()));

    }



    private ComPdfKitClient(String publicKey, String secretKey, Duration readTimeout, Duration connectTimeout) {
        this.address = "http://101.132.103.13:8090/server/";
        this.publicKey = publicKey;
        this.secretKey = secretKey;
        this.restTemplate = new RestTemplateBuilder()
                .setReadTimeout(readTimeout)
                .setConnectTimeout(connectTimeout)
                .setBufferRequestBody(false)
                .build();
        refreshAccessToken();
    }

    public ComPdfKitClient(String publicKey, String secretKey) {
        this.address = "http://101.132.103.13:8090/server/";
        this.publicKey = publicKey;
        this.secretKey = secretKey;
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.setReadTimeout(ofSeconds(60 * 5));
        restTemplateBuilder.setConnectTimeout(ofSeconds(60 * 5));
        restTemplateBuilder.setBufferRequestBody(false);
        this.restTemplate = restTemplateBuilder
                .build();
        refreshAccessToken();
    }



    /**
     * 获取token
     *
     * @param publicKey 项目key
     * @param secretKey  秘钥
     * @return String
     */
    private ComPdfKitOauthResult getComPdfKitAuth(String publicKey, String secretKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> tokenParam = new HashMap<>();
        tokenParam.put("publicKey", publicKey);
        tokenParam.put("secretKey", secretKey);
        ResponseEntity<ComPdfKitResult<ComPdfKitOauthResult>> responseEntity;
        ParameterizedTypeReference<ComPdfKitResult<ComPdfKitOauthResult>> typeRef = new ParameterizedTypeReference<ComPdfKitResult<ComPdfKitOauthResult>>() {};
        try {
            responseEntity = restTemplate.exchange(
                    address.concat(ComPdfKitConstant.API_V1_OAUTH_TOKEN),
                    HttpMethod.POST,
                    new HttpEntity<>(JsonUtils.getJsonString(tokenParam), headers),
                    typeRef
            );
        } catch (Exception e) {
            log.error(ComPdfKitConstant.EXCEPTION_MSG_GET_TOKEN_FAIL + "{}", e.getMessage());
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_GET_TOKEN_FAIL);
        }
        if (responseEntity.getStatusCode() != HttpStatus.OK || ObjectUtils.isEmpty(responseEntity.getBody())) {
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_GET_TOKEN_FAIL);
        }
        // 设置过期
        // redisUtils.set(ComPDFKitConstant.COM_PDF_Kit_TOKEN, token, responseEntity.getBody().get(ComPDFKitConstant.EXPIRES_IN).asLong()-20L);
        return responseEntity.getBody().getData();
    }



    private HttpHeaders basicHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.getAccessToken());
        return headers;
    }



    /**
     * 创建任务
     *
     * @param executeTypeUrl 任务执行类型
     * @return CreateTaskResult
     */
    public CreateTaskResult createTask(String executeTypeUrl) {
        String url = address.concat(ComPdfKitConstant.API_V1_CREATE_TASK).replace("{executeTypeUrl}", executeTypeUrl);
        ResponseEntity<ComPdfKitResult<CreateTaskResult>> response;
        ParameterizedTypeReference<ComPdfKitResult<CreateTaskResult>> typeRef = new ParameterizedTypeReference<ComPdfKitResult<CreateTaskResult>>() {};
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(basicHeaders()),
                    typeRef
            );
        } catch (Exception e) {
            log.error(ComPdfKitConstant.EXCEPTION_MSG_CREATE_TASK_FAIL + "{}", e.getMessage());
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_CREATE_TASK_FAIL + e.getMessage());
        }
        if (response.getStatusCode() != HttpStatus.OK || ObjectUtils.isEmpty(response.getBody() )){
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_CREATE_TASK_FAIL);
        }
        return response.getBody().getData();
    }

    /**
     * 上传文件
     *
     * @param file     文件
     * @param taskId   任务id
     * @param password 密码
     * @return UploadFileResult
     */
    public UploadFileResult uploadFile(File file, String taskId, String password)  {
        log.info("开始上传文件，taskId：{},password:{}",taskId,password);
        String url = address.concat(ComPdfKitConstant.API_V1_UPLOAD_FILE);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();;
        FileSystemResource fs = new FileSystemResource(file);
        param.add("file", fs);
        param.add("taskId", taskId);
        param.add("password", password);
        HttpHeaders headers = basicHeaders() ;
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        ResponseEntity<ComPdfKitResult<UploadFileResult>> response = null;
        ParameterizedTypeReference<ComPdfKitResult<UploadFileResult>> typeRef = new ParameterizedTypeReference<ComPdfKitResult<UploadFileResult>>() {};
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(param, headers),
                    typeRef
            );
        } catch (Exception e) {
            log.error(ComPdfKitConstant.EXCEPTION_MSG_UPLOAD_FILE_FAIL + "{}", e.getMessage());
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_UPLOAD_FILE_FAIL + e.getMessage());
        }finally {
            try {
                if(file.exists()){
                    boolean delete = file.delete();
                }
            }catch (Exception e){
                log.error("删除文件失败；{}",e.getMessage());
            }
        }

        if (response.getStatusCode() != HttpStatus.OK || ObjectUtils.isEmpty(response.getBody()) || !CommonConstant.SUCCESS_CODE.equals(response.getBody().getCode()) || ObjectUtils.isEmpty(response.getBody())) {
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_UPLOAD_FILE_FAIL+ Objects.requireNonNull(response.getBody()).getMsg());
        }
        return response.getBody().getData();
    }

    /**
     * 执行任务转档
     *
     * @param taskId 任务id
     * @return 任务id
     */
    public CreateTaskResult executeTask(String taskId){
        log.info("开始执行任务转档，taskId：{}",taskId);
        String url = address.concat(ComPdfKitConstant.API_V1_EXECUTE_TASK).concat("?taskId=").concat(taskId);
        ResponseEntity<ComPdfKitResult<CreateTaskResult>> response;
        ParameterizedTypeReference<ComPdfKitResult<CreateTaskResult>> result = new ParameterizedTypeReference<ComPdfKitResult<CreateTaskResult>>(){};
        try{
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(JsonUtils.getJsonString(taskId),basicHeaders()),
                    result
            );
        }catch (Exception e){
            log.error(ComPdfKitConstant.EXCEPTION_MSG_EXECUTE_TASK_FAIL + "{}", e.getMessage());
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_EXECUTE_TASK_FAIL + e.getMessage());
        }
        if(response.getStatusCode() != HttpStatus.OK || ObjectUtils.isEmpty(response.getBody()) || !CommonConstant.SUCCESS_CODE.equals(response.getBody().getCode()) || ObjectUtils.isEmpty(response.getBody())){
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_EXECUTE_TASK_FAIL+response.getBody());
        }
        return response.getBody().getData();
    }

    /**
     * 查询任务文件状态
     *
     * @param taskId 任务id
     * @return QueryTaskInfoResult
     */
    public QueryTaskInfoResult queryTaskInfo(String taskId){
        log.info("开始查询转档状态，taskId：{}",taskId);
        String url = address.concat(ComPdfKitConstant.API_V1_TASK_INFO).concat("?taskId=").concat(taskId);
        ResponseEntity<ComPdfKitResult<QueryTaskInfoResult>> response;
        ParameterizedTypeReference<ComPdfKitResult<QueryTaskInfoResult>> result = new ParameterizedTypeReference<ComPdfKitResult<QueryTaskInfoResult>>(){};
        try{
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(JsonUtils.getJsonString(taskId),basicHeaders()),
                    result
            );
        }catch (Exception e){
            log.error(ComPdfKitConstant.EXCEPTION_MSG_TASK_INFO_FAIL + "{}", e.getMessage());
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_TASK_INFO_FAIL + e.getMessage());
        }
        if(response.getStatusCode() != HttpStatus.OK || ObjectUtils.isEmpty(response.getBody()) || !CommonConstant.SUCCESS_CODE.equals(response.getBody().getCode()) || ObjectUtils.isEmpty(response.getBody().getData())){
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_TASK_INFO_FAIL+response.getBody());
        }
        log.info("查询状态成功：{}",JsonUtils.getJsonString(response.getBody().getData()));
        return response.getBody().getData();
    }

}
