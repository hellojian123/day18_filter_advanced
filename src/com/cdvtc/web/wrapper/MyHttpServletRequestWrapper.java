package com.cdvtc.web.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private HttpServletRequest request;
	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		try {
			String value = this.request.getParameter(name);
			if(value==null)
				return null;
			if(!this.request.getMethod().equalsIgnoreCase("get")){
				return value;
			}
			value = new String(value.getBytes("ISO-8859-1"),this.request.getCharacterEncoding());
			return value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
