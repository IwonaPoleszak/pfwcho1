package com.example.pfwcho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class PfwchoApplication {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//	static final String DB_URL = "jdbc:mysql://Full2020-86394:3306/pfwcho?useSSL=false&serverTimezone=UTC";
	static final String DB_URL = "jdbc:mysql://localhost:3306?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

	static final String USER = "ipoleszak";
	static final String PASS = "ipoleszak";


	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet resultSet = conn.getMetaData().getCatalogs();
			boolean exists = false;
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if(databaseName.equals("pfwcho")) {
					exists = true;
				}
			}
			resultSet.close();
			if(!exists) {
				String sql = "CREATE DATABASE pfwcho";
				String sql2 = "CREATE TABLE pfwcho.country (`id` int(11) NOT NULL AUTO_INCREMENT, `name` varchar(90) DEFAULT NULL, `capital` varchar(90) DEFAULT NULL, PRIMARY KEY (`id`)) ";
				stmt.executeUpdate(sql);
				stmt.executeUpdate(sql2);
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}

		SpringApplication.run(PfwchoApplication.class, args);

	}


}




