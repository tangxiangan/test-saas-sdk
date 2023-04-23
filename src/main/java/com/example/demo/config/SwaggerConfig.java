package com.example.demo.config;


import com.example.demo.constant.CommonConstant;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;


@SuppressWarnings({"Guava", "deprecation"})
@EnableSwagger2
@Profile({"local", "test"})
@Configuration
public class SwaggerConfig {

    @Value("${security.oauth2.client.access-token-uri:}")
    private String tokenUri;

    @Value("${security.oauth2.client.client-id:}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret:}")
    private String clientSecret;

    @Value("${security.oauth2.client.user-authorization-uri:}")
    private String authorizeUri;

    @Value("${swagger.basePackage:com.example.demo.controller}")
    private String basePackage;

    @Value("${swagger.title:sample Title}")
    private String title;

    @Value("${swagger.description:Sample Description}")
    private String description;

    private String version = "v1.0";

    private String contactName = "";

    private String contactEmail = "";

    /**
     * 主要是这个方法，其他的方法是抽出去的，所以大家不要害怕为啥有这么多方法
     * 在 basePackage 里面写需要生成文档的 controller 路径
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private Predicate<RequestHandler> basePackage() {
        return input -> {
            assert input != null;
            return declaringClass(input).transform(handlerPackage()).or(true);
        };
    }

    private Function<Class<?>, Boolean> handlerPackage() {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(CommonConstant.STRING_SIGN_COMMA)) {
                assert input != null;
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

    /**
     * 这个方法主要是写一些文档的描述
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                title,
                description,
                version,
                "",
                new Contact(contactName, "", contactEmail),
                "", "", Collections.emptyList());
    }

    /**
     * 这个类决定了你使用哪种认证方式，我这里使用密码模式
     * 其他方式自己摸索一下，完全莫问题啊
     */
    private SecurityScheme securityScheme() {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(tokenUri);

        return new OAuthBuilder()
                .name("spring_oauth")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }

    /**
     * 这里设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 这里是写允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")};
    }


}
