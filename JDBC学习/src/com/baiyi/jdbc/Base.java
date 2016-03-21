package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

	
	public static void main(String[] args) {
		Base.test();

	}

	static void test(){
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//注册驱动
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("数据库驱动管理创建成功");
		} catch (SQLException e) {
			System.out.println("数据库驱动管理创建失败");
		}
		
		//建立连接
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zjjdbc", "root", "");
		} catch (SQLException e) {
			System.out.println("建立数据库连接失败");
		}
		
		//创建语句
		try {
			st = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("创建数据库语句失败");
		}
		
		//执行语句
		try {
			rs = st.executeQuery("select * from user");
		} catch (SQLException e) {
			System.out.println("执行语句失败");
		}
		
		//处理结果
		try {
			while(rs.next()){
				System.out.println(rs.getObject(1));
				System.out.println(rs.getObject(2));
				System.out.println(rs.getObject(3));
			}
		} catch (SQLException e) {
			System.out.println("处理结果失败");
		}
		
		//释放资源
		try {
			
			rs.close();
			st.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("释放资源失败");
		}
		
	}
	
}
