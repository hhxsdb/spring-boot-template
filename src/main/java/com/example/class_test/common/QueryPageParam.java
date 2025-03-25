package com.example.class_test.common;

import java.util.HashMap;
import lombok.Data;

/**
 * @ClasssName QueryPageParam
 * @Create 2025/3/13   15:12
 * @Content
 */
@Data
public class QueryPageParam {
//  当前端未传递值 设置默认值
  private static int PAGE_SIZE = 20;
  private static int PAGE_NUM= 1;
//
  private int pageSize  = PAGE_SIZE;
  private int pageNum = PAGE_NUM ;
  private HashMap params = new HashMap<>();
}
