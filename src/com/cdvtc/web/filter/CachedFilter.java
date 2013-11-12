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


//�������ݵ��ڴ�
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
		
		//1,�õ��û������uri
		String uri = request.getRequestURI();		
		
		//2������������û��uri��Ӧ������
		byte[] value = cached.get(uri);
		
		//3������������У�ֱ���û������ݴ������������򷵻أ�
		if(value!=null){
			response.getOutputStream().write(value);
			return;
		}
		
		//4�����������û�У���Ŀ����Դִ�У�������Ŀ����Դ�������
		MyBufferResponseWrapper myResponse = new MyBufferResponseWrapper(response);
		chain.doFilter(request, myResponse);
		byte[] out = myResponse.getBuffer();		
		
		//5���Ѳ�������Դ���������û������uriΪ�ؼ��ֱ��浽�����У�
		cached.put(uri, out);
		
		//6�������ݴ�������
		response.getOutputStream().write(out);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
