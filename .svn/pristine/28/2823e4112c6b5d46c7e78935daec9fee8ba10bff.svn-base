package com.great.tool;

import com.alibaba.fastjson.JSONObject;

public class UrlUtils {
    public static String parseUrl(String serverUrl,int type){
        String url="";
        JSONObject obj=JSONObject.parseObject(serverUrl);
        if(type==1){
        	url=obj.getString("localUrl");
        }else if(type==0){
        	url=obj.getString("netUrl");
        }
        return url;
    }
    
}
