package com.lwh.common.user;

/**
 * 钱来网校验提示码
 * @author zy
 *
 */
public enum ValidInfoCode {

	//####手机号区#####
	/**
	 * 手机号为空
	 */
	QLWUP0001("QLWUP0001", "手机号为空"),
	
	/**
	 * 请输入正确的手机号(手机号格式不对)
	 */
	QLWUP0002("QLWUP0002", "请输入正确的手机号"),
	
	/**
	 * 手机号已注册
	 */
	QLWUP0003("QLWUP0003", "手机号已注册"),
	
	//#####密码区#####
	/**
	 * 密码为空
	 */
	QLWPW0001("QLWPW0001", "密码为空"),
	
	/**
	 * 密码校验失败
	 */
	QLWPW0002("QLWPW0002", "密码校验失败"),
	
	/**
	 * 密码不一致
	 */
	QLWPW0006("QLWPW0006", "密码不一致"),
	
	/**
	 * 密码长度：6-11
	 */
	QLWPW0007("QLWPW0007", "密码长度：6-11"),
	
	/**
	 * 支付密码不能与登陆密码一致
	 */
	QLWPW0008("QLWPW0008", "支付密码不能与登陆密码一致"),
	
	/**
	 * 表单提交方式非post方式提交
	 */
	QLWPW0003("QLWPW0003", "提交方式非POST方式，执行无效"),
	
	/**
	 * 提交表单数据有空字段，提交无效
	 */
	QLWPW0004("QLWPW0004", "提交表单数据有空字段，提交无效"),
	
	/**
	 * "密码修改成功"
	 */
	QLWPW0005("QLWPW0005", "密码修改成功"),
	
	/**
	 *密码修改失败
	 */
	QLWPW0009("QLWPW0009", "密码修改失败"),
	//#####图片验证码区#####
	/**
	 * 图片验证码为空
	 */
	QLWPIC0001("QLWPIC0001","图片验证码为空"),
	
	/**
	 * 图片验证码校验失败
	 */
	QLWPIC0002("QLWPIC0002","图片验证码校验失败"),
	
	/**
	 * 图片验证码校验成功
	 */
	QLWPIC0003("QLWPIC0003","图片验证码校验成功"),
	
	/**
	 * 图片验证码失效
	 */
	QLWPIC0004("QLWPIC0004","图片验证码失效"),
	
	//#####短信验证码区#####
	/**
	 * 短信验证码为空
	 */
	QLWSMS0001("QLWSMS0001", "短信验证码为空"),
	
	/**
	 * 短信验证码校验失败
	 */
	QLWSMS0002("QLWSMS0002", "短信验证码校验失败"),
	
	/**
	 * 您的渠道有误(无短信隐藏码)
	 */
	QLWSMS0003("QLWSMS0003", "您的渠道有误"),
	
	/**
	 * 短信发送成功
	 */
	QLWSMS0004("QLWSMS0004", "短信发送成功"),
	
	/**
	 * 短信发送失败
	 */
	QLWSMS0005("QLWSMS0005", "短信发送失败"),
	
	/**
	 * 短信验证码失效
	 */
	QLWSMS0006("QLWSMS0006", "短信验证码失效"),
	
	//#####业务参数区#######
	/**
	 * pageId为空
	 */
	QLWBS0001("QLWBS0001", "pageId为空"),
	
	//#####邀请码区#######
	/**
	 * 邀请码错误
	 */
	QLWIVT0001("QLWIVT0001","邀请码错误"),
	
	
	//#####系统异常区#####
	/**
	 * 系统异常
	 */
	QLWEXC0001("QLWEXC0001", "系统异常"),
	
	//#####业务执行结果描述区#####
		//##注册##
	    /**
	     * 注册成功
	     */
	    QLWBS_RG0001("QLWBS_RG0001","注册成功"),
		/**
		 * 注册失败
		 */
		QLWBS_RG0002("QLWBS_RG0002", "注册失败"),
			
		//##登录##
		/**
		 * 登录成功
		 */
		QLWBS_LG0001("QLWBS_LG0001","登录成功"),
		/**
		 * 账号或密码为空
		 */
		QLWBS_LG0002("QLWBS_LG0002", "账号或密码为空"),
		/**
		 * 账号或密码错误
		 */
		QLWBS_LG0003("QLWBS_LG0003", "账号或密码错误"),
		/**
		 * 账号或密码错误
		 */
		QLWBS_LG0004("QLWBS_LG0004", "目前为非交易时间，请在交易时间登陆"),
	
	//#####非法操作(直接攻击接口或绕过页面规定的流程直接攻击)#####
	/**
	 * 非法操作或攻击接口
	 */
	QLWHK0001("QLWHK0001","非法操作或攻击接口"),
	
	
	//#####身份证信息认证(直接攻击接口或绕过页面规定的流程直接攻击)#####
	/**
	 * 姓名不能为空
	 */
	QLWIDC0001("QLWIDC0001","姓名不能为空"),
	
	
	/**
	 * 身份证不能为空
	 */
	QLWIDC0002("QLWIDC0002","身份证不能为空"),
	
	/**
	 * 实名认证次数太多，请明天再试
	 */
	QLWIDC0004("QLWIDC0004","实名认证次数太多，请明天再试"),
	
	
	/**
	 * 未实名认证
	 */
	QLWIDC0006("QLWIDC0006","未实名认证"),
	
	/**
	 * 实名认证信息错误
	 */
	QLWIDC0005("QLWIDC0005","实名认证信息错误"),
	
	/**
	 * 身份证号不足18位
	 */
	QLWIDC0003("QLWIDC0003","身份证号不足18位"),
	
	
	QLWRG0001("QLWRG0001","您已经领取过奖品啦！");
	
	
	
	private String code;
	
	private String desc;

	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	private ValidInfoCode(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
}
