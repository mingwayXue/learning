package com.xue.demo.api.dao;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xue.demo.api.common.RestResponse;
import com.xue.demo.api.config.GenericRest;
import com.xue.demo.api.model.Agency;
import com.xue.demo.api.model.ListResponse;
import com.xue.demo.api.model.User;
import com.xue.demo.api.utils.Rests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 调用用户服务
 * Created by Mingway on 2018/9/2.
 */
@Repository
@DefaultProperties(groupKey="userDao",
		commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")},	//命令属性--超时时间
		threadPoolProperties={@HystrixProperty(name="coreSize",value="10")		//线程池属性--线程池大小、最大队列大小
				,@HystrixProperty(name="maxQueueSize",value="1000")},
		threadPoolKey="userDao"
)
public class UserDao {

    @Autowired
    private GenericRest rest;

    //用户服务的服务名
    @Value("${service.name.user}")
    private String userServiceName;

//    public String getusername(Long id) {
//        //当在url前面添加“direct://”时，表示以直连的方式访问（此时不能通过访问“应用名+地址”的方式访问）
//        String url = "http://user/getusername?id=" + id;    //对应的是user-service中UserController的RequestMapping（getusername）
//        RestResponse<String> response = rest.get(url, new ParameterizedTypeReference<RestResponse<String>>() {}).getBody();
//        return response.getResult();
//    }

    /**
     * 获取用户信息
     * @param query
     * @return
     */
    @HystrixCommand
    public List<User> getUserList(User query) {
        ResponseEntity<RestResponse<List<User>>> responseEntity = rest.post("http://"+ userServiceName + "/user/getList", query, new ParameterizedTypeReference<RestResponse<List<User>>>() {});
        RestResponse<List<User>> restResponse = responseEntity.getBody();
        if (restResponse.getCode() == 0) {
            return restResponse.getResult();
        } else {
            return null;
        }
    }

    /**
     * 增加用户
     * @param account
     */
	@HystrixCommand
    public User addUser(User account) {
        String url = "http://" + userServiceName + "/user/add";
        ResponseEntity<RestResponse<User>> responseEntity = rest.post(url,account, new ParameterizedTypeReference<RestResponse<User>>() {});
        RestResponse<User> response = responseEntity.getBody();
        if (response.getCode() == 0) {
            return response.getResult();
        }{
            throw new IllegalStateException("Can not add user");
        }
    }

    /**
     * 用户激活
     * @param key
     */
	@HystrixCommand
    public boolean enable(String key) {
        Rests.exc(() ->{
                    String url = Rests.toUrl(userServiceName, "/user/enable?key=" + key);
                    ResponseEntity<RestResponse<Object>> responseEntity =
                            rest.get(url, new ParameterizedTypeReference<RestResponse<Object>>() {});
                    return responseEntity.getBody();
                }
        );
        return true;
    }

    /**
     * 用户登录，鉴权
     * @param user
     * @return
     */
	@HystrixCommand
    public User authUser(User user) {
        String url = "http://" + userServiceName + "/user/auth";
        ResponseEntity<RestResponse<User>> responseEntity =  rest.post(url, user, new ParameterizedTypeReference<RestResponse<User>>() {});
        RestResponse<User> response = responseEntity.getBody();
        if (response.getCode() == 0) {
            return response.getResult();
        }{
            throw new IllegalStateException("Can not add user");
        }
    }

    /**
     * 用户登出
     * @param token
     */
	@HystrixCommand
    public void logout(String token) {
        String url = "http://" + userServiceName + "/user/logout?token=" + token;
        rest.get(url, new ParameterizedTypeReference<RestResponse<Object>>() {});
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
	@HystrixCommand(fallbackMethod = "getUserByTokenFb", ignoreExceptions = RuntimeException.class)	//服务熔断时，触发方法getUserByTokenFb; 注：当方法抛出异常时，同样会被hystrix捕获定义为服务熔断，所以为了让异常能自处理，需要添加ignoreExceptions属性
    public User getUserByToken(String token) {
        String url = "http://" + userServiceName + "/user/get?token=" + token;
        ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>() {});
        RestResponse<User> response = responseEntity.getBody();
        if (response == null || response.getCode() != 0) {
            return null;
        }
        return response.getResult();
    }

	/**
	 * 服务降级方法
	 * @param token
	 * @return
	 */
	public User getUserByTokenFb(String token){
        return new User();
    }

    /**
     *
     * @param key
     * @return
     */
	@HystrixCommand
    public String getEmail(String key) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/user/getKeyEmail?key=" + key);
            ResponseEntity<RestResponse<String>> responseEntity =
                    rest.get(url,new ParameterizedTypeReference<RestResponse<String>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

	@HystrixCommand
    public User reset(String key, String password) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/user/reset?key=" + key + "&password="+password);
            ResponseEntity<RestResponse<User>> responseEntity =
                    rest.get(url,new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

	@HystrixCommand
    public void resetNotify(String email, String url) {
        Rests.exc(() -> {
            String sendUrl = Rests.toUrl(userServiceName, "/user/resetNotify?email=" + email + "&url="+url);
            rest.get(sendUrl,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return new Object();
        });
    }

	@HystrixCommand
    public Agency getAgencyById(Integer id) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/agency/agencyDetail?id=" + id);
            ResponseEntity<RestResponse<Agency>> responseEntity =
                    rest.get(url, new ParameterizedTypeReference<RestResponse<Agency>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

	@HystrixCommand
    public void addAgency(Agency agency) {
        Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/agency/add");
            ResponseEntity<RestResponse<Object>> responseEntity =
                    rest.post(url, agency,new ParameterizedTypeReference<RestResponse<Object>>() {});
            return responseEntity.getBody();
        });
    }

	@HystrixCommand
    public ListResponse<User> getAgentList(Integer limit, Integer offset) {
        return Rests.exc(() -> {
            String url = Rests.toUrl(userServiceName, "/agency/agentList?limit=" + limit + "&offset="+offset);
            ResponseEntity<RestResponse<ListResponse<User>>> responseEntity =
                    rest.get(url,new ParameterizedTypeReference<RestResponse<ListResponse<User>>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

	@HystrixCommand
    public User updateUser(User user) {
        return Rests.exc(() ->{
            String url = Rests.toUrl(userServiceName, "/user/update");
            ResponseEntity<RestResponse<User>> responseEntity =
                    rest.post(url, user, new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

	@HystrixCommand
    public List<Agency> getAllAgency() {
        return Rests.exc(() ->{
            String url = Rests.toUrl(userServiceName, "/agency/list");
            ResponseEntity<RestResponse<List<Agency>>> responseEntity =
                    rest.get(url, new ParameterizedTypeReference<RestResponse<List<Agency>>>() {});
            return responseEntity.getBody();
        }).getResult();
    }

	@HystrixCommand
    public User getAgentById(Long id) {
        return Rests.exc(() ->{
            String url = Rests.toUrl(userServiceName, "/agency/agentDetail?id=" +id);
            ResponseEntity<RestResponse<User>> responseEntity =
                    rest.get(url, new ParameterizedTypeReference<RestResponse<User>>() {});
            return responseEntity.getBody();
        }).getResult();
    }
}
