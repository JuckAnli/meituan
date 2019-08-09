package com.hx.meituan.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hx.meituan.pojo.Car;
import com.hx.meituan.mapper.CarMapper;
import com.hx.meituan.service.ICarService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author wangjunjie
 * @version 2019年04月11日---下午15:51:16
 * @description
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
  @Autowired
  CarMapper carMapper;

  @Override
  public List<Car> selectCarPage(Page<Car> page, Wrapper<Car> wrapper) {
    return carMapper.selectCarPage(wrapper, page);
  }
}
