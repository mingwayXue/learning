package com.xue.demo.dao;

import com.xue.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mingway on Date:2018-08-18 11:07.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Mapper
public interface UserDao {

	@Select("select * from user where id = #{id}")
	User findUserById(Integer id);		//@Param("id")，如果有多个参数时，必须指定@Param注解

	User findId(Integer id);			//@Param("id")，如果有多个参数时，必须指定@Param注解

	@Select("select * from user")
	List<User> findAll();

}
