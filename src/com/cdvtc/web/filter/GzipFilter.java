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

//解决全站的压缩问题
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
		
		chain.doFilter(request, responseWrapper);  //servlet调用的response的getOutputStream的writer方法将写入到自己定义的缓冲区而不是浏览器，然后再在filter里将数据压缩后打给浏览器
		
		//将流压缩后写入浏览器
		byte[] out = responseWrapper.getBuffer();
		
		System.out.println("filter压缩前的大小"+out.length);
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		
		gout.write(out);
		gout.close();
		
		byte[] gzip = bout.toByteArray();
		
		response.setHeader("content-encoding", "gzip");
		response.setContentLength(gzip.length);
		
		response.getOutputStream().write(gzip);//调用tomcat原始的response将压缩后的数据写入浏览器
	
		System.out.println("压缩后的大小："+gzip.length);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}


