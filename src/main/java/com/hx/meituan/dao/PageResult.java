package com.hx.meituan.dao;

import cn.com.geostar.common.beans.StandardResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * @Auther: 汪俊杰
 * @Date：2019年04月13日---14:46
 * @Description: layui page标准返回参数
 */
public class PageResult extends StandardResult {
  @ApiModelProperty(value = "总数据量")
  private int count;  // 总条数

  public PageResult(boolean state, String msg, Object data, int code,  int count) {
    super(state, msg, data, code, null);
    this.count = count;
  }

  public PageResult(int count) {
    this.count = count;
  }

  public static StandardResult ok(String msg, Object data, int count) {
    if (data == null) {
      data = new ArrayList<Object>();
    }

    if (msg == null) {
      msg = "";
    }

    return new PageResult(true, msg, data, 0, count);
  }

  public static StandardResult ok(Object data, int total, int pages) {

    return PageResult.ok(null, data, total);
  }


  public static StandardResult ok() {
    return PageResult.ok(null, 0);
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
