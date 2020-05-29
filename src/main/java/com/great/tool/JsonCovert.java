package com.great.tool;

import net.sf.json.JSONObject;

public class JsonCovert {

	public static JSONObject filterNull(JSONObject jsonObject) {
		String jsonObjectString = jsonObject.toString();
		jsonObjectString = jsonObjectString.replaceAll(":null,", ":\"\",");
		jsonObjectString = jsonObjectString.replaceAll(":null", ":\"\"");
		jsonObjectString = jsonObjectString.replaceAll(",null", ",\"\"");
		jsonObject = JSONObject.fromObject(jsonObjectString);
		return jsonObject;
	}
}
