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
		//ע������
		try {
			//��һ��ע�������ķ���
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//�ڶ���ע�������ķ���
//			System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
			//������ע�������ķ������Ƽ�ʹ�÷�ʽ��
//			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("���ݿ������������ɹ�");
		} catch (Exception e) {
			System.out.println("���ݿ�����������ʧ��");
		}
		
		//��������
		try {
			String url = "jdbc:mysql://localhost:3306/zjjdbc";
			String user = "root";
			String password = "";
			
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("�������ݿ�����ʧ��");
		}
		
		//�������
		try {
			st = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("�������ݿ����ʧ��");
		}
		
		//ִ�����
		try {
			rs = st.executeQuery("select * from user");
		} catch (SQLException e) {
			System.out.println("ִ�����ʧ��");
		}
		
		//������
		try {
			while(rs.next()){
				System.out.println(rs.getObject(1));
				System.out.println(rs.getObject(2));
				System.out.println(rs.getObject(3));
			}
		} catch (SQLException e) {
			System.out.println("������ʧ��");
		}
		
		//�ͷ���Դ
		try {
			
			rs.close();
			st.close();
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("�ͷ���Դʧ��");
		}
		
	}
	
}
