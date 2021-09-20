package com.github.service.impl;

import com.github.annotation.Component;
import com.github.annotation.Inject;
import com.github.model.ResultInfo;
import com.github.model.Retryer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class ProxyRetryerServiceImpl extends Retryer implements MethodInterceptor {

    @Inject
    @Autowired
    private ResultServiceImpl resultService;

    private ConcurrentHashMap<ProxyRetryerServiceImpl, String> map = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String code = (String) objects[0];
        map.put(this, code);
        ResultInfo resultInfo = (ResultInfo) method.invoke(resultService, objects);
        // count 应该从配置中加载，这里演示10
        setBaseInfo(resultInfo, 10);
        return doRetryer();
    }

    @Override
    public ResultInfo loop() throws Exception {
        return resultService.callback(map.get(this));
    }

    public ResultServiceImpl getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ResultServiceImpl.class);
        enhancer.setCallback(this);
        return (ResultServiceImpl) enhancer.create();
    }
}
