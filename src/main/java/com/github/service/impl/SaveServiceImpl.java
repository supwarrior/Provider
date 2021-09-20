package com.github.service.impl;

import com.github.annotation.Component;
import com.github.service.ISaveService;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class SaveServiceImpl implements ISaveService {

    /**
     * 模拟调用外部服务
     *
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public String getCode(int code) throws Exception {
        Thread.sleep(1000);
        if (code == 1) {
            return "Q-W-E-R-T-Y-U-I-O-P";
        } else {
            return "a-s-d-f-g-h-j-k-l";
        }
    }

}
