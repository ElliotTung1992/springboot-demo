package com.github.dge1992.quickstart.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.exceptions.TemplateInputException;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/6/18
 **/
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/index")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://www.baidu.com");
        return "index";
    }

    @RequestMapping("/testErrorPage")
    public String testErrorPage() throws Exception{
        throw new Exception("页面不存在！");
    }

    @ResponseBody
    @RequestMapping("/testErrorPage2")
    public Object testErrorPage2(User user){
        user.getFullName();
        return 10/0;
    }
}
