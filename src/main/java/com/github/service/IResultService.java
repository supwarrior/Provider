package com.github.service;

import com.github.model.ResultInfo;

/**
 * @author 康盼Java开发工程师
 */
public interface IResultService {

    /**
     * callback
     *
     * @param code
     * @return
     * @throws Exception
     */
    ResultInfo callback(String code) throws Exception;
}
