package com.cyl.tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LinkSql {


	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyl_selenium?characterEncoding=utf8","root","");//cyl710
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}