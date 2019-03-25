package com.xue.demo.user.common;

public enum RestCode {
  OK(0,"OK"),
  UNKNOWN_ERROR(1,"用户服务异常"),
  WRONG_PAGE(10100,"页码不合法"),
  TOKEN_INVALID(2,"TOKEN失效"),
  USER_NOT_EXIST(3,"用户不存在"),
  USER_NOT_FOUND(10101,"用户未找到"),
  ILLEGAL_PARAMS(10102,"参数不合法");
  
  
  public final int code;
  public final String msg;
  private RestCode(int code,String msg){
    this.code = code;
    this.msg = msg;
  }

}
