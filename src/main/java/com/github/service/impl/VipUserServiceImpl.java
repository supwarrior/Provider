package com.github.service.impl;

import com.github.annotation.Component;
import com.github.model.User;
import com.github.service.IUserService;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class VipUserServiceImpl implements IUserService {

    private User user;

    @Override
    public User getUser() {
        return user;
    }


    public void initDataMethod(){
        System.out.println("执行：init-method");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
    }
}
