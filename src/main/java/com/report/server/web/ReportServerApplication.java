package com.report.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(value = "com.report.server")
@RestController
public class ReportServerApplication {
    private final static Logger logger = LoggerFactory.getLogger(ReportServerApplication.class);

    public static void main(String[] args) {
        logger.info("##################################ReportServerApplication init Start ....");
        SpringApplication.run(ReportServerApplication.class, args);
        logger.info("##################################ReportServerApplication init End ....");
    }

}
