package com.hx.meituan.controller;

import java.util.List;

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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.com.geostar.common.beans.StandardResult;
import cn.com.geostar.common.beans.PaginationResult;

import com.hx.meituan.service.IGoodsService;
import com.hx.meituan.pojo.Goods;


/**
 *
 *
 * @author wangjunjie
 * @description : Goods 控制器    商品信息
 * @since 2019-04-08
 */

@RestController
public class GoodsController {
  private final Logger logger = LoggerFactory.getLogger(GoodsController.class);

  @Autowired
  public IGoodsService goodsService;

  /**
   * @description : 获取分页列表
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @GetMapping("/goods")
  public StandardResult selectPage(Integer pageSize, Integer pageNumber) {
    try {
      Page<Goods> page = new Page<Goods>(pageNumber, pageSize);
      Wrapper<Goods> wrapper = new EntityWrapper<Goods>();
      goodsService.selectPage(page, wrapper);
      return PaginationResult.ok(null, page.getRecords(), page.getTotal(), page.getPages());
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return PaginationResult.faild(e);
    }
  }

  /**
   * @description : 通过商品名获取Goods
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @GetMapping("/goodsInfo")
  public StandardResult selectById(String goodName) {
    try {
      Wrapper<Goods> wrapper = new EntityWrapper<Goods>();
      wrapper.eq(Goods.GOODS_NAME, goodName);
      Goods goods = goodsService.selectOne(wrapper);
      return StandardResult.ok(goods);
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 通过id删除Goods
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @DeleteMapping("/goods/{id}")
  public StandardResult deleteById(@PathVariable String id) {
    try {
      goodsService.deleteById(id);
      return StandardResult.ok();
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 批量更新Goods
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @PutMapping("/goods")
  public StandardResult updateById(@RequestBody List<Goods> goodsList) {
    try {
      goodsService.updateBatchById(goodsList);
      return StandardResult.ok();
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 添加Goods
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @PostMapping("/goods")
  public StandardResult insert(@ModelAttribute Goods goods) {
    try {
      goodsService.insert(goods);
      return StandardResult.ok();
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }
}
