package com.wangxin.langshu.business;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wangxin.langshu.authCenter.util.enums.OperatePlatEnum;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	// http://localhost:8080/swagger-ui.html	原访问路径
	// http://localhost:8080/doc.html   		友好体验版的访问路径

	//swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	@Bean
	public Docket createRestApi() {
		List<Parameter> parameters = new ArrayList<>();
		parameters.add(new ParameterBuilder()
                .name("token")
                .description("认证token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());
		parameters.add(new ParameterBuilder()
                .name("platform")
                .description("默认为空，如果是需要授权的需要填写平台标识,可选值pcweb、pcboss、mobile")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());
		parameters.add(new ParameterBuilder()
                .name("orgno")
                .description("wx")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.wangxin.langshu"))
				.paths(PathSelectors.any())
				.build()
				//.securitySchemes(securitySchemes())
				//.securityContexts(securityContexts())
				.globalOperationParameters(parameters);
	}
	
	/*
	private List<ApiKey> securitySchemes() {
		List<ApiKey> apiKeyList = new ArrayList<>();
		apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
		return apiKeyList;
	}*/


	//构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("旺鑫树学习考试系统")
				//创建人 
				.contact("http://wangxinvip.com")
				// 描述
				.description("旺鑫树学习系统在线文档，可根据各个api接口的规范来调用调试")
				//版本号
				.version("1.0")
				//.termsOfServiceUrl("http://127.0.0.1:8080/parkmanager/swagger-ui.html")
				.build();
	}
	/*
	private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!app).*$")).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }*/
	
}
