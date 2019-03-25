package com.xue.demo.house.services;

import com.xue.demo.house.common.LimitOffset;
import com.xue.demo.house.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mingway on Date:2018-10-17 8:54.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Service
public class RecommendService {

	private static final String HOT_HOUSE_KEY = "_hot_house";

	@Autowired
	private HouseService houseService;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 获取房产热度
	 * @param size
	 * @return
	 */
	public List<House> getHotHouse(Integer size) {
//  Set<String> idSet =  redisTemplate.opsForZSet().range(HOT_HOUSE_KEY, 0, -1);
		Set<String> idSet =  redisTemplate.opsForZSet().reverseRange(HOT_HOUSE_KEY, 0, -1);//bug修复，根据热度从高到底排序
		List<Long> ids = idSet.stream().map(b -> Long.parseLong(b)).collect(Collectors.toList());
		House query = new House();
		query.setIds(ids);
		return houseService.queryAndSetImg(query, LimitOffset.build(size, 0));
	}

	/**
	 * 增加热度
	 * @param id
	 */
	public void increaseHot(long id) {
		redisTemplate.opsForZSet().incrementScore(HOT_HOUSE_KEY, ""+id, 1.0D);

		//只保留前前十个热度
		redisTemplate.opsForZSet().removeRange(HOT_HOUSE_KEY, 0, -11);
	}

	/**
	 * 获取最新房源
	 * @return
	 */
	public List<House> getLastest() {
		House query = new House();
		query.setSort("create_time");
		return houseService.queryAndSetImg(query, LimitOffset.build(8, 0));
	}
}
