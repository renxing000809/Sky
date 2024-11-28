package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
public class SkyApplication {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.setProperty("druid.mysql.usePingMethod", "false");
        SpringApplication.run(SkyApplication.class, args);
        log.info("server started");
    }
}
