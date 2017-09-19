package com.lwh.common.page;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class NumberFormatter extends TagSupport{
	
	private static final long serialVersionUID = -7353949797756710295L;
	
	private static Logger logger = Logger.getLogger(HiddenInfo.class);
	
	private Object number;

	public Object getNumber() {
		return number;
	}

	public void setNumber(Object number) {
		this.number = number;
	}

	@Override
	public int doStartTag() throws JspException {
		return PageTag.SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		//定义局部变量
		String result = "";
		String _number = String.valueOf(number);
		if(_number.contains("[\"") || _number.contains("\"]")){
			_number = _number.replace("[\"", "");
			_number = _number.replace("\"]", "");
		}
		JspWriter writer = this.pageContext.getOut();
		boolean flag = false;
		//定义局部变量结束
		//开始转换数字格式
		if(_number.startsWith("-")){
			flag = true;
			_number = _number.replace("-", "");
		}
		if(_number != null && !_number.equalsIgnoreCase("null")){
			//传入的数字带有小数点，为浮点数
			if(_number.indexOf(".") > 0){
				String integerPart = _number.substring(0, _number.indexOf("."));
				String floatPart = _number.substring(_number.indexOf("."), _number.length());
				char[] content = integerPart.toCharArray();
				content = this.reverse(content);
				for(int i = 0;i < content.length;i++){
					if(i % 3 == 0 && i != 0){
						result = result + "," + content[i];
					}else{
						result = result + content[i];
					}
					if(i == content.length - 1){
						break;
					}
				}
				if(flag == false){
					result = new String(this.reverse(result.toCharArray())) + floatPart;
				}else{
					result = "-" + new String(this.reverse(result.toCharArray())) + floatPart;
				}
				
			}else{
				//传入的数字不带小数点，为整形
				String integerPart = _number;
				char[] content = integerPart.toCharArray();
				content = this.reverse(content);
				for(int i = 0;i < content.length;i++){
					if(i % 3 == 0 && i != 0){
						result = result + "," + content[i];
					}else{
						result = result + content[i];
					}
					if(i == content.length - 1){
						break;
					}
				}
				if(flag == false){
					result = new String(this.reverse(result.toCharArray()));
				}else{
					result = "-" + new String(this.reverse(result.toCharArray()));
				}
			}
		}else{
			logger.error("传入的数字为空，系统无法进行转换");
		}
		//转换数字格式结束
		try {
			writer.write(result);
		} catch (IOException e) {
			logger.error(e);
		}
		return PageTag.EVAL_PAGE;
	}
	
	private char[] reverse(char[] target){
		char[] _temp = new char[target.length];
		for(int i = 0,y = target.length - 1;i < target.length;i++,y--){
			_temp[y] = target[i];
		}
		return _temp;
	}

	@Override
	public void release() {
		super.release();
	}
	
}
