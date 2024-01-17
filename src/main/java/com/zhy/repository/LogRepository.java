package com.zhy.repository;

import com.zhy.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhy
 * <p>
 * 2020年4月18日
 */
public interface LogRepository extends JpaRepository<Log, Integer> {
}
