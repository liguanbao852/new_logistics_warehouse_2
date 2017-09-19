package com.lwh.common.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.lwh.common.constants.ISystemCache;


public class SystemCacheInit implements BeanPostProcessor {
	private static Logger logger = Logger.getLogger(SystemCacheInit.class);

	@Override
	public Object postProcessAfterInitialization(Object obj, String beanName)
			throws BeansException {
		if (obj instanceof ISystemCache) {
			logger.info("初始化系统缓存 begin..." + beanName);
			ISystemCache handle = (ISystemCache) obj;
			handle.init();
			logger.info("初始化系统缓存 end");
		}
		return obj;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		// TODO Auto-generated method stub
		return arg0;
	}

}
