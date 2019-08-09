package com.hx.meituan.controller;


import cn.com.geostar.common.utils.DateUtils;
import cn.com.geostar.common.utils.StringUtils;
import cn.com.geostar.common.utils.UUIDUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hx.meituan.dao.PageResult;
import com.hx.meituan.pojo.Car;
import com.hx.meituan.pojo.UserInfo;
import com.hx.meituan.service.ICarService;
import com.hx.meituan.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.com.geostar.common.beans.StandardResult;
import cn.com.geostar.common.beans.PaginationResult;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * @author wangjunjie
 * @description : UserInfo 控制器    ${table.comment}
 * @since 2019-04-07
 */

@RestController
public class UserInfoController {
  private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

  @Autowired
  public IUserInfoService userInfoService;
  @Autowired
  public ICarService iCarService;
  /**
   * @description : 获取分页列表
   * @author : wangjunjie
   * @since : Create in 2019-04-07
   */
  @GetMapping("/userInfo")
  public StandardResult selectPage(Integer page, Integer limit) {
    try {
      Page<UserInfo> userInfoPage = new Page<UserInfo>(page, limit);
      Wrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
      userInfoService.selectPage(userInfoPage, wrapper);
      int count = userInfoService.selectCount(wrapper);
      return PageResult.ok(null, userInfoPage.getRecords(), count);
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return PaginationResult.faild(e);
    }
  }

  /**
   * @description : 登录
   * @author : wangjunjie
   * @since : Create in 2019-04-07
   */
  @PostMapping("/login")
  public StandardResult login(@ModelAttribute UserInfo userInfo, HttpServletRequest request) {
    try {
      //判断用户是否存在
      Wrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
      wrapper.eq(UserInfo.TEL, userInfo.getTel());
      UserInfo selectOne = userInfoService.selectOne(wrapper);
      if (selectOne == null) {
        return StandardResult.faild("您输入的手机号还未注册!");
      } else {
        //校验输入的密码是否正确
        if (!userInfo.getPassword().equals(selectOne.getPassword())) {
          return StandardResult.faild("您输入的密码不正确！");
        }
        request.getSession().setAttribute("user", selectOne);
        return StandardResult.ok(selectOne);
      }
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }


  /**
   * @description : 更新UserInfo
   * @author : wangjunjie
   * @since : Create in 2019-04-07
   */
  @PostMapping("/change")
  public StandardResult updateById(@ModelAttribute UserInfo userInfo, HttpServletRequest request, String date) {
    try {
      //从session获取当前登录人信息
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      if(StringUtils.isNotBlank(date)){
        Date date1 = DateUtils.parseStringToDate(date);
        userInfo.setBirth(date1);
      }
      if(user == null){
        return StandardResult.faild("session中没有用户信息");
      }
      //判断输入的手机号是否被其他人注册
      Wrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
      wrapper.eq(UserInfo.TEL, userInfo.getTel());
      wrapper.ne(UserInfo.ID, user.getId());
      UserInfo info = userInfoService.selectOne(wrapper);
      if(info != null){
        return StandardResult.faild("该手机号已经被注册!");
      }
      userInfo.setId(user.getId());
      userInfoService.updateById(userInfo);
      //修改成功将session中数据进行修改
      request.getSession().removeAttribute("user");
      request.getSession().setAttribute("user", userInfoService.selectById(user.getId()));
      return StandardResult.ok("修改成功！");
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * @description : 注册
   * @author : wangjunjie
   * @since : Create in 2019-04-07
   */
  @PostMapping("/userInfo")
  public StandardResult insert(@ModelAttribute UserInfo userInfo) {
    try {
      //校验手机号是否被注册
      Wrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
      wrapper.eq(UserInfo.TEL, userInfo.getTel());
      UserInfo selectOne = userInfoService.selectOne(wrapper);
      if (selectOne != null) {
        return StandardResult.faild("手机号已经被注册！");
      }
      //没有昵称，设置初始化昵称
      if (StringUtils.isAnyBlank(userInfo.getName())) {
        //用默认昵称
        userInfo.setName("匿名用户");
      }
      userInfoService.insert(userInfo);
      return StandardResult.ok();
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * 通过session获取用户信息
   *
   * @param request
   * @return
   */
  @GetMapping("/session")
  public StandardResult getInfoBySession(HttpServletRequest request) {
    try {
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      if (user != null) {
        return StandardResult.ok(user);
      } else {
        return StandardResult.faild("没有获取到session数据");
      }
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * 获取用户余额
   * @param request
   * @return
   */
  @GetMapping("/money")
  public StandardResult getMoney(HttpServletRequest request) {
    try {
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      if (user != null) {
        //查询用户余额
        UserInfo info = userInfoService.selectById(user.getId());
        return StandardResult.ok(info);
      } else {
        return StandardResult.faild("没有获取到session数据");
      }
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * 余额充值
   * @param money
   * @param request
   * @return
   */
  @PostMapping("/money")
  public StandardResult addMoney(String money, HttpServletRequest request){
    try {
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      if (user != null) {
        //查询余额
        UserInfo info = userInfoService.selectById(user.getId());
        double oldMoney = Double.parseDouble(info.getMoney());
        double addMoney = Double.parseDouble(money);
        //充值余额
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setMoney(StringUtils.valueOf(oldMoney+addMoney));
        userInfoService.updateById(userInfo);
        return StandardResult.ok("充值成功！");
      } else {
        return StandardResult.faild("没有获取到session数据");
      }
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }
  /**
   * 修改密码
   * @param userInfo
   * @param request
   * @return
   */
  @PostMapping("/changepwd")
  public StandardResult updatePwd(@ModelAttribute UserInfo userInfo, HttpServletRequest request, String oldPassWord){
    try {
      //从session中获取用户信息
      UserInfo user = (UserInfo) request.getSession().getAttribute("user");
      if(user == null){
        return StandardResult.faild("session中没有用户信息");
      }
      //判断输入的原密码是否正确
      UserInfo info = userInfoService.selectById(user.getId());
      if(!info.getPassword().equals(oldPassWord)){
        return StandardResult.faild("您输入的密码不正确，请输入正确密码！");
      }
      //修改密码
      userInfo.setId(user.getId());
      userInfoService.updateById(userInfo);
      return StandardResult.ok("修改成功！");
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * 修改用户信息
   * @param userInfo
   * @return
   */
  @PostMapping("/changeUser")
  public StandardResult changeUser(@ModelAttribute UserInfo userInfo) {
    try {
      //判断输入的手机号是否被其他人注册
      Wrapper<UserInfo> wrapper = new EntityWrapper<UserInfo>();
      wrapper.eq(UserInfo.TEL, userInfo.getTel());
      wrapper.ne(UserInfo.ID, userInfo.getId());
      UserInfo info = userInfoService.selectOne(wrapper);
      if(info != null){
        return StandardResult.faild("该手机号已经被注册!");
      }
      if("".equals(userInfo.getPassword())){
        userInfo.setPassword(null);
      }
      userInfoService.updateById(userInfo);
      return StandardResult.ok("修改成功！");
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }

  /**
   * 删除用户
   * @param id
   * @return
   */
  @PostMapping("/deleteUser")
  public StandardResult delete(String id) {
    try {
      userInfoService.deleteById(id);
      //删除购物车信息
      Wrapper<Car> wrapper = new EntityWrapper<Car>();
      wrapper.eq(Car.USERID, id);
      iCarService.delete(wrapper);
      return StandardResult.ok("删除成功！");
    } catch (Exception e) {
      logger.error("异常信息:", e);
      return StandardResult.faild(e);
    }
  }
}
