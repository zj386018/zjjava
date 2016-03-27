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
		
		//��������
		try {
//			connection = JdbcUtils.getConnection();
			connection = JdbcUtilsSingle.getInstance().getConnection();//����ģʽ
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�������ݿ�����ʧ��");
		}
		
		//�������
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�������ݿ����ʧ��");
		}
		
		//ִ�����
		try {
			resultSet = statement.executeQuery("select * from user");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ִ�����ʧ��");
		}
		 

		//������
		try {
			while(resultSet.next()){
				System.out.print(resultSet.getObject(1)+"   ");
				System.out.print(resultSet.getObject(2)+"   ");
				System.out.println(resultSet.getObject(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("������ʧ��");
		}finally{
//			JdbcUtils.freeResource(resultSet, statement, connection);
			JdbcUtilsSingle.getInstance().freeResource(resultSet, statement, connection);
		}
		
		
	}
	

	
}
