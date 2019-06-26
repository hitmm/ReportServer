package com.report.server.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.report.server.model.service.result.LoginResult;
import com.report.server.service.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/26-17:38
 *  
 */
@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "/login/{userName}/{passWord}", method = RequestMethod.GET)
    public LoginResult request(@PathVariable("userName") String userName, @PathVariable("passWord") String passWord) throws Exception {
        LoginResult result = loginService.login(userName, passWord);
        logger.info(String.format("result : %s.", JSONObject.toJSONString(result)));
        return result;
    }
}
