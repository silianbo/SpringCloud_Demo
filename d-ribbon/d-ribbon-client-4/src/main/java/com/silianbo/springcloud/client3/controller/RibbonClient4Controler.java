package com.silianbo.springcloud.client3.controller;


import com.silianbo.springcloud.client3.provider.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonClient4Controler {
    @Autowired
    Environment environment;
    @Autowired
    private HelloService helloService;

    @GetMapping(value = "s")
    public String helloServer() {
        String port = environment.getProperty("local.server.port");
        return "Hello port= " + port;
    }

    @GetMapping(value = "/hi")
    public String hi() {
        return helloService.hiService();
    }
}
