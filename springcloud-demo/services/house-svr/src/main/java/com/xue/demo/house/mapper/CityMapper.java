package com.xue.demo.house.mapper;

import java.util.List;

import com.xue.demo.house.model.City;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CityMapper {
  
  public List<City> selectCitys(City city);

}
