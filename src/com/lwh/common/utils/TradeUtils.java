package com.lwh.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class TradeUtils {
	private static Logger logger = Logger.getLogger(TradeUtils.class);
	
	
	// 判断是否在交易时间段
	public static boolean isTradeTime()
	{		
		Date nowDayTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String nowTime = sdf.format(nowDayTime);
		
		logger.info("判断当前时间["+ nowTime +"]是否为在可交易时间段");
		try{
			Date nowTimeHms = sdf.parse(nowTime);
			
			// 获取交易所服务开放时间
			Date dayTradeBeginTimeHms = sdf.parse(PropUtils.read("dayTradeBeginTime"));
			Date dayTradeEndTimeHms = sdf.parse(PropUtils.read("dayTradeEndTime"));
			Date nightTradeBeginTimeTimeHms = sdf.parse(PropUtils.read("nightTradeBeginTime"));
			Date nightTradeEndTimeHms = sdf.parse(PropUtils.read("nightTradeEndTime"));
	
		
			if(nowTimeHms.after(dayTradeBeginTimeHms) && nowTimeHms.before(dayTradeEndTimeHms))
			{
				return true;
			}
			else if(nowTimeHms.after(nightTradeBeginTimeTimeHms) && nowTimeHms.before(nightTradeEndTimeHms))
			{
				return true;
			}
		}
		catch(Exception e)
		{
			logger.error("time ParseException error: " + e);
		}
		
		return false;
			
	}
	
	// 判断是否在交易所开放时间段
	public static boolean isServerOpenTime()
	{	
		Date nowDayTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String nowTime = sdf.format(nowDayTime);
		
		logger.info("判断当前时间["+ nowTime +"]是否在交易所开放时间段");
		try{
			Date nowTimeHms = sdf.parse(nowTime);
			
			// 获取交易所服务开放时间
			Date dayOpenBeginTimeHms = sdf.parse(PropUtils.read("dayOpenBeginTime"));
			Date dayOpenEndTimeHms = sdf.parse(PropUtils.read("dayOpenEndTime"));
			Date nightOpenBeginTimeHms = sdf.parse(PropUtils.read("nightOpenBeginTime"));
			Date fnightOpenEndTimeHms = sdf.parse("23:59:59");
			Date fnightOpenBeginTimeHms = sdf.parse("00:00:00");
			Date nightOpenEndTimeHms = sdf.parse(PropUtils.read("nightOpenEndTime"));
		
			if(nowTimeHms.after(dayOpenBeginTimeHms) && nowTimeHms.before(dayOpenEndTimeHms))
			{
				return true;
			}
			else if(nowTimeHms.after(nightOpenBeginTimeHms) && nowTimeHms.before(fnightOpenEndTimeHms))
			{
				return true;
			}
			else if(nowTimeHms.after(fnightOpenBeginTimeHms) && nowTimeHms.before(nightOpenEndTimeHms))
			{
				return true;
			}	
		}
		catch(Exception e)
		{
			logger.error("time ParseException error: " + e);
		}
		
		return false;
	}
	
	
	// 1-白天 2-上半夜（20:00:00-23:59:59） 3-下半夜（00:00:00-8:00:00)
	public static int isDayQutionTime()
	{
		Date nowDayTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String nowTime = sdf.format(nowDayTime);
		
		logger.info("判断当前时间["+ nowTime +"]是否要显示白天行情");
		try{
			Date nowTimeHms = sdf.parse(nowTime);
			
			// 获取交易所服务开放时间
			Date dayQuotationBeginTimeHms = sdf.parse(PropUtils.read("dayQuotationBeginTime"));
			Date dayQuotationEndTimeHms = sdf.parse(PropUtils.read("dayQuotationEndTime"));
			Date midNightTimeHms = sdf.parse("23:59:59");
			
			if(nowTimeHms.after(dayQuotationBeginTimeHms) && nowTimeHms.before(dayQuotationEndTimeHms))
			{
				return 1;
			}
			else if(nowTimeHms.after(dayQuotationEndTimeHms) && nowTimeHms.before(midNightTimeHms))
			{
				return 2;
			}
			else
			{
				return 3;
			}
		}
		catch(Exception e)
		{
			logger.error("time ParseException error: " + e);
		}
		
		return 1;
	}
	
	
	public static void main(String[] args) throws ParseException{

		System.out.println("当前交易所是否开放：" + isServerOpenTime());
		System.out.println("当前是否可交易：" + isTradeTime());
		System.out.println("当前是否显示白天行情：" + isDayQutionTime());
		
	}
}
