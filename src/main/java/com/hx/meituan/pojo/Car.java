package com.hx.meituan.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author wangjunjie
 * @version 2019年04月11日---下午15:51:16
 * @description
 */
public class Car extends Model<Car> {

  private static final long serialVersionUID = 1L;

  public static final String ID = "ID";

  public static final String USERID = "USERID";

  public static final String GOODS_NAME = "GOODS_NAME";

  public static final String PRICE = "PRICE";

  public static final String NUMS = "NUMS";

  public static final String TOTAL = "TOTAL";

  public static final String STATE = "STATE";

  public static final String TIME = "TIME";

  /**
   * 主键id
   */
  @ApiModelProperty(value = "主键id")
  @TableId("ID")
  private String id;
  /**
   * 用户id
   */
  @ApiModelProperty(value = "用户id")
  @TableField("USERID")
  private String userid;
  /**
   * 商品名
   */
  @ApiModelProperty(value = "商品名")
  @TableField("GOODS_NAME")
  private String goodsName;
  /**
   * 单价
   */
  @ApiModelProperty(value = "单价")
  @TableField("PRICE")
  private String price;
  /**
   * 数量
   */
  @ApiModelProperty(value = "数量")
  @TableField("NUMS")
  private String nums;
  /**
   * 总价
   */
  @ApiModelProperty(value = "总价")
  @TableField("TOTAL")
  private String total;
  /**
   * 购物车状态 0未付款 1已付款
   */
  @ApiModelProperty(value = "购物车状态 0未付款 1已付款")
  @TableField("STATE")
  private String state;
  /**
   * 时间
   */
  @ApiModelProperty(value = "时间")
  @TableField("TIME")
  private Date time;
  /**
   * 用户名
   */
  @ApiModelProperty(value = "用户名")
  @TableField(exist = false)
  private String name;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getNums() {
    return nums;
  }

  public void setNums(String nums) {
    this.nums = nums;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  protected Serializable pkVal() {
    return this.id;
  }


}
