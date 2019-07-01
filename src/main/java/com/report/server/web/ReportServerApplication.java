package com.report.server.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(value = "com.report.server")
@RestController
public class ReportServerApplication {

    public static void main(String[] args) throws Exception {
        //type01
        SpringApplication springApplication = new SpringApplication(ReportServerApplication.class);
        springApplication.run(args);
       }

}
