package com.baiyi.jdbc._11clob;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baiyi.jdbc.JdbcUtils;

public class ClobTest {

	public static void main(String[] args) {

//		insert();
		read(1);
	}

	static void read(int id) {
		Connection connection = null;
		// Statement statement = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;

		// 建立连接
		try {
			connection = JdbcUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("建立数据库连接失败");
		}

		String sql = "select bigtxt from clob where id=?";

		// 创建语句
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建数据库语句失败");
		}

		// 执行语句
		try {
			resultSet = pStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("执行语句失败");
		}

		Reader reader = null;
		Writer writer = null;
		// 处理结果
		try {
			while (resultSet.next()) {
				File file = new File("第一章");
				Clob clob = resultSet.getClob("bigtxt");
				reader = clob.getCharacterStream();
//				reader = resultSet.getCharacterStream("bigtxt");
				writer = new BufferedWriter(new FileWriter(file));
				char[] buff = new char[1024];
				for(int i=0;(i=reader.read(buff))>0;){
					writer.write(buff,0,i);
				}
				
				System.out.println(resultSet.getClob("bigtxt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("处理结果失败");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.freeResource(resultSet, pStatement, connection);
			try {
				writer.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static void insert() {
		Connection connection = null;
		// Statement statement = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;

		// 建立连接
		try {
			connection = JdbcUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("建立数据库连接失败");
		}

		String sql = "insert into clob(bigtxt) values(?)";
		Reader reader = null;
		// 创建语句
		try {
			pStatement = connection.prepareStatement(sql);
			File file = new File("src/com/baiyi/jdbc/_11clob/第四章.txt");
			reader = new BufferedReader(new FileReader(file));
			pStatement.setCharacterStream(1, reader, file.length());// 字符流
			// pStatement.setAsciiStream(1, x, length)//ascii 字节流
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建数据库语句失败");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("未读取到文件");
		}

		// 执行语句
		try {
			pStatement.execute();
//			pStatement.executeQuery();
			reader.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("执行语句失败");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.freeResource(resultSet, pStatement, connection);
		}

	}

}
