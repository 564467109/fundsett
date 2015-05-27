package com.uusoft.fund.common;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils {
	
	public static final String SUCCESS="success";
	public static final String CODE="code";
	public static final String MSG="msg";
	public static final String CONTENT="content";
	/**
	 * 用于接口之间相互传递
	 * @param success
	 * @param code
	 * @param msg
	 * @param content
	 * @return
	 */
	public static Map<String,Object> messageMap(boolean success,int code,String msg,Object content){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put(SUCCESS, success);
		resultMap.put(CODE, code);
		resultMap.put(MSG, msg);
		resultMap.put(CONTENT, content);
		return resultMap;
	}
}
