package com.poly.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class hihi {
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=PolyOE";

	public static void main(String[] args) throws ClassNotFoundException {
//		try {
//			Connection connection = DriverManager.getConnection(url, "sa","root");
//			System.out.print("Kết nối thành công!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.print("Kết nối không thành công!");
//		}
		
		double so1 = 27;
		double so2 = 10;
		double div = so1/so2;
		int div1 = (int)div;
		System.out.println(div1);
	}
}
