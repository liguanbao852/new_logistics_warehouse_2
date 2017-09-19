package com.lwh.common.page;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author yeyu
 */
public class HiddenInfo extends TagSupport {
	private static Logger logger = Logger.getLogger(HiddenInfo.class);
	// 需要隐藏的内容
	private String hidden_value;

	public String getHidden_value() {
		return hidden_value;
	}

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setHidden_value(String hidden_value) {
		this.hidden_value = hidden_value;
	}

	private static final long serialVersionUID = 6861025317140047720L;

	@Override
	public int doStartTag() throws JspException {
		return PageTag.SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		// 定义局部变量
		String rtn_value = null;
		// 定义局部变量结束
		JspWriter writer = this.pageContext.getOut();
		// 开始处理字符串
		if (type == 0) {
			rtn_value = this.userNameHandler(hidden_value);
		} else if (type == 1) {
			rtn_value = this.idcardHandler(hidden_value);
		} else if (type == 12) {
			rtn_value = this.idcardHandlerExt(hidden_value);
		} else if (type == 2) {
			rtn_value = this.phoneNumberHandler(hidden_value);
		} else if (type == 13) {
			rtn_value = this.nameHandler(hidden_value);
		} else if (type == 14) {
			rtn_value = this.idcardHandlerExt(hidden_value);
		} else if (type == 15) {
			rtn_value = this.emailHander(hidden_value);
		} else if (type == 16) {
			rtn_value = this.carInfoHandler(hidden_value);
		} else if (type == 17) {
			rtn_value = this.utilString(hidden_value);
		} else {
			rtn_value = this.phoneNumberHandler(hidden_value);
		}
		// logger.info(rtn_value);
		try {
			writer.write(rtn_value);
		} catch (IOException e) {
			logger.error(e);
		}
		// 处理字符串结束
		return PageTag.EVAL_PAGE;
	}

	private String carInfoHandler(String carInfo) {
		if (carInfo.length() <= 8) {
			return carInfo;
		} else {
			String front = carInfo.substring(0, carInfo.length() - 6) + "****"
					+ carInfo.substring(carInfo.length() - 2, carInfo.length());
			return front;
		}
	}

	private String userNameHandler(String userName) {
		return null;
	}

	/**
	 * 处理身份证号码信息，保留前3位和后4位明文，中间使用4位*字符代替
	 * 
	 * @param idcard
	 * @return
	 */
	private String idcardHandler(String idcard) {
		if (StringUtils.isBlank(idcard) || idcard.length() < 7) {
			return idcard;
		}
		String front = idcard.substring(0, 3);
		String rear = idcard.substring(idcard.length() - 4, idcard.length());
		String hiddenInfo = front + "****" + rear;
		return hiddenInfo;
	}

	/**
	 * 只显示身份证后四位
	 * 
	 * @param idcard
	 * @return
	 */
	private String idcardHandlerExt(String idcard) {
		String rear = idcard.substring(idcard.length() - 4, idcard.length());
		return "**" + rear;
	}

	/**
	 * 只显示名字第一位
	 * 
	 * @param name
	 * @return
	 */
	private String nameHandler(String name) {
		String rear = name.substring(0, 1);
		return rear + "**";
	}

	/**
	 * 只显示名字第一位(由于 数据格式为 ["123"] 类型 故从第二位开始取)
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unused")
	private String nameHandlerExt(String name) {
		String rear = name.substring(2, 3);
		return rear + "**";
	}

	private String phoneNumberHandler(String phoneNum) {
		// 处理身份证号码信息，保留前3位和后4位明文，中间使用4位*字符代替
		String front = phoneNum.substring(0, 3);
		String rear = phoneNum.substring(phoneNum.length() - 4,
				phoneNum.length());
		String hiddenInfo = front + "****" + rear;
		return hiddenInfo;
	}

	// 金钱千位显示
	private String utilString(String str) {
		if (str == null) {
			str = "0";
		}
		BigDecimal bd = new BigDecimal(str);
		DecimalFormat df = new DecimalFormat(",###,##0.00");
		return df.format(bd);

	}

	/**
	 * 处理邮箱，前两个字符及@后面字符不隐藏
	 * 
	 * @param email
	 * @return
	 */
	private String emailHander(String email) {
		String[] emails = email.split("@");
		String head = emails[0].substring(0, 2) + "***";
		return head + "@" + emails[1];
	}

	@Override
	public void release() {
		super.release();
	}

}
