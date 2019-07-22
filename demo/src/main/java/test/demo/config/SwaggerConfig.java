package test.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("test.demo.controller"))
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employee API")
            .description("Employee API reference for developers")
            .termsOfServiceUrl("http://thanhtinhpas1.com")
            .licenseUrl("thanhtinhpas1@gmail.com").version("1.0").build();
    }
}