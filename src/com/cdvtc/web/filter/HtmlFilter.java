package com.cdvtc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdvtc.web.wrapper.MyHtmlFilterRequestWrapper;

//html×ªÒåfilter
public class HtmlFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		MyHtmlFilterRequestWrapper requestWrapper = new MyHtmlFilterRequestWrapper(req);
		
		chain.doFilter(requestWrapper, res);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
