package com.xue.demo.repository;

import com.xue.demo.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Created by mingway on Date:2018-12-01 9:57.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

	/**
	 * 查找年龄段用户
	 * @param start
	 * @param end
	 * @return
	 */
	Flux<User> findByAgeBetween(int start, int end);

	/**
	 * 根据mongodb的查询语句，查询数据
	 * @return
	 */
	@Query("{'age':{'$gte':20,'$lte':30}}")
	Flux<User> oldUser();
}
