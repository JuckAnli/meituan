package com.hx.meituan.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hx.meituan.pojo.Car;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author wangjunjie
 * @version 2019年04月11日---下午15:51:16
 * @description
 */
public interface ICarService extends IService<Car> {
  /**
   * 获取购物车分页
   * @param page
   * @param wrapper
   * @return
   */
  List<Car> selectCarPage(Page<Car> page, Wrapper<Car> wrapper);
}
