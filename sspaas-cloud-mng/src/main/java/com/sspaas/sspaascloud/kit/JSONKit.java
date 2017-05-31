package com.sspaas.sspaascloud.kit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * service 通用方法
 * @author CoderKe
 *
 */
public class JSONKit {
	static	Logger log  =  Logger.getLogger(JSONKit.class);
	static Gson gson = new Gson();
	/**封装json通用map*/
	private static Map<String, Object> jsonMap = new HashMap<String, Object>();
	
	/**
	 * 获取错误信息
	 * @param code 错误代码
	 * @param Msg 错误信息
	 * @return 封装后json
	 */
	public String getErrorMsg(String code,String Msg){
		String errMsg = "{\"ERR\":\""+code+"\",\"MSG\":\""+Msg+"\"}";
		return errMsg;
	}
	
	public void outList(List<?> list, PrintWriter out){
		String jsonStr = gson.toJson(list);
		out.println(jsonStr);
	}
	
	public void objToJson(Object obj, PrintWriter out){
		String jsonStr = gson.toJson(obj);
		log.debug("封装JSON:"+jsonStr);
		out.println(jsonStr);
	}
	
	public String objToJson(Object obj){
		String jsonStr = gson.toJson(obj);
		return jsonStr;
	}
	protected void mapMsgToJson(int code,String msg,Map<String, Object> map,HttpServletResponse resp){
		jsonMap.put("code", code);
		jsonMap.put("msg", msg);
		jsonMap.put("data", map);
		String json = gson.toJson(jsonMap);
		log.debug("封装并输入的json:"+json);
		getPrintWriter(resp).print(json);
	}
	/**
	 * 获取PrintWriter对象
	 * @param response
	 * @return
	 */
	public static PrintWriter getPrintWriter(HttpServletResponse response){
		response.setContentType("text/javascript");//指定MIME类型 json
		response.setCharacterEncoding("utf-8");//指定为utf-8  
		PrintWriter printWriter  = null;
		try {
			printWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("获取PrintWriter对象发生异常："+e.getMessage());
		}
		return printWriter;
	}
	
	
	public static void outMsg(int code,String msg,HttpServletResponse resp){
        jsonMap.put("statusCode", code);
		jsonMap.put("statusMsg", msg);
		String json = gson.toJson(jsonMap);
		log.debug("封装并输入的json:"+json);
		getPrintWriter(resp).print(json);
	}

	public void objToJson(Object obj, HttpServletResponse resp){
		String jsonStr = gson.toJson(obj);
		log.debug("封装JSON:"+jsonStr);
		getPrintWriter(resp).println(jsonStr);
	}
}
