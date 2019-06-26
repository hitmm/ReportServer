package com.report.server.web;

import com.report.server.model.service.result.LoginResult;
import com.report.server.service.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(value = "com.report.server")
@RestController
public class ReportServerApplication {

    @Autowired
    private ILoginService loginService;

    public static void main(String[] args) {
        SpringApplication.run(ReportServerApplication.class, args);
    }

    @RequestMapping(value = "/login/{userName}/{passWord}", method = RequestMethod.GET)
    public LoginResult request(@PathVariable("userName") String userName,@PathVariable("passWord") String passWord) throws Exception {
        System.out.println(userName);
        return loginService.login(userName,passWord);
    }
}
