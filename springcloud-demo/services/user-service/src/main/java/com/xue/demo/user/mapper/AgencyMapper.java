package com.xue.demo.user.mapper;

import java.util.List;

import com.xue.demo.user.common.PageParams;
import com.xue.demo.user.model.Agency;
import com.xue.demo.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface AgencyMapper {

  List<Agency> select(Agency agency);
  
  int insert(Agency agency);
  
  List<User> selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);
  
  Long selectAgentCount(@Param("user") User user);
}
