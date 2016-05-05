package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLInject {

	public static void main(String[] args) {
		
		read("zhou");
		System.out.println("---sql语句注入之后---");
		read("'or 1 or '");//sql语句注入 不再使用拼串的方式赋值sql语句，而是采用PreparedStatement传值
	}

	static void read(String name) {
		Connection connection = null;
//		Statement statement = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;

		// 建立连接
		try {
			connection = JdbcUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("建立数据库连接失败");
		}

		String sql = "select userid,username,userage from user where username=?";
		
		// 创建语句
		try {
//			statement = connection.createStatement();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, name);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建数据库语句失败");
		}

		// 执行语句
		try {
			resultSet = pStatement
					.executeQuery();
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
			JdbcUtils.freeResource(resultSet, pStatement, connection);
		}

	}

}
