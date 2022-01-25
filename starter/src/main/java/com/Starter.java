package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 3:20 下午
 * description：
 */
@SpringBootApplication
@ImportResource(locations = {"classpath*:service-*.xml"})
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
