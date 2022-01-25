package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/24 5:04 下午
 * description：
 */
@SpringBootApplication
//@ImportResource(locations = {"classpath:spring-boot-stagging-*.xml"})
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
