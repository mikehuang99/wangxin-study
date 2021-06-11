package com.wangxin.langshu.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
@SpringBootApplication
@MapperScan(basePackages = {"com.wangxin.langshu.business.mapper",
		"com.wangxin.langshu.business.edu.dao.*.impl.mapper"})
@ImportResource("classpath:dubbo-provider.xml")
@EnableScheduling//定时任务
*/
public class ServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");

        SpringApplication.run(ServiceApplication.class, args);
    }

}
