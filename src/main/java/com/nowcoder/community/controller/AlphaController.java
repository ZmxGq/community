package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zmx
 * @create 2022-06-12 10:31
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello Spring Boot.";
    }
}
