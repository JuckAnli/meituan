package com.hx.meituan.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 团购信息
 * </p>
 *
 * @author wangjunjie
 * @version 2019年04月08日---下午18:52:23
 * @description
 */
@TableName("GOODS_GROUP")
@ApiModel(value = "GOODS_GROUP", description = "团购信息")
public class GoodsGroup extends Model<GoodsGroup> {

  private static final long serialVersionUID = 1L;

  public static final String ID = "ID";

  public static final String GOODSID = "GOODSID";

  public static final String TITLE = "TITLE";

  public static final String PRICE = "PRICE";

  public static final String OLD_PRICE = "OLD_PRICE";

  public static final String SOLD_NUMS = "SOLD_NUMS";

  public static final String IMG = "IMG";

  /**
   * 主键
   */
  @ApiModelProperty(value = "主键")
  @TableId("ID")
  private String id;
  /**
   * goods主键
   */
  @ApiModelProperty(value = "goods主键")
  @TableField("GOODSID")
  private String goodsid;
  /**
   * 套餐名
   */
  @ApiModelProperty(value = "套餐名")
  @TableField("TITLE")
  private String title;
  /**
   * 优惠价格
   */
  @ApiModelProperty(value = "优惠价格")
  @TableField("PRICE")
  private String price;
  /**
   * 门店价
   */
  @ApiModelProperty(value = "门店价")
  @TableField("OLD_PRICE")
  private String oldPrice;
  /**
   * 已售数量
   */
  @ApiModelProperty(value = "已售数量")
  @TableField("SOLD_NUMS")
  private String soldNums;
  /**
   * 图片
   */
  @ApiModelProperty(value = "图片")
  @TableField("IMG")
  private String img;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getGoodsid() {
    return goodsid;
  }

  public void setGoodsid(String goodsid) {
    this.goodsid = goodsid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getOldPrice() {
    return oldPrice;
  }

  public void setOldPrice(String oldPrice) {
    this.oldPrice = oldPrice;
  }

  public String getSoldNums() {
    return soldNums;
  }

  public void setSoldNums(String soldNums) {
    this.soldNums = soldNums;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  @Override
  protected Serializable pkVal() {
    return this.id;
  }


}
