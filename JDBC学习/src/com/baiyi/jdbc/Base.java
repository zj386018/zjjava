package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {

	public static void main(String[] args) {

	}

	static void test(){
		
		try {
			//ע������
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("���ݿ������������ɹ�");
		} catch (SQLException e) {
			System.out.println("���ݿ�����������ʧ��");
		}
		
		//��������
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "", "");
		} catch (SQLException e) {
			System.out.println("�������ݿ�����ʧ��");
		}
		
		
		
		
	}
	
}
