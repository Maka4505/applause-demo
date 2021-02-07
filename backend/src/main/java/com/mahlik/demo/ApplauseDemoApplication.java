package com.mahlik.demo;

import com.mahlik.demo.configuration.PropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PropertiesConfiguration.class)
public class ApplauseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplauseDemoApplication.class, args);
    }

}
