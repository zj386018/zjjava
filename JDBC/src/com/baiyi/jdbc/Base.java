package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

	
	public static void main(String[] args) {
		template();
	}
	
	static void template(){

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		//建立连接
		try {
//			connection = JdbcUtils.getConnection();
			connection = JdbcUtilsSingle.getInstance().getConnection();//单例模式
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("建立数据库连接失败");
		}
		
		//创建语句
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建数据库语句失败");
		}
		
		//执行语句
		try {
			resultSet = statement.executeQuery("select * from user");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("执行语句失败");
		}
		 

		//处理结果
		try {
			while(resultSet.next()){
				System.out.print(resultSet.getObject(1)+"   ");
				System.out.print(resultSet.getObject(2)+"   ");
				System.out.println(resultSet.getObject(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("处理结果失败");
		}finally{
//			JdbcUtils.freeResource(resultSet, statement, connection);
			JdbcUtilsSingle.getInstance().freeResource(resultSet, statement, connection);
		}
		
		
	}
	

	
}
