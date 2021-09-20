package com.github.service.impl;

import com.github.annotation.Component;
import com.github.model.ResultInfo;
import com.github.service.IResultService;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class ResultServiceImpl implements IResultService {

    /**
     * 模拟调用 SaveServiceImpl保存方法异步返回的信息
     *
     * @return
     */
    @Override
    public ResultInfo callback(String code) throws Exception {
        System.out.println("根据标识：" + code + "查询结果");
        ResultInfo result = new ResultInfo();
        String[] state = new String[]{"V", "V", "V", "V", "V", "V", "S", "S", "E", "V"};
        String[] info = new String[]{"正在处理中", "正在处理中", "正在处理中", "正在处理中", "正在处理中", "正在处理中", "保存成功", "保存成功", "保存失败,code不满足条件", "正在处理中"};
        SplittableRandom splittableRandom = new SplittableRandom();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        Random random = new Random(System.currentTimeMillis() + splittableRandom.nextInt(10));
        int n = random.nextInt(state.length);
        result.setState(state[n]);
        result.setMessage(info[n]);
        return result;
    }

}
