package com.xue.demo.mapper;

import com.xue.demo.SpringbootPhoenixApplicationTests;
import com.xue.demo.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class JDBCMapperTest extends SpringbootPhoenixApplicationTests {

    @Autowired
    private PersonMapper mapper;

    @Test
    public void findAll() throws Exception {
        List<Person> personList = new ArrayList<>();

        personList = mapper.findAll();

        log.info("{}", personList);
    }

    @Test
    public void findById() {
        Person p = new Person();
        p = mapper.findById(2);
        log.info("person: {}", p);
    }

    @Test
    public void execSql() {
        String sql = "create table demo01(id integer not null primary key, name varchar)";
        mapper.execSql(sql);
    }

}