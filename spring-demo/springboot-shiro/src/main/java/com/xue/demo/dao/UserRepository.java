package com.xue.demo.dao;

import com.xue.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mingway on Date:2019-01-10 15:58.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);
}
