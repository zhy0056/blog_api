package com.zhy.service;

import java.util.List;

import com.zhy.entity.User;

/**
 * @author zhy
 * <p>
 * 2020年10月23日
 */
public interface UserService {

    List<User> findAll();

    User getUserByAccount(String account);

    User getUserById(Long id);

    Long saveUser(User user);

    Long updateUser(User user);

    void deleteUserById(Long id);
}
