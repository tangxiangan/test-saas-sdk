package com.example.demo.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.constant.PaddleAPIConstant;
import com.example.demo.interceptor.PaddleClientHttpRequestInterceptor;
import com.example.demo.properties.PaddleProperties;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @author txa 2023/1/16
 * <p>
 * PaddleClient
 */
@Component
@Slf4j
public class PaddleClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static String PADDLE_CHECKOUT_HOST;

    private static String PADDLE_VENDORS_HOST;

    private final PaddleProperties properties;

    public PaddleClient(PaddleProperties properties, PaddleClientHttpRequestInterceptor paddleClientHttpRequestInterceptor) {
        this.properties = properties;
        PADDLE_CHECKOUT_HOST = properties.getCheckoutHost();
        PADDLE_VENDORS_HOST = properties.getVendorsHost();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(properties.getReadTimeOut());
        factory.setConnectTimeout(properties.getConnectTimeout());
        restTemplate.setRequestFactory(factory);
        restTemplate.setInterceptors(Collections.singletonList(paddleClientHttpRequestInterceptor));
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(@NotNull ClientHttpResponse clientHttpResponse) throws IOException {
                // 只要重写此方法，不去抛出HttpClientErrorException异常即可
                // HttpStatus statusCode = clientHttpResponse.getStatusCode();
            }
        });
    }


    @NotNull
    private HttpHeaders getWwwFormHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }

    public String cancelSubscription(String thirdSubscriptionId) {
        log.info("paddle 取消续订:{}", thirdSubscriptionId);
        MultiValueMap<String, Object> requestParamMap = getValueMap(null);
        requestParamMap.set("subscription_id", thirdSubscriptionId);
        String resultJson = restTemplate.exchange(
                PADDLE_VENDORS_HOST + PaddleAPIConstant.LIST_TRANSACTIONS,
                HttpMethod.POST,
                new HttpEntity<>(requestParamMap, getWwwFormHttpHeaders()),
                String.class).getBody();
//        PaddleResult<Void> paddleResult = JSON.parseObject(resultJson,
//                new TypeReference<PaddleResult<Void>>() {
//                });

//        if (!paddleResult.isSuccess()) {
//            log.error("paddle 生成支付链接调用失败\n：{},\n参数：{}", paddleResult.getError(), thirdSubscriptionId);
//            throw new BackendRuntimeException(paddleResult.getError().getMessage());
//        }
        return null;
    }
    @NotNull
    private MultiValueMap<String, Object> getValueMap(Object o) {
        MultiValueMap<String, Object> requestParamMap = new LinkedMultiValueMap<>();
        requestParamMap.set(PaddleAPIConstant.VENDORID, properties.getVendorId());
        requestParamMap.set(PaddleAPIConstant.VENDORAUTHCODE, properties.getVendorAuthCode());
        Map<String, Object> stringObjectMap = JSON.parseObject(JSON.toJSONString(o),
                new TypeReference<Map<String, Object>>() {
                });
        Iterables.removeIf(stringObjectMap.values(), Objects::isNull);
        requestParamMap.setAll(stringObjectMap);
        return requestParamMap;
    }




}
