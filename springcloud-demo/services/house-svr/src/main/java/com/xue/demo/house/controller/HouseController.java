package com.xue.demo.house.controller;

import com.google.common.base.Objects;
import com.xue.demo.house.common.*;
import com.xue.demo.house.model.*;
import com.xue.demo.house.services.HouseService;
import com.xue.demo.house.services.RecommendService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mingway on Date:2018-10-17 8:46.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@RestController
@RequestMapping("house")
public class HouseController {

	@Autowired
	private HouseService houseService;

	@Autowired
	private RecommendService recommendService;

	/**
	 * 房产列表展示
	 * @param req
	 * @return
	 */
	@RequestMapping("list")
	public RestResponse<ListResponse<House>> houseList(@RequestBody HouseQueryReq req){
		Integer limit  = req.getLimit();
		Integer offset = req.getOffset();
		House   query  = req.getQuery();
		Pair<List<House>, Long> pair = houseService.queryHouse(query, LimitOffset.build(limit, offset));
		return RestResponse.success(ListResponse.build(pair.getKey(), pair.getValue()));
	}

	/**
	 * 根据id获取房产信息
	 * @param id
	 * @return
	 */
	@RequestMapping("detail")
	public RestResponse<House> houseDetail(long id){
		House house = houseService.queryOneHouse(id);
		recommendService.increaseHot(id);		//增加热度
		return RestResponse.success(house);
	}

	/**
	 * 添加房产留言
	 * @param userMsg
	 * @return
	 */
	@RequestMapping("addUserMsg")
	public RestResponse<Object> houseMsg(@RequestBody UserMsg userMsg){
		houseService.addUserMsg(userMsg);
		return RestResponse.success();
	}

	/**
	 * 房产评分
	 * @param rating
	 * @param id
	 * @return
	 */
//	@ResponseBody
	@RequestMapping("rating")
	public RestResponse<Object> houseRate(Double rating,Long id){
		houseService.updateRating(id,rating);
		return RestResponse.success();
	}

	/**
	 * 所有小区信息
	 * @return
	 */
	@RequestMapping("allCommunitys")
	public RestResponse<List<Community>> toAdd(){
		List<Community> list = houseService.getAllCommunitys();
		return RestResponse.success(list);
	}

	/**
	 * 所有城市信息
	 * @return
	 */
	@RequestMapping("allCitys")
	public RestResponse<List<City>> allCitys(){
		List<City> list = houseService.getAllCitys();
		return RestResponse.success(list);
	}

	/**
	 * 房产新增
	 * @param house
	 * @return
	 */
	@RequestMapping("add")
	public RestResponse<Object> doAdd(@RequestBody House house){
		house.setState(CommonConstants.HOUSE_STATE_UP);
		if (house.getUserId() == null) {
			return RestResponse.error(RestCode.ILLEGAL_PARAMS);
		}
		houseService.addHouse(house,house.getUserId());
		return RestResponse.success();
	}

	/**
	 * 房产绑定/解绑用户
	 * @param req
	 * @return
	 */
	@RequestMapping("bind")
	public RestResponse<Object> delsale(@RequestBody HouseUserReq req){
		Integer bindType = req.getBindType();
		HouseUserType houseUserType = Objects.equal(bindType, 1) ? HouseUserType.SALE : HouseUserType.BOOKMARK;
		if (req.isUnBind()) {
			houseService.unbindUser2Houser(req.getHouseId(),req.getUserId(),houseUserType);
		}else {
			houseService.bindUser2House(req.getHouseId(), req.getUserId(), houseUserType);
		}
		return RestResponse.success();
	}

	@RequestMapping("hot")
	public RestResponse<List<House>> getHotHouse(Integer size){
		List<House> list =   recommendService.getHotHouse(size);
		return RestResponse.success(list);
	}

	@RequestMapping("lastest")
	public RestResponse<List<House>> getLastest(){
		return RestResponse.success(recommendService.getLastest());
	}
}
