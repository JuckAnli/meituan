package com.hx.meituan.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author wangjunjie
 * @version 2019年04月08日---下午16:08:54
 * @description
 */
public class Goods extends Model<Goods> {

  private static final long serialVersionUID = 1L;

  public static final String ID = "ID";

  public static final String ADDRESS = "ADDRESS";

  public static final String TEL = "TEL";

  public static final String BUSINESS_TIME = "BUSINESS_TIME";

  public static final String IMG = "IMG";

  public static final String GOODS_NAME = "GOODS_NAME";


  /**
   * 主键
   */
  @ApiModelProperty(value = "主键")
  @TableId("ID")
  private String id;
  /**
   * 地址
   */
  @ApiModelProperty(value = "地址")
  @TableField("ADDRESS")
  private String address;
  /**
   * 电话
   */
  @ApiModelProperty(value = "电话")
  @TableField("TEL")
  private String tel;
  /**
   * 营业时间
   */
  @ApiModelProperty(value = "营业时间")
  @TableField("BUSINESS_TIME")
  private String businessTime;
  /**
   * 图片地址
   */
  @ApiModelProperty(value = "图片地址")
  @TableField("IMG")
  private String img;
  /**
   * 商品名称
   */
  @ApiModelProperty(value = "商品名称")
  @TableField("GOODS_NAME")
  private String goodsName;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getBusinessTime() {
    return businessTime;
  }

  public void setBusinessTime(String businessTime) {
    this.businessTime = businessTime;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  @Override
  protected Serializable pkVal() {
    return this.id;
  }


}
