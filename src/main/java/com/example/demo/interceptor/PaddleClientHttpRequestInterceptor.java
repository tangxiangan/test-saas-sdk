package com.example.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author txa 2023/1/16
 */
@Slf4j
@Component
public class PaddleClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @NotNull
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //打印请求头信息
        tranceRequest(request, body);
        ClientHttpResponse execute = execution.execute(request, body);
        HttpStatus statusCode = execute.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return execute;
        } else if (statusCode.is3xxRedirection()) {
            // TODO http 3xx异常
            throw new RuntimeException();
        } else if (statusCode.is4xxClientError()) {
            // TODO http 4xx异常
            throw new RuntimeException();
        } else if (statusCode.is5xxServerError()) {
            // TODO http 5xx异常
            throw new RuntimeException();
        } else {
            // TODO http 未知异常
            throw new RuntimeException();
        }
    }

    private void tranceRequest(HttpRequest request, byte[] body) {
        log.info("======= paddle request begin ========");
        log.info("uri : {}", request.getURI());
        log.info("method : {}", request.getMethod());
        log.info("headers : {}", request.getHeaders());
        log.info("request body : {}", new String(body, StandardCharsets.UTF_8));
        log.info("======= paddle request end ========");
    }
}
