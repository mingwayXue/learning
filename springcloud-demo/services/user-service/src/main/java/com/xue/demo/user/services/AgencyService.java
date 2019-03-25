package com.xue.demo.user.services;

import com.xue.demo.user.common.PageParams;
import com.xue.demo.user.mapper.AgencyMapper;
import com.xue.demo.user.model.Agency;
import com.xue.demo.user.model.User;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mingway on Date:2018-10-15 19:13.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Service
public class AgencyService {

	@Autowired
	private AgencyMapper agencyMapper;

	@Value("${file.prefix}")
	private String imgPrefix;

	/**
	 * 获取经济机构信息
	 * @return
	 */
	public List<Agency> getAllAgency(){
		return agencyMapper.select(new Agency());
	}

	/**
	 * 根据id获取机构信息
	 * @param id
	 * @return
	 */
	public Agency getAgency(Integer id){
		Agency agency = new Agency();
		agency.setId(id);
		List<Agency> agencies = agencyMapper.select(agency);
		if (agencies.isEmpty()) {
			return null;
		}
		return agencies.get(0);
	}

	/**
	 * 添加机构信息
	 * @param agency
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int add(Agency agency) {
		return  agencyMapper.insert(agency);
	}

	/**
	 * 获取所有的机构（配对类型）
	 * @param pageParams
	 * @return 所有机构信息和机构数量
	 */
	public Pair<List<User>,Long> getAllAgent(PageParams pageParams) {
		List<User> agents =  agencyMapper.selectAgent(new User(),pageParams);
		setImg(agents);
		Long count  =  agencyMapper.selectAgentCount(new User());
		return ImmutablePair.of(agents, count);
	}

	/**
	 * 头像（图片）设置
	 * @param users
	 */
	public void setImg(List<User> users){
		users.forEach(u -> {
			u.setAvatar(imgPrefix + u.getAvatar());
		});
	}

	/**
	 * 获取经纪人详情
	 * @param id
	 * @return
	 */
	public User getAgentDetail(Long id) {
		User user = new User();
		user.setId(id);
		user.setType(2);
		List<User> list = agencyMapper.selectAgent(user, new PageParams(1, 1));
		setImg(list);
		if (!list.isEmpty()) {
			User agent = list.get(0);
			//将经纪人关联的经纪机构也一并查询出来
			Agency agency = new Agency();
			agency.setId(agent.getAgencyId().intValue());
			List<Agency> agencies = agencyMapper.select(agency);
			if (!agencies.isEmpty()) {
				agent.setAgencyName(agencies.get(0).getName());
			}
			return agent;
		}
		return null;
	}
}
