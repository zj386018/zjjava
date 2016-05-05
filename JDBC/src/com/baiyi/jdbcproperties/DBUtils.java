package com.baiyi.jdbcproperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBUtils {
	private static Properties pro = new Properties();
	private static String driver;
	private static String username;
	private static String password;
	private static String url;
	static {
		String path = DBUtils.class.getResource("").getPath() + "db.properties";
		InputStream io = null;
		try {
			io = new FileInputStream(path);
			pro.load(io);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				io.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		driver = pro.getProperty("driver");
		username = pro.getProperty("username");
		password = pro.getProperty("password");
		url = pro.getProperty("url");
	}

	public static void main(String[] args) {
		System.out.println(driver);
		System.out.println(username);
		System.out.println(password);
		System.out.println(url);
	}
	
}
