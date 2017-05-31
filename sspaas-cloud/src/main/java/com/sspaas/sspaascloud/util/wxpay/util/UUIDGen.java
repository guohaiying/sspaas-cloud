package com.sspaas.sspaascloud.util.wxpay.util;

import java.util.UUID;

public class UUIDGen {
	/**
	   * 随机生成UUID
	   * @return
	   */
	  public static String getUUID(){
	    UUID uuid=UUID.randomUUID();
	    String str = uuid.toString(); 
	    String uuidStr=str.replace("-", "");
	    return uuidStr;
	  }
}
