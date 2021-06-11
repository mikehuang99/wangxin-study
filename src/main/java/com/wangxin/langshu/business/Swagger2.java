package com.wangxin.langshu.business;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Configuration
//@EnableSwagger2
public class Swagger2 {

	//@Bean
	public Docket createTestApi() throws Exception {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.wangxin.langshu.business")).paths(PathSelectors.any()).build();
	}

	public ApiInfo apiInfo() throws Exception{
		return new ApiInfoBuilder()
				.title("Spring Boot中使用Swagger2构建restful APIs")
				.description("旺鑫教育云平台")
				.termsOfServiceUrl("http://www.wangxinvip.com")
				.version("1.0")
				.build();
	}
}
