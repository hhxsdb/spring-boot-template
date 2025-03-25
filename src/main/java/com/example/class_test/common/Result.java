package com.example.class_test.common;

import lombok.Data;

/**
 * @ClasssName Result
 * @Create 2025/3/14   16:13
 * @Content 封装返回给前端的数据
 */
@Data
public class Result {
  private int code;//编码:200
  private String msg;//成功 or 失败
  private  Long total;//返回数据长度
  private Object data ;//返回的数据

  public static Result fail(){
    return result(400,"失败",0L,null);
  }
  public static Result success(Object data,Long total){
    return result(200,"成功",total,data);
  }
  public static Result success(Object data){
    return result(200,"成功",0L,data);
  }
  public static Result success(){
    return result(200,"成功",0L,null);
  }
  private static Result result(int code,String msg,Long total,Object data){
    Result res = new Result();
    res.setCode(code);
    res.setMsg(msg);
    res.setTotal(total);
    res.setData(data);
    return  res;
  }
}
