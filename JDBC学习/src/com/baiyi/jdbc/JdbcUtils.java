package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtils {

	private static String url = "jdbc:mysql://localhost:3306/zjjdbc";
	private static String user = "root";
	private static String password = "";

	private JdbcUtils() {
	}

	static {
		// ע������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("δ�ҵ����ݿ�������");
			throw new ExceptionInInitializerError(e);
		}
	}

	// ��ȡ���ݿ�����
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	//�ͷ���Դ
	public static void freeResource(ResultSet resultSet, Statement statement,
			Connection connection) {

		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}					
				
			}
			
		}

	}

}
