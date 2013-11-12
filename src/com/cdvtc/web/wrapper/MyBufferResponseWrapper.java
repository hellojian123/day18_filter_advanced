package com.cdvtc.web.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyBufferResponseWrapper extends HttpServletResponseWrapper {

	private ByteArrayOutputStream bout = new ByteArrayOutputStream();
	private PrintWriter printWriter = null;
	
	public MyBufferResponseWrapper(HttpServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return new MyServletOutputStream(bout);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		this.printWriter = new PrintWriter(new OutputStreamWriter(bout,super.getResponse().getCharacterEncoding()));
		return this.printWriter;
	}
	
	//获取流数据。
	public byte[] getBuffer(){
		
		try {
			if(this.printWriter!=null){
				this.printWriter.close();
			}			
			if(this.bout!=null){
				this.bout.flush();
				return bout.toByteArray();
			}
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}

class MyServletOutputStream extends ServletOutputStream {

	private ByteArrayOutputStream bout = null;
	
	public MyServletOutputStream(ByteArrayOutputStream bout) {
		super();
		this.bout = bout;
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		this.bout.write(b);
	}
	
}

