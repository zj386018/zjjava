package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLInject {

	public static void main(String[] args) {
		
		read("zhou");
		System.out.println("---sql���ע��֮��---");
		read("'or 1 or '");//sql���ע�� ����ʹ��ƴ���ķ�ʽ��ֵsql��䣬���ǲ���PreparedStatement��ֵ
	}

	static void read(String name) {
		Connection connection = null;
//		Statement statement = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;

		// ��������
		try {
			connection = JdbcUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�������ݿ�����ʧ��");
		}

		String sql = "select userid,username,userage from user where username=?";
		
		// �������
		try {
//			statement = connection.createStatement();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, name);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�������ݿ����ʧ��");
		}

		// ִ�����
		try {
			resultSet = pStatement
					.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ִ�����ʧ��");
		}

		// ������
		try {
			while (resultSet.next()) {
				System.out.print(resultSet.getObject(1) + "   ");
				System.out.print(resultSet.getObject(2) + "   ");
				System.out.println(resultSet.getObject(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("������ʧ��");
		} finally {
			JdbcUtils.freeResource(resultSet, pStatement, connection);
		}

	}

}
