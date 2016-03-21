package com.baiyi.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {

	public static void main(String[] args) {

	}

	static void test(){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("数据库驱动管理创建成功");
		} catch (SQLException e) {
			System.out.println("数据库驱动管理创建失败");
		}
		
		
	}
	
}
