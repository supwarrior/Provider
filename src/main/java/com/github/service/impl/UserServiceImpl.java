package com.github.service.impl;

import com.github.annotation.Component;
import com.github.annotation.Inject;
import com.github.model.User;
import com.github.service.IUserService;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class UserServiceImpl implements IUserService {

    @Inject
    private User user;

    @Override
    public User getUser() {
        return user;
    }
}
