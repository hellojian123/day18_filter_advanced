package com.cdvtc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo4 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String value = "�й�svfasdsfdasdfasdfasdcasdfasdfasdfadsfasdfasdfasdfasdfasdffj;";
		System.out.println("ѹ��ǰ�Ĵ�С"+value.getBytes().length);
		response.getOutputStream().write(value.getBytes());
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
