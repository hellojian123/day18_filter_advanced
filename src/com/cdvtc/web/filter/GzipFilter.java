package com.cdvtc.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdvtc.web.wrapper.MyBufferResponseWrapper;

//���ȫվ��ѹ������
public class GzipFilter implements Filter {

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
		
		MyBufferResponseWrapper responseWrapper = new MyBufferResponseWrapper(response);
		
		chain.doFilter(request, responseWrapper);  //servlet���õ�response��getOutputStream��writer������д�뵽�Լ�����Ļ������������������Ȼ������filter�ｫ����ѹ�����������
		
		//����ѹ����д�������
		byte[] out = responseWrapper.getBuffer();
		
		System.out.println("filterѹ��ǰ�Ĵ�С"+out.length);
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		
		gout.write(out);
		gout.close();
		
		byte[] gzip = bout.toByteArray();
		
		response.setHeader("content-encoding", "gzip");
		response.setContentLength(gzip.length);
		
		response.getOutputStream().write(gzip);//����tomcatԭʼ��response��ѹ���������д�������
	
		System.out.println("ѹ����Ĵ�С��"+gzip.length);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}


