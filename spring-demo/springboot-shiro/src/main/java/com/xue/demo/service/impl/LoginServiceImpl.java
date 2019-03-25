package com.xue.demo.service.impl;

import com.xue.demo.dao.RoleRepository;
import com.xue.demo.dao.UserRepository;
import com.xue.demo.domain.Permission;
import com.xue.demo.domain.Role;
import com.xue.demo.domain.User;
import com.xue.demo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by mingway on Date:2019-01-10 16:10.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Service
@Transactional
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	//添加用户
	@Override
	public User addUser(Map<String, Object> map) {
		User user = new User();
		user.setName(map.get("username").toString());
		user.setPassword(Integer.valueOf(map.get("password").toString()));
		userRepository.save(user);
		return user;
	}

	//添加角色
	@Override
	public Role addRole(Map<String, Object> map) {
		Optional<User> optional = userRepository.findById(Long.valueOf(map.get("userId").toString()));
		User user = optional.get();	//备注todo
		Role role = new Role();
		role.setRoleName(map.get("roleName").toString());
		role.setUser(user);
		Permission permission1 = new Permission();
		permission1.setPermission("create");
		permission1.setRole(role);
		Permission permission2 = new Permission();
		permission2.setPermission("update");
		permission2.setRole(role);
		List<Permission> permissions = new ArrayList<>();
		permissions.add(permission1);
		permissions.add(permission2);
		role.setPermissions(permissions);
		roleRepository.save(role);
		return role;
	}

	//查询用户通过用户名
	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
}