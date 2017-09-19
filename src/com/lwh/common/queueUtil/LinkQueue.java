package com.lwh.common.queueUtil;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkQueue { 
  
	private static Logger logger = LoggerFactory.getLogger(LinkQueue.class);
	
    private List queue = new LinkedList();
    
    //入队
    @SuppressWarnings("unchecked")
	public synchronized void put(Object object) throws Exception{
        
    	queue.add(object);
    }
    
    // 获取队头元素
    public Object getFront() throws Exception{
    	
    	Object object = null;
    	if(isEmpey()){
    		logger.info("队列为空,无法获取对头元素");		
        }
    	else
    	{
    		object = queue.get(0);
    	}
    
        return object;
    }
    
    //出队
    public synchronized Object get() throws Exception{
    	 Object object = null;
        if(isEmpey()){
        	logger.info("出队失败!队列为空!");
        }
        else
        {
        	object = this.queue.remove(0);
        }
        
        return object;
    }
    
    //清空队列
    public void clear(){
        queue = null;
        queue = new LinkedList();
    }
    
    //获得队列当前大小
    public  int size(){
        return this.queue.size();
    }
   
    //判断队列是否为空
    public boolean isEmpey(){
    	if( this.queue.size() == 0)
    	{
    		return true;
    	}
        return false;
    }
  
    
    public static void main(String[] args) {
    	
    	double openPrice = 1.7976931348623157E3;
		logger.info("openPrice: " + openPrice);
		BigDecimal s = new BigDecimal(openPrice).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
		logger.info("pDepthMarketData.OpenPrice: " + s.toString());
    }
}