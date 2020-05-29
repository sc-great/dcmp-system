package com.great.tool;

public class UUID {
	
	
	public static String randomUUID(){
		String uuid = java.util.UUID.randomUUID().toString();
		uuid = uuid.toUpperCase();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
	
	public static void main(String[] agr) {
		for (int i = 0; i < 10; i++) {
			System.out.println(UUID.randomUUID());
		}
	}
}
