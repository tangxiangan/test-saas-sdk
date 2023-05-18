package io.github.tangxiangan.client;


import com.alibaba.fastjson.JSON;
import io.github.tangxiangan.constant.ComPdfKitConstant;
import io.github.tangxiangan.constant.CommonConstant;
import io.github.tangxiangan.exception.BackendRuntimeException;
import io.github.tangxiangan.param.FileParameter;
import io.github.tangxiangan.pojo.comPdfKit.*;
import io.github.tangxiangan.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.time.Duration.ofSeconds;

/**
 * @author txa 2023/1/16
 * ComPdfKitClient
 */


public class ComPdfKitClient {

    private final Logger log = LoggerFactory.getLogger(ComPdfKitClient.class);

    private final String publicKey;

    private final String secretKey;

    private static String accessToken;

    private static long expireTime;

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
        // Call the API to refresh the token
        ComPdfKitOauthResult newToken = getComPdfKitAuth(this.publicKey,this.secretKey);
        setAccessToken(newToken.getAccessToken(), Long.parseLong(newToken.getExpiresIn()));
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
     * get token
     *
     * @param publicKey publicKey
     * @param secretKey  secretKey
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
        return responseEntity.getBody().getData();
    }



    private HttpHeaders basicHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.getAccessToken());
        return headers;
    }



    /**
     * createTask
     *
     * @param executeTypeUrl task execution type
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
     * uploadFile
     *
     * @param file     file
     * @param taskId   taskId
     * @param password password
     * @return UploadFileResult
     */
    public UploadFileResult uploadFile(File file, String taskId, String password) {
        return getUploadFileResult(file, taskId, password, null);
    }

    /**
     * uploadFile
     *
     * @param file   file
     * @param taskId taskId
     * @return UploadFileResult
     */
    public UploadFileResult uploadFile(File file, String taskId) {
        return getUploadFileResult(file, taskId, null, null);
    }

    /**
     * uploadFile
     *
     * @param file          file
     * @param taskId        taskId
     * @param password      password
     * @param fileParameter fileParameter
     * @return UploadFileResult
     */
    public UploadFileResult uploadFile(File file, String taskId, String password, FileParameter fileParameter) {
        return getUploadFileResult(file, taskId, password, fileParameter);
    }

    /**
     * uploadFile
     *
     * @param file          file
     * @param taskId        taskId
     * @param fileParameter fileParameter
     * @return UploadFileResult
     */
    public UploadFileResult uploadFile(File file, String taskId, FileParameter fileParameter) {
        return getUploadFileResult(file, taskId, null, fileParameter);
    }


    private UploadFileResult getUploadFileResult(File file, String taskId, String password, FileParameter fileParameter) {
        log.info("Start uploading files, task Id: {}, password: {}", taskId, password);
        String url = address.concat(ComPdfKitConstant.API_V1_UPLOAD_FILE);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        FileSystemResource fs = new FileSystemResource(file);
        param.add("file", fs);
        param.add("taskId", taskId);
        if (!StringUtils.isEmpty(password)) {
            param.add("password", password);
        }
        if (!ObjectUtils.isEmpty(fileParameter)) {
            param.add("parameter", JSON.toJSONString(fileParameter));
        }
        HttpHeaders headers = basicHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        ResponseEntity<ComPdfKitResult<UploadFileResult>> response = null;
        ParameterizedTypeReference<ComPdfKitResult<UploadFileResult>> typeRef = new ParameterizedTypeReference<ComPdfKitResult<UploadFileResult>>() {
        };
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
                log.error("Failed to delete file; {}",e.getMessage());
            }
        }
        if (response.getStatusCode() != HttpStatus.OK || ObjectUtils.isEmpty(response.getBody()) || !CommonConstant.SUCCESS_CODE.equals(response.getBody().getCode()) || ObjectUtils.isEmpty(response.getBody())) {
            throw new BackendRuntimeException(ComPdfKitConstant.EXCEPTION_MSG_UPLOAD_FILE_FAIL+ Objects.requireNonNull(response.getBody()).getMsg());
        }
        return response.getBody().getData();
    }


    /**
     * executeTask
     *
     * @param taskId taskId
     * @return CreateTaskResult
     */
    public CreateTaskResult executeTask(String taskId){
        log.info("Start executing task transfer, taskId: {}",taskId);
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
     * Query task file status
     *
     * @param taskId taskId
     * @return QueryTaskInfoResult
     */
    public QueryTaskInfoResult queryTaskInfo(String taskId){
        log.info("Start to query the transfer status, taskId: {}",taskId);
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
        log.info("Query status succeeded: {}",JsonUtils.getJsonString(response.getBody().getData()));
        return response.getBody().getData();
    }

}
