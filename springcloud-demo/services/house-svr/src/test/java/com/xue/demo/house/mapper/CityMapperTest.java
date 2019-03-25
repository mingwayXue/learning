package com.xue.demo.house.mapper;

import com.xue.demo.house.HouseSvrApplicationTests;
import com.xue.demo.house.model.City;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mingway on Date:2018-10-16 18:07.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class CityMapperTest extends HouseSvrApplicationTests{

	@Autowired
	private CityMapper cityMapper;

	@Test
	public void selectCitys() throws Exception {
		City city = new City();
		city.setId(1);
		List<City> cities = cityMapper.selectCitys(city);
		System.out.println(cities);
	}

}