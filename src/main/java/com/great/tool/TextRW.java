package com.great.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class TextRW {

	public static StringBuffer readTxtFile(String filePath) {
		StringBuffer redTxtContent = new StringBuffer();
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					redTxtContent.append(lineTxt);
				}
				read.close();
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return redTxtContent;
	}

	public static void writeTxtFile(String content, String filePath) throws Exception {
		FileOutputStream o = null;
		File file = new File(filePath);
		try {
			o = new FileOutputStream(file);
			o.write(content.getBytes("UTF-8"));
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
