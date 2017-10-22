package com.api.demo;

import com.api.demo.controller.FileUploadController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = {
	    FileUploadController.class
})
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.api.demo"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(metaData());
    }
    private ApiInfo metaData() {

    	 ApiInfo apiInfo = new ApiInfo(
                 "Spring Boot REST API",
                 "Spring Boot REST API for File Upload Application",
                 "1.0",
                 "Terms of service",
                "",
                "",
                 "");
         return apiInfo;
    }
}