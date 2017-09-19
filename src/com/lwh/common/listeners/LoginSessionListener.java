package com.lwh.common.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lwh.user.entity.UserInfo;




/**
* 单点登录
* 如果你在别的其他机器上已经登录，系统提示账号已经登录，不能登录
* @author Administrator
*
*/
public class LoginSessionListener implements HttpSessionAttributeListener {

   Log log = LogFactory.getLog(this.getClass());
   
   Map<String,HttpSession> map = new HashMap<String,HttpSession>();
   
   //session属性添加的时候被调用
   public void attributeAdded(HttpSessionBindingEvent event) {
       // TODO Auto-generated method stub
       String name = event.getName();
       if(name=="userInfo"){
           UserInfo userInfo = (UserInfo)event.getValue();
           
           if(map.get(userInfo.getUserId())!=null){
               HttpSession session = map.get(userInfo.getUserId());
               
               UserInfo userInfoOld = (UserInfo)session.getAttribute("userInfo");
               
               session.setAttribute("sms","账号"+userInfoOld.getUserId()
                       +"已经在其他地方登录，你被迫下线");
               
               session.removeAttribute("userInfo");
           }
           
           //将session以用户名为索引放入session
           map.put(userInfo.getUserId(), event.getSession());
       }
   }

   //session属性删除的时候调用
   public void attributeRemoved(HttpSessionBindingEvent event) {
       // TODO Auto-generated method stub

       String name = event.getName();
       if(name.equals("userInfo")){
    	   UserInfo userInfo = (UserInfo)event.getValue();
           //移除的session在map中移除
           map.remove(userInfo.getUserId());
           log.info("账号"+userInfo.getUserId()+"已经退出。");
       }
   }

   //session属性修改的时候调用
   public void attributeReplaced(HttpSessionBindingEvent event) {
       // TODO Auto-generated method stub
       
       String name = event.getName();
       if(name.equals("userInfo")){
           UserInfo oldUserInfo = (UserInfo)event.getValue();
           //移除旧的登录信息
           map.remove(oldUserInfo.getUserId());
           
           //新的的登录信息
           UserInfo userInfo = (UserInfo)event.getSession().getAttribute("userInfo");
           
           //判断是否在别的机器上登录过
           if(map.get(userInfo.getUserId())!=null){
               HttpSession session = map.get(userInfo.getUserId());
               session.removeAttribute("userInfo");
               session.setAttribute("sms", "你的账号在别的机器上登录，你被迫下线。");
           }
           map.put(userInfo.getUserId(), event.getSession());
       }
   }

}