package com.example.task_management_system_ampada;

import com.example.task_management_system_ampada.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class TaskManagementSystemAmpadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSystemAmpadaApplication.class, args);
    }

}
