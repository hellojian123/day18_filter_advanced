package com.cdvtc.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//gzipѹ���㷨
public class ServletDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message = "�й�asdfadsfgasdddghsdggsdgfszgvbsghsdgfvshsdgfsdfghsdfgsgs";
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		
		System.out.println("ԭʼ��С"+message.getBytes().length);
		
		gout.write(message.getBytes());
		gout.close();
		
		
		byte[] gzip = bout.toByteArray();
		
		response.setHeader("content-encoding", "gzip");
		response.setContentLength(gzip.length);
		
		response.getOutputStream().write(gzip);
		
		System.out.println("ѹ�����С"+gzip.length);
		
	
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
