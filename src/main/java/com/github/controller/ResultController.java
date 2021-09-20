package com.github.controller;

import com.github.annotation.RepeatSubmit;
import com.github.model.ResultInfo;
import com.github.service.ISaveService;
import com.github.service.impl.ProxyRetryerServiceImpl;
import com.github.service.impl.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 康盼Java开发工程师
 */
@RestController
@RequestMapping("/api")
public class ResultController {

    @Autowired
    private ISaveService saveService;

    @Autowired
    private ProxyRetryerServiceImpl proxyRetry;

    @GetMapping(path = "/retry")
    @RepeatSubmit
    public ResultInfo retry() {
        try {
            ResultServiceImpl proxy = proxyRetry.getProxy();
            String code = saveService.getCode(1);
            ResultInfo resultInfo = proxy.callback(code);
            return resultInfo;
        } catch (Exception exception) {
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setMessage(exception.getMessage());
            return resultInfo;
        }
    }
}
