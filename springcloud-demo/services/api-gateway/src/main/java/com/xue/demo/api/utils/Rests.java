package com.xue.demo.api.utils;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用多线程接口Callable接口二次封装Rests
 */
public final class Rests {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(Rests.class);
  
  private Rests(){};
  
  private static DefaultHanlder defaultHanlder = new DefaultHanlder();
  
  
  /**
   * 执行服务调用，并判断返回状态
   * 创建执行线程的三种方式：
   * 方式一：继承Thread类
   * 方式二：实现Runnable接口
   * 方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
   *
   * @param callable
   * @return
   */
  //Callable是类似于Runnable的接口，实现Callable接口的类和实现Runnable的类都是可被其它线程执行的任务
  public static <T> T exc(Callable<T> callable){
    return exc(callable, defaultHanlder);
  }
  
  public static <T> T exc(Callable<T> callable, ResultHandler handler){
    T result = sendReq(callable);
    return handler.handle(result);
  }
  
  public static String toUrl(String serviceName,String path){
    return "http://" + serviceName + path;
  }
  
  public static class DefaultHanlder implements ResultHandler {

    @Override
    public <T> T handle(T result) {
      int code = 1;
      String msg = "";
      try {
        code =  (Integer)FieldUtils.readDeclaredField(result, "code", true);
        msg =  (String)FieldUtils.readDeclaredField(result, "msg", true);
      } catch (Exception e) {
        //ignore
      }
      if (code != 0) {
        throw new RestException("Get erroNo " + code + " when execute rest call with errorMsg " +msg);
      }
      return result;
    }
    
  }
  
  public interface ResultHandler{
    <T> T handle(T result);
  }

  /**
   * 发送请求，具体的请求操作封装在callable中
   * @param callable
   * @param <T>
   * @return
   */
  public static <T> T sendReq(Callable<T> callable){
    T result = null;
    try {
      result = callable.call();
    } catch (Exception e) {
      throw new RestException("sendReq error by " + e.getMessage());
    }finally {
      LOGGER.info("result={}",result);
    }
    return result;
    
  }
  
  
}
