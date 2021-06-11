package com.wangxin.langshu;

import java.util.Arrays;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.apache.catalina.Context;



/**
 * http://localhost:8080/doc.html
 * http://localhost:8080/swagger-ui.html
 *
 */
//@EnableSwagger2Doc
@SpringBootApplication
//@ImportResource("classpath:dubbo-customer.xml")
@MapperScan(basePackages = {"com.wangxin.langshu.business.mapper","com.wangxin.langshu.authCenter.mapper",
"com.wangxin.langshu.business.edu.dao.*.impl.mapper","com.wangxin.langshu.authCenter.edu.dao.*.impl.mapper"})
@EnableScheduling//定时任务
public class ApiApplication {  


	
    @Autowired
    private Environment env;

	public static class CustomGenerator implements BeanNameGenerator {

		@Override
		public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
			return definition.getBeanClassName();
		}
	}
	 
	//----支持https开始
	@Bean
    TomcatServletWebServerFactory tomcatServletWebServerFactory() {
		String serverSslEnable =  env.getProperty("server.ssl.enable");
		System.out.println("serverSslEnable:"+serverSslEnable);
		if(serverSslEnable!=null&&"true".equals(serverSslEnable.trim())) {
	        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
	            @Override
	            protected void postProcessContext(Context context) {
	                // TODO Auto-generated method stub
	                SecurityConstraint constraint = new SecurityConstraint();
	                constraint.setUserConstraint("CONFIDENTIAL");
	                SecurityCollection collection = new SecurityCollection();
	                collection.addPattern("/");
	                constraint.addCollection(collection);
	                context.addConstraint(constraint);
	            }            
	        };
	        
	        factory.addAdditionalTomcatConnectors(createTomcatConnector());
	        return factory;     
		}else {
			TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
	                 
	        };
	        return factory;
		}
        
    }
    
    private Connector createTomcatConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        String httpPort =  env.getProperty("http.port");
        System.out.println("httpPort:"+httpPort);
        connector.setPort(Integer.parseInt(httpPort));
        connector.setSecure(false);
        String serverPort =  env.getProperty("server.port");
        System.out.println("serverPort:"+serverPort);
        connector.setRedirectPort(Integer.parseInt(serverPort));
        return connector;
    }
	//----支持https结束

	
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");

        //SpringApplication.run(ApiApplication.class, args);
        
        SpringApplicationBuilder sab=new SpringApplicationBuilder(ApiApplication.class)
				.beanNameGenerator(new CustomGenerator());
		//这里如果想打印你加载的Spring的bean,可以这样做：
		ApplicationContext ac =sab.run(args);
		Arrays.stream(ac.getBeanDefinitionNames()).forEach(System.out::println);
    }
    

}
