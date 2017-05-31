package com.sspaas.sspaascloud.controller.commons;


import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.sspaas.sspaascloud.kit.JSONKit;



public class BaseController extends JSONKit {

	/**
	 * 活取ServletOutputStream对象
	 * @param response
	 * @return
	 */
	public ServletOutputStream getOutputStream(HttpServletResponse response){
		ServletOutputStream outptStream  = null;
		try {
			outptStream =response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outptStream;
	}
	
}
