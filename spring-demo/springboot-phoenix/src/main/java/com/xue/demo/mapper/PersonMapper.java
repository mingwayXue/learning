package com.xue.demo.mapper;


import com.xue.demo.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {

    List<Person> findAll();

    Person findById(@Param("id") Integer id);

    void execSql(@Param("sqlStr") String sqlStr);
}
