package com.cdvtc.web.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHtmlFilterRequestWrapper extends HttpServletRequestWrapper {

	public MyHtmlFilterRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String value = super.getParameter(name);
		if (value == null) {
			return null;
		}
		value = filter(value);
		return value;
	}

	public String filter(String message) {
		if (message == null)
			return (null);
		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuilder result = new StringBuilder(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
			}
		}
		return (result.toString());
	}

}
