package com.liushao.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionFactory {
	public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test";
	public static final String DB_USER = "root";
	public static final String DB_PSW = "";
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static  int TOTAL = 0;
	public static BasicDataSource dataSource;
	static{
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	static{
		dataSource = new BasicDataSource();
		dataSource.setUrl(DB_URL);
		dataSource.setDriverClassName(DRIVER_NAME);
		dataSource.setUsername(DB_USER);
		dataSource.setPassword(DB_PSW);
	}
	
	
	public static Connection getConnection(){
		
		try {
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PSW);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Connection getDataSourceConnection(){
		try {
			System.out.println("this is no" + TOTAL++ + "connection");
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
