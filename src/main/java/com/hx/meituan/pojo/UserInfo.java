package com.hx.meituan.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

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
 * @version 2019年04月07日---下午15:52:10
 * @description
 */
@TableName("USER_INFO")
@ApiModel(value = "USER_INFO", description = "${table.comment}")
public class UserInfo extends Model<UserInfo> {

  private static final long serialVersionUID = 1L;

  public static final String ID = "ID";

  public static final String TEL = "TEL";

  public static final String EMAIL = "EMAIL";

  public static final String NAME = "NAME";

  public static final String PASSWORD = "PASSWORD";

  public static final String ROLE = "ROLE";

  public static final String SEX = "SEX";

  public static final String BIRTH = "BIRTH";

  public static final String MONEY = "MONEY";

  /**
   * 主键
   */
  @ApiModelProperty(value = "主键")
  @TableId("ID")
  private String id;
  /**
   * 电话
   */
  @ApiModelProperty(value = "电话")
  @TableField("TEL")
  private String tel;
  /**
   * 邮箱
   */
  @ApiModelProperty(value = "邮箱")
  @TableField("EMAIL")
  private String email;
  /**
   * 昵称
   */
  @ApiModelProperty(value = "昵称")
  @TableField("NAME")
  private String name;
  /**
   * 密码
   */
  @ApiModelProperty(value = "密码")
  @TableField("PASSWORD")
  private String password;

  /**
   *
   * 角色
   */
  @ApiModelProperty(value = "角色")
  @TableField("ROLE")
  private String role;
  /**
   * 性别
   */
  @ApiModelProperty(value = "性别")
  @TableField("SEX")
  private String sex;
  /**
   * 生日
   */
  @ApiModelProperty(value = "生日")
  @TableField("BIRTH")
  private Date birth;
  /**
   * 余额
   */
  @ApiModelProperty(value = "余额")
  @TableField("MONEY")
  private String money;
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public String getMoney() {
    return money;
  }

  public void setMoney(String money) {
    this.money = money;
  }

  @Override
  protected Serializable pkVal() {
    return this.id;
  }


}
