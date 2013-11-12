package com.cdvtc.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdvtc.web.wrapper.MyBufferResponseWrapper;


//缓存数据到内存
public class CachedFilter implements Filter {

	private Map<String,byte[] > cached = new HashMap<String,byte[] >();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//1,得到用户请求的uri
		String uri = request.getRequestURI();		
		
		//2，看缓存中有没有uri对应的数据
		byte[] value = cached.get(uri);
		
		//3，如果缓存中有，直接拿缓存数据打给浏览器，程序返回，
		if(value!=null){
			response.getOutputStream().write(value);
			return;
		}
		
		//4，如果缓存中没有，让目标资源执行，并捕获目标资源的输出。
		MyBufferResponseWrapper myResponse = new MyBufferResponseWrapper(response);
		chain.doFilter(request, myResponse);
		byte[] out = myResponse.getBuffer();		
		
		//5，把捕获后的资源的数据以用户请求的uri为关键字保存到缓存中，
		cached.put(uri, out);
		
		//6，把数据打给浏览器
		response.getOutputStream().write(out);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
