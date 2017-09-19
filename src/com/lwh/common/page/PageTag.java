package com.lwh.common.page;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * 
 * @author yeyu
 * 
 */
public final class PageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(PageTag.class);

	// 请求地址
	private String submitUrl;

	// 当前页
	private int currentPage;
	// 每页显示个数
	private int pageSize;
	// 总记录数
	private int totalSize;

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	/**
	 * 获取总页数
	 */
	public int getTotalPage() {
		if (totalSize < pageSize) {
			return 1;
		}
		int page = totalSize / pageSize;
		int pageInt = totalSize % pageSize;
		if (pageInt > 0) {
			return page + 1;
		} else {
			return page;
		}
	}

	@Override
	public int doStartTag() throws JspException {
		return PageTag.SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		// 总页数
		int totalPage = this.getTotalPage();

		// url连接符
		String prefix = submitUrl.contains("?") ? "&" : "?";

		StringBuilder builder = new StringBuilder();
		if (totalSize != 0) {
			builder.append("<div class=\"fen_msg\">");
			if (currentPage != 0 && currentPage != 1) {
				builder.append("<a href=\"").append(submitUrl).append(prefix)
						.append("currentPage=1&pageSize=").append(pageSize)
						.append("\">首页</a>");
				builder.append("<a href=\"").append(submitUrl).append(prefix)
						.append("currentPage=").append(currentPage - 1)
						.append("&pageSize=").append(pageSize)
						.append("\">上一页</a>");
			} else {
				builder.append("<a href=\"javascript:void(0)\"").append(
						"\">首页</a>");
			}
			int end = currentPage + pageSize < totalPage ? currentPage
					+ pageSize : totalPage;
			int begin = end - pageSize > 0 ? end - pageSize : 1;
			for (int i = begin; i <= end; i++) {
				if (currentPage == i) {
					builder.append("<a class=\"ijdh_a iucikd_bg\" href=\"")
							.append(submitUrl).append(prefix)
							.append("currentPage=").append(i)
							.append("&pageSize=").append(pageSize)
							.append("\">").append(i).append("</a>");
				} else {
					builder.append("<a class=\"ijdh_a\" href=\"")
							.append(submitUrl).append(prefix)
							.append("currentPage=").append(i)
							.append("&pageSize=").append(pageSize)
							.append("\">").append(i).append("</a>");
				}
			}
			if (currentPage != totalPage) {
				builder.append("<a href=\"").append(submitUrl).append(prefix)
						.append("currentPage=").append(currentPage + 1)
						.append("&pageSize=").append(pageSize)
						.append("\">下一页</a>");
				builder.append("<a href=\"").append(submitUrl).append(prefix)
						.append("currentPage=").append(totalPage)
						.append("&pageSize=").append(pageSize)
						.append("\">末页</a>");
			} else {
				builder.append("<a href=\"javascript:void(0)\"").append(
						"\">末页</a>");
			}
			builder.append("<span>共").append(totalPage).append("页</span>");
			builder.append("<span>").append(totalSize).append("条记录</span>");
			builder.append("<span>转到<select id=\"selectPage\" onchange=\"javascript:toSelectedPage(this, '"
					+ submitUrl + "'," + pageSize + ");\">");
			for (int i = 1; i <= totalPage; i++) {
				if (currentPage == i) {
					builder.append("<option selected value=\"").append(i)
							.append("\">");
				} else {
					builder.append("<option value=\"").append(i).append("\">");
				}
				builder.append(i);
				builder.append("</option>");
			}
			builder.append("</select> 页</span>");
			builder.append("</div>");

			// 构造分页标签HTML结束
			JspWriter out = this.pageContext.getOut();
			try {
				out.write(builder.toString());
			} catch (IOException e) {
				logger.info(e);
			}
		}
		return PageTag.EVAL_PAGE;

	}

	@Override
	public void release() {
		super.release();
	}

}
