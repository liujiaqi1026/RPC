package com.jiaqi.service;

import com.jiaqi.common.User;

public interface UserService {

    User getUserByUserId(Integer id);

    Integer insertUserId(User user);
}
