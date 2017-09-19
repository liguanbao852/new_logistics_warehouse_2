package com.lwh.common.queueUtil;

public class Queue { 

	private static final int MAX_SIZE = 10;
    private Object[] queue;        //队列
    private int front;             //头指针
    private int rear;              //尾指针
    private int length;            //队列初始化长度
    
    
    
    //初始化队列
    private void init(){
        queue = new Object[this.length + 1]; 
        front = rear = 0;
    }
    //入队
    public void put(Object object) throws Exception{
        if(isFull()){
        	System.out.println("队列已满!重新初始化");   
        	
        	Object[] oldQueue = queue;
        	this.length = this.length * 2;      	
        	this.init();
        	for(int i=0; i<oldQueue.length; i++)
        	{
        		this.put(oldQueue[i]);
        	}
        }
        queue[rear] = object;
        rear = (rear + 1) % queue.length;
    }
    
    // 获取队头元素
    public Object getFront() throws Exception{
    	
    	if(isEmpey()){
            throw new Exception("出队失败!队列为空!");
        }
        Object object = queue[front];
        
        return object;
    }
    
    //出队
    public Object get() throws Exception{
        if(isEmpey()){
            throw new Exception("出队失败!队列为空!");
        }
        Object object = queue[front];
        queue[front] = null;  //释放对象
        front = (front + 1) % queue.length;
        return object;
    }
    //清空队列
    public void clear(){
        queue = null;
        queue = new Object[this.length];
    }
    //获得队列当前大小
    public int size(){
        return (rear - front + queue.length ) % queue.length;
    }
    //判断队列是否已满
    public boolean isFull(){
        return (rear + 1) % queue.length == front;
    }
    //判断队列是否为空
    public boolean isEmpey(){
        return front == rear;
    }
    public Queue(){
        this.length = MAX_SIZE;
        init();
    }
    public Queue(int length){
        this.length = length;
        init();
    }
}