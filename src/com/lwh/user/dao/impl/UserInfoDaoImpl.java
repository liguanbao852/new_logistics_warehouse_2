package com.lwh.user.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.lwh.user.entity.UserInfo;
import com.lwh.user.dao.UserInfoDao;


@Component
public class UserInfoDaoImpl implements UserInfoDao {
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;

	@Override
	public int insertUserInfo(UserInfo userInfo) {
		
		userInfo.setId(UUID.randomUUID().toString());
		
		//sqlMapClientTemplate.insert("insertUserInfo", userInfo);
		
		return 1;
	}

	

	
	
}
