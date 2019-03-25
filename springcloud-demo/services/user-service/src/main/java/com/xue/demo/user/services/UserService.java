package com.xue.demo.user.services;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.xue.demo.user.exception.UserException;
import com.xue.demo.user.mapper.UserMapper;
import com.xue.demo.user.model.User;
import com.xue.demo.user.utils.BeanHelper;
import com.xue.demo.user.utils.HashUtils;
import com.xue.demo.user.utils.JwtHelper;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by mingway on Date:2018-10-15 15:45.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Service
public class UserService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private UserMapper userMapper;

	//一般为文件服务器前缀
	@Value("file.prefix")
	private String imgPrefix;

	@Autowired
	private MailService mailService;

	/**
	 *	根据id获取user对象
	 * @param id
	 * @return
	 */
	public User getUserById(Long id) {
		String key = "user:" + id;

		//1.通过redis缓存获取用户数据
		String userJson = (String) redisTemplate.opsForValue().get(key);		//需要与设置redis的key保持一致
		User user = null;

		/**
		 * 2.判断redis缓存是否存在用户数据：
		 * 存在：返回数据
		 * 不存在：从数据库获取数据，并把数据存入redis，设置缓存时间5min
		 */
		if (Strings.isNullOrEmpty(userJson)) {
			user = userMapper.selectById(id);
			user.setAvatar(imgPrefix + user.getAvatar());	//设置头像
			String string  = JSON.toJSONString(user);
			redisTemplate.opsForValue().set(key, string);	//设置key
			redisTemplate.expire(key, 5, TimeUnit.MINUTES);		//设置过期时间
		} else {
			user = JSON.parseObject(userJson, User.class);
		}
		return user;
	}

	/**
	 * 获取用户信息（用户名-密码）
	 * @param user
	 * @return
	 */
	public List<User> getUserByQuery(User user) {
		List<User> users = userMapper.select(user);
		users.forEach(u -> {
			u.setAvatar(imgPrefix + u.getAvatar());
		});
		return users;
	}

	/**
	 * 注册
	 * @param user		用户信息
	 * @param enableUrl	激活链接
	 * @return
	 */
	public boolean addAccount(User user, String enableUrl) {
		user.setPasswd(HashUtils.encryPassword(user.getPasswd()));
		BeanHelper.onInsert(user);		//插入更新时间和创建时间
		userMapper.insert(user);		//插入数据
		registerNotify(user.getEmail(), enableUrl);		//调用邮件通知用户
		return true;
	}

	/**
	 * 发送注册激活邮件
	 * @param email
	 * @param enableUrl
	 */
	private void registerNotify(String email, String enableUrl) {
		String randomKey = HashUtils.hashString(email) + RandomStringUtils.randomAlphabetic(10);		//随机码
		redisTemplate.opsForValue().set(randomKey, email);
		redisTemplate.expire(randomKey, 1,TimeUnit.HOURS);
		String content = enableUrl +"?key="+  randomKey;
		mailService.sendSimpleMail("用户邮件激活", content, email);
	}

	/**
	 * 响应邮件的激活
	 * @param key
	 */
	public Boolean enable(String key) {
		String email = (String) redisTemplate.opsForValue().get(key);
		if (StringUtils.isBlank(email)) {
			throw new UserException(UserException.Type.USER_NOT_FOUND, "无效的key");
		}
		User updateUser = new User();
		updateUser.setEmail(email);
		updateUser.setEnable(1);
		userMapper.update(updateUser);		//可以省略考虑数据库事务
		return true;
	}

	/**
	 * 校验用户名密码、生成token并返回用户对象
	 * @param email
	 * @param passwd
	 * @return
	 */
	public User auth(String email, String passwd) {
		if (StringUtils.isBlank(email) || StringUtils.isBlank(passwd)) {
			throw new UserException(UserException.Type.USER_AUTH_FAIL,"User Auth Fail");
		}
		User user = new User();
		user.setEmail(email);
		user.setPasswd(HashUtils.encryPassword(passwd));
		user.setEnable(1);
		List<User> list =  getUserByQuery(user);
		if (!list.isEmpty()) {
			User retUser = list.get(0);
			onLogin(retUser);
			return retUser;
		}
		throw new UserException(UserException.Type.USER_AUTH_FAIL,"User Auth Fail");
	}

	/**
	 * 生成token信息
	 * @param user
	 */
	private void onLogin(User user) {
		//生成token：根据用户名（邮件）、用户姓名、当前时间戳秒数
		String token =  JwtHelper.genToken(ImmutableMap.of("email", user.getEmail(), "name", user.getName(),"ts", Instant.now().getEpochSecond()+""));
		renewToken(token,user.getEmail());
		user.setToken(token);
	}

	/**
	 * redis存入用户token：用户名-token
	 * @param token
	 * @param email
	 * @return
	 */
	private String renewToken(String token, String email) {
		redisTemplate.opsForValue().set(email, token);
		redisTemplate.expire(email, 30, TimeUnit.MINUTES);
		return token;
	}

	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 */
	public User getLoginedUserByToken(String token) {
		Map<String, String> map = null;
		try {
			map = JwtHelper.verifyToken(token);		//token认证
		} catch (Exception e) {
			throw new UserException(UserException.Type.USER_NOT_LOGIN,"User not login");
		}
		String email =  map.get("email");
		Long expired = redisTemplate.getExpire(email);
		if (expired > 0L) {
			renewToken(token, email);
			User user = getUserByEmail(email);
			user.setToken(token);
			return user;
		}
		throw new UserException(UserException.Type.USER_NOT_LOGIN,"user not login");

	}

	/**
	 * 根据用户名（邮件）获取用户信息
	 * @param email
	 * @return
	 */
	private User getUserByEmail(String email) {
		User user = new User();
		user.setEmail(email);
		List<User> list = getUserByQuery(user);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		throw new UserException(UserException.Type.USER_NOT_FOUND,"User not found for " + email);
	}

	/**
	 * 删除缓存中的token信息
	 * @param token
	 */
	public void invalidate(String token) {
		Map<String, String> map = JwtHelper.verifyToken(token);
		redisTemplate.delete(map.get("email"));
	}

	@Transactional(rollbackFor = Exception.class)
	public User updateUser(User user) {
		if (user.getEmail() == null) {
			return null;
		}
		if (!Strings.isNullOrEmpty(user.getPasswd()) ) {
			user.setPasswd(HashUtils.encryPassword(user.getPasswd()));
		}
		userMapper.update(user);
		return userMapper.selectByEmail(user.getEmail());
	}

	public void resetNotify(String email,String url) {
		String randomKey = "reset_" + RandomStringUtils.randomAlphabetic(10);
		redisTemplate.opsForValue().set(randomKey, email);
		redisTemplate.expire(randomKey, 1,TimeUnit.HOURS);
		String content = url +"?key="+  randomKey;
		mailService.sendSimpleMail("房产平台重置密码邮件", content, email);

	}

	public String getResetKeyEmail(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}

	public User reset(String key, String password) {
		String email = getResetKeyEmail(key);
		User updateUser = new User();
		updateUser.setEmail(email);
		updateUser.setPasswd(HashUtils.encryPassword(password));
		userMapper.update(updateUser);
		return getUserByEmail(email);
	}
}
