package com.lwh.ThreeLogicServiece.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lwh.ThreeLogicServiece.service.ThreeLogicServiece;
import com.lwh.common.utils.PropUtils;
import com.lwh.user.dao.UserInfoDao;

@Service
public class ThreeLogicServieceImpl implements ThreeLogicServiece {

	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserInfoDao userInfoDaoImpl;

	// L0层判断
	@Override
	public Boolean firstLogic(String orderId) {

		logger.info("开始进行第L0层判断");
		
		String productId = "";
		int productNum = 0;
		
		// 判断产品需要的机器和功能是否正常
		Boolean isMachineAndFunctionOk = this.isMachineAndFunctionOk(productId);
		if(!isMachineAndFunctionOk){
			
			return false;
		}
		
		Boolean isMaterialEnough = this.isMaterialEnough(productId, productNum);
		if(!isMaterialEnough){
			
			return false;
		}
		
		Boolean isAccessoryEnough = this.isAccessoryEnough(productId, productNum);
		if(!isAccessoryEnough){
			
			return false;
		}
		
		Boolean isTimeEnough = this.isTimeEnough();
		if(!isTimeEnough){
			
			return false;
		}
		
		
		return true;
	}

	// L1层判断
	@Override
	public Boolean secondLogic(String productId) {

		logger.info("开始进行第L1层判断");
		
		return true;
	}

	// L2层判断
	@Override
	public Boolean thirdLogic(String productId) {

		logger.info("开始进行第L2层判断");

		
		return true;
	}


	
	
	// 判断产品需要的机器和功能是否正常
	private Boolean isMachineAndFunctionOk(String productId)
	{
		
		return true;
	}
	
	// 判断产品所需的物料是否足够
	private Boolean isMaterialEnough(String productId,int productNum)
	{	
		
		return true;
	}
	
	// 判断产品所需采购的配件是否足够
	private Boolean isAccessoryEnough(String productId,int productNum)
	{	
		
		return true;
	}
	
	private Boolean isTimeEnough()
	{
		int h = Integer.valueOf(PropUtils.read("dayHourLength"));
		
		
		return true;
	}
}
