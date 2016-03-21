package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {

	public static void main(String[] args) {

	}

	static void test(){
		
		try {
			//注册驱动
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("数据库驱动管理创建成功");
		} catch (SQLException e) {
			System.out.println("数据库驱动管理创建失败");
		}
		
		//建立连接
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "", "");
		} catch (SQLException e) {
			System.out.println("建立数据库连接失败");
		}
		
		
		
		
	}
	
}
