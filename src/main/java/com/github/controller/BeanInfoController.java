package com.github.controller;

import com.alibaba.fastjson.JSON;
import com.github.annotation.RepeatSubmit;
import com.github.javabean.BeanDriverManager;
import com.github.javabean.Beans;
import com.github.model.ResultInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 康盼Java开发工程师
 */
@RestController
@RequestMapping("/bean")
public class BeanInfoController {

    @GetMapping(path = "/print")
    @RepeatSubmit
    public ResultInfo print() throws Exception{
        Class.forName("com.github.javabean.BeanDriverManager");
        BeanDriverManager manager = (BeanDriverManager) Beans.cache.get("beanDriverManager");
        Map<String, Object> map = manager.printAllBean();
        ResultInfo resultInfo = new ResultInfo();
        String message = JSON.toJSONString(map);
        resultInfo.setMessage(message);
        return resultInfo;
    }
}
