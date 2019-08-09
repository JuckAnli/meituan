package com.hx.meituan.controller;

import java.util.Date;
import java.util.List;

import cn.com.geostar.common.utils.StringUtils;
import com.hx.meituan.dao.PageResult;
import com.hx.meituan.pojo.GoodsGroup;
import com.hx.meituan.pojo.UserInfo;
import com.hx.meituan.service.IGoodsGroupService;
import com.hx.meituan.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.com.geostar.common.log.SystemLog;
import cn.com.geostar.common.beans.Constants;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.com.geostar.common.beans.StandardResult;
import cn.com.geostar.common.beans.PaginationResult;

import com.hx.meituan.service.ICarService;
import com.hx.meituan.pojo.Car;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjunjie
 * @description : Car 控制器    ${table.comment}
 * @since 2019-04-11
 */

@RestController
public class CarController {
  private final Logger logger = LoggerFactory.getLogger(CarController.class);

  @Autowired
  public ICarService carService;
  @Autowired
  public IUserInfoService iUserInfoService;
  @Autowired
  public IGoodsGroupService iGoodsGroupService;

  /**
   * @description : 获取购物车分页列表
   * @author : wangjunjie
   * @since : Create in 2019-04-11
   */
  @GetMapping("/car")
  public StandardResult selectPage(Integer page, Integer limit, HttpServletRequest request) {
    try {
      //获取用户信息
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      Page<Car> pageUser = new Page<Car>(page, limit);
      Wrapper<Car> wrapper = new EntityWrapper<Car>();
      wrapper.eq(Car.USERID, user.getId());
      wrapper.eq(Car.STATE, 0);
      carService.selectPage(pageUser, wrapper);
      int count = carService.selectCount(wrapper);
      return PageResult.ok(null, pageUser.getRecords(), count);
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return PaginationResult.faild(e);
    }
  }

  /**
   * 获取所有购物信息
   *
   * @param page
   * @param limit
   * @return
   */
  @GetMapping("/allCar")
  public StandardResult selectCarPage(Integer page, Integer limit) {
    try {
      Page<Car> pageUser = new Page<Car>(page, limit);
      Wrapper<Car> wrapper = new EntityWrapper<Car>();
      wrapper.eq(Car.STATE, 1);
      List<Car> cars = carService.selectCarPage(pageUser, wrapper);
      pageUser.setRecords(cars);
      int count = carService.selectCount(wrapper);
      return PageResult.ok(null, pageUser.getRecords(), count);
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return PaginationResult.faild(e);
    }
  }

  /**
   * @description : 获取购买过的商品
   * @author : wangjunjie
   * @since : Create in 2019-04-11
   */
  @GetMapping("/payCar")
  public StandardResult selectById(Integer page, Integer limit, HttpServletRequest request) {
    try {
      //获取用户信息
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      Page<Car> pageUser = new Page<Car>(page, limit);
      Wrapper<Car> wrapper = new EntityWrapper<Car>();
      wrapper.eq(Car.USERID, user.getId());
      wrapper.eq(Car.STATE, 1);
      carService.selectPage(pageUser, wrapper);
      int count = carService.selectCount(wrapper);
      return PageResult.ok(null, pageUser.getRecords(), count);
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 通过id删除Car
   * @author : wangjunjie
   * @since : Create in 2019-04-11
   */
  @PostMapping("/delete")
  public StandardResult deleteById(String id) {
    try {
      carService.deleteById(id);
      return StandardResult.ok("删除成功！");
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 支付
   * @author : wangjunjie
   * @since : Create in 2019-04-11
   */
  @PostMapping("/pay")
  public StandardResult pay(Car car, HttpServletRequest request) {
    try {
      //获取用户
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      //从数据库获取用户信息
      UserInfo userInfo = iUserInfoService.selectById(user.getId());
      //从余额扣除购买商品的钱
      String money = StringUtils.valueOf(Double.parseDouble(userInfo.getMoney()) - Double.parseDouble(car.getTotal()));
      userInfo.setMoney(money);
      //更新用户信息
      iUserInfoService.updateById(userInfo);
      //修改套餐售卖数量
      Wrapper<GoodsGroup> wrapper = new EntityWrapper<GoodsGroup>();
      wrapper.eq(GoodsGroup.TITLE, car.getGoodsName());
      GoodsGroup goodsGroup = iGoodsGroupService.selectOne(wrapper);
      goodsGroup.setSoldNums(StringUtils.valueOf(Integer.parseInt(goodsGroup.getSoldNums()) + Integer.parseInt(car.getNums())));
      iGoodsGroupService.updateById(goodsGroup);
      car.setState("1");
      car.setTime(new Date());
      carService.updateById(car);
      return StandardResult.ok();
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 添加Car
   * @author : wangjunjie
   * @since : Create in 2019-04-11
   */
  @PostMapping("/car")
  public StandardResult insert(@ModelAttribute Car car, HttpServletRequest request) {
    try {
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      car.setUserid(user.getId());
      car.setTime(new Date());
      carService.insert(car);
      return StandardResult.ok();
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * 修改购物车信息
   * @param car
   *
   * @return
   */
  @PostMapping("/changeCar")
  public StandardResult changeCar(@ModelAttribute Car car) {
    try {
      String nums = car.getNums();
      String price = car.getPrice();
      double total = Double.parseDouble(nums) * Double.parseDouble(price);
      car.setTotal(StringUtils.valueOf(total));
      carService.updateById(car);
      return StandardResult.ok("修改成功！");
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }
}
