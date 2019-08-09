package com.hx.meituan.controller;

import java.util.List;

import com.hx.meituan.dao.PageResult;
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

import com.hx.meituan.service.IGoodsGroupService;
import com.hx.meituan.pojo.GoodsGroup;


/**
 * @author wangjunjie
 * @description : GoodsGroup 控制器    团购信息
 * @since 2019-04-08
 */

@RestController
public class GoodsGroupController {
  private final Logger logger = LoggerFactory.getLogger(GoodsGroupController.class);

  @Autowired
  public IGoodsGroupService goodsGroupService;

  /**
   * @description : 获取分页列表
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @GetMapping("/goodsGroup")
  public StandardResult selectPage(Integer page, Integer limit) {
    try {
      Page<GoodsGroup> goodsPage = new Page<GoodsGroup>(page, limit);
      Wrapper<GoodsGroup> wrapper = new EntityWrapper<GoodsGroup>();
      goodsGroupService.selectPage(goodsPage, wrapper);
      int count = goodsGroupService.selectCount(wrapper);
      return PageResult.ok(null, goodsPage.getRecords(), count);
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return PaginationResult.faild(e);
    }
  }

  /**
   * @description : 通过goodsid获取GoodsGroup
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @GetMapping("/goodsGroupByGoodsId")
  public StandardResult selectById(String id) {
    try {
      Wrapper<GoodsGroup> wrapper = new EntityWrapper<GoodsGroup>();
      wrapper.eq(GoodsGroup.GOODSID, id);
      List<GoodsGroup> goodsGroups = goodsGroupService.selectList(wrapper);
      return StandardResult.ok(goodsGroups);
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 通过goodsName获取GoodsGroup
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @GetMapping("/goodsGroupByGoodsName")
  public StandardResult selectByName(String name) {
    try {
      Wrapper<GoodsGroup> wrapper = new EntityWrapper<GoodsGroup>();
      wrapper.eq(GoodsGroup.TITLE, name);
      GoodsGroup goodsGroups = goodsGroupService.selectOne(wrapper);
      return StandardResult.ok(goodsGroups);
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 通过id删除GoodsGroup
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @DeleteMapping("/goodsGroup/{id}")
  public StandardResult deleteById(@PathVariable String id) {
    try {
      goodsGroupService.deleteById(id);
      return StandardResult.ok();
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 更新GoodsGroup
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @PostMapping("/updateGoodsGroup")
  public StandardResult updateById(GoodsGroup goodsGroup) {
    try {
      goodsGroupService.updateById(goodsGroup);
      return StandardResult.ok("修改成功！");
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 添加GoodsGroup
   * @author : wangjunjie
   * @since : Create in 2019-04-08
   */
  @PostMapping("/goodsGroup")
  public StandardResult insert(@ModelAttribute GoodsGroup goodsGroup) {
    try {
      goodsGroupService.insert(goodsGroup);
      return StandardResult.ok();
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }
}
