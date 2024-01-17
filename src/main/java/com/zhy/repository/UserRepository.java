package com.zhy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhy.entity.User;

/**
 * @author zhy
 * <p>
 * 2020年10月23日
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount(String account);

}
