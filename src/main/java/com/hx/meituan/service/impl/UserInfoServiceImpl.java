package com.hx.meituan.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hx.meituan.mapper.UserInfoMapper;
import com.hx.meituan.pojo.UserInfo;
import com.hx.meituan.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author wangjunjie
 * @version 2019年04月07日---下午15:52:10
 * @description
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
	
}
