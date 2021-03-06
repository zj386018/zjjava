package com.baiyi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtilsSingleLater {

	private static String url = "jdbc:mysql://localhost:3306/zjjdbc";
	private static String user = "root";
	private static String password = "";
	
	private static JdbcUtilsSingleLater jdbcUtilsSingleLater;

	private JdbcUtilsSingleLater() {
		
	}

	
	public static JdbcUtilsSingleLater getInstance(){
		//延迟加载
		if(jdbcUtilsSingleLater == null){
			synchronized (JdbcUtilsSingleLater.class) {
				if(jdbcUtilsSingleLater == null){
				jdbcUtilsSingleLater = new JdbcUtilsSingleLater();
				}
			}
		}
		return jdbcUtilsSingleLater;
	}

	
	static {
		// 注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("未找到数据库驱动类");
			throw new ExceptionInInitializerError(e);
		}
	}

	
	// 获取数据库连接
	public  Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	
	//释放资源
	public void freeResource(ResultSet resultSet, Statement statement,
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
