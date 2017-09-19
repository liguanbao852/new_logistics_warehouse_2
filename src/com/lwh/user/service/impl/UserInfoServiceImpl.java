package com.lwh.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwh.user.dao.UserInfoDao;
import com.lwh.user.entity.UserInfo;
import com.lwh.user.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDaoImpl;
	

	@Override
	public int insertUserInfo(UserInfo userInfo) {
		return userInfoDaoImpl.insertUserInfo(userInfo);
	}

	

	
}
