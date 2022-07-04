package com.jiaqi.service;

import com.jiaqi.common.User;
import com.jiaqi.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("Get User By User id. Id:" + id);
        Random random = new Random();
        return User.builder()
                .id(id)
                .userName(UUID.randomUUID().toString())
                .sex(random.nextBoolean())
                .build();
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("成功插入User！ UserId:" + user.getId());
        return user.getId();
    }
}
