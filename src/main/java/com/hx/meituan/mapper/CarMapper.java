package com.hx.meituan.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hx.meituan.pojo.Car;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * ${table.comment} Mapper 接口
 * </p>
 *
 * @author wangjunjie
 * @version 2019年04月11日---下午15:51:16
 * @description
 */
public interface CarMapper extends BaseMapper<Car> {
  /**
   * 获取购物车分页
   * @param wrapper
   * @param page
   * @return
   */
  List<Car> selectCarPage(@Param("ew")Wrapper<Car> wrapper, Page<Car> page);
}