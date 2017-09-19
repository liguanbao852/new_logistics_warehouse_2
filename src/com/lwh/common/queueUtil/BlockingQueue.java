package com.lwh.common.queueUtil;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {

	  private List queue = new LinkedList();
	  private int  limit = 100;

	  public BlockingQueue(int limit){
	    this.limit = limit;
	  }


	  public synchronized void enqueue(Object item)
	  throws InterruptedException  {
	    while(this.queue.size() == this.limit) {
	      wait();
	    }
	    if(this.queue.size() == 0) {
	      notifyAll();
	    }
	    this.queue.add(item);
	  }


	  public synchronized Object dequeue()
	  throws InterruptedException{
	    while(this.queue.size() == 0){
	      wait();
	    }
	    if(this.queue.size() == this.limit){
	      notifyAll();
	    }

	    return this.queue.remove(0);
	  }

		//获得队列当前大小
	    public int size(){
	        return this.queue.size();
	    }
	    
	    // 获取队头元素
	    public Object getFront() throws Exception{
	    	
	    	Object object = null;
	    	if(this.queue.size() > 0)
	    	{
	    		object = this.queue.get(0);
	    	}
	    	
	        return object;
	    }
	  
	}