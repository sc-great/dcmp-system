package com.great.tool;

import org.springframework.util.StringUtils;

public class AreaUtils {
    public static String subStringAreaCode(String areaCode){
    	//(0,2)(2,4)(4,6)(6,9)(9,12)
        String subareaCode=areaCode;
        /*if(areaCode.substring(9,12).equals("000")){
        	subareaCode=areaCode.substring(0,9);
        }
        if(areaCode.substring(6,9).equals("000")){
            subareaCode=areaCode.substring(0,6);
        }
        if(areaCode.substring(4,6).equals("00")){
            subareaCode=areaCode.substring(0,4);
        }
        if(areaCode.substring(2,4).equals("00")){
            subareaCode=areaCode.substring(0,2);
        }*/
        return  subareaCode;
    }
    /**
     * 直属父子级关系
     * @param parentCode
     * @param childrenCode
     * @return
     */
    public static boolean isParentWithSon(String parentCode,String childrenCode){
    	if(StringUtils.isEmpty(parentCode)||StringUtils.isEmpty(childrenCode)){
    		return false;
    	}
    	String subChildren=subStringAreaCode(childrenCode);
    	String subParent=subStringAreaCode(parentCode);
    	if(subChildren.length()<=subParent.length()){
    		return false;
    	}
    	if(subChildren.substring(0, subParent.length()).equals(subParent)){
    		int num=subChildren.length()-subParent.length();
    		if(num>4){
    			return false;
    		}else{
    			return true;
    		}
    	}else{
    		return false;
    	}
    }
    /**
     * 多级父子关系
     * @param parentCode
     * @param childrenCode
     * @return
     */
    public static boolean isParent(String parentCode,String childrenCode){
    	if(StringUtils.isEmpty(parentCode)||StringUtils.isEmpty(childrenCode)){
    		return false;
    	}
    	String subChildren=subStringAreaCode(childrenCode);
    	String subParent=subStringAreaCode(parentCode);
    	if(subChildren.length()<=subParent.length()){
    		return false;
    	}
    	if(subChildren.substring(0, subParent.length()).equals(subParent)){
    		return true;
    	}else{
    		return false;
    	}
    }
}
