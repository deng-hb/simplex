package com.denghb.simplex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

// http://localhost:8081/swagger-ui.html
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket system() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("System")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.denghb.simplex.sys"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket business() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Business")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.denghb.simplex.bu"))
                .paths(PathSelectors.any())
                .build();
    }

    private List<SecurityContext> securityContexts() {
        return Arrays.asList(SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})))
                .forPaths(PathSelectors.regex("^/sys/.*$"))
                .build());
    }

    private List<ApiKey> securitySchemes() {
        return Arrays.asList(new ApiKey("Authorization", "X-Access-Token", "header"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simplex Project")
                .description("API Document")
                .version("1.0")
                .build();
    }
}