package com.xue.demo.service;

import com.xue.demo.domain.Role;
import com.xue.demo.domain.User;

import java.util.Map;

/**
 * Created by mingway on Date:2019-01-10 16:09.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public interface ILoginService {

	User addUser(Map<String, Object> map);

	Role addRole(Map<String, Object> map);

	User findByName(String name);
}
