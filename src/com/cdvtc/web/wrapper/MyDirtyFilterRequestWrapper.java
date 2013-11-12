package com.cdvtc.web.wrapper;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyDirtyFilterRequestWrapper extends HttpServletRequestWrapper {

	private List<String> dirtyWords = Arrays.asList("ÉµB","ÐóÉú","²Ùµ°","ÄãÂèB");
	
	public MyDirtyFilterRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		for(String dirtyWord:dirtyWords){
			if(value.contains(dirtyWord)){
				value = value.replace(dirtyWord, "xxx");
			}
		}
		return value;
	}

}
