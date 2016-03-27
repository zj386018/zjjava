package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {

	public static void main(String[] args) {
		insert();
	}

	
	static void insert() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// 建立连接
		try {
			 connection = JdbcUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("建立数据库连接失败");
		}

		// 创建语句
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建数据库语句失败");
		}

		// 执行语句
		try {
			String sql = "insert into user(username,userage) values('zhou',33)";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("执行语句失败");
		}
		 finally {
			 JdbcUtils.freeResource(resultSet, statement, connection);
		}
		
	}
	
	
	static void read() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// 建立连接
		try {
			 connection = JdbcUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("建立数据库连接失败");
		}

		// 创建语句
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建数据库语句失败");
		}

		// 执行语句
		try {
			resultSet = statement.executeQuery("select userid,username,userage from user");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("执行语句失败");
		}

		// 处理结果
		try {
			while (resultSet.next()) {
				System.out.print(resultSet.getObject(1) + "   ");
				System.out.print(resultSet.getObject(2) + "   ");
				System.out.println(resultSet.getObject(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("处理结果失败");
		} finally {
			 JdbcUtils.freeResource(resultSet, statement, connection);
		}
		
	}

	
	
}
