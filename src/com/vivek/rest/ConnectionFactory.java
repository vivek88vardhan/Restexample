package com.vivek.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/sample";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	
	public static Connection conn = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public ResultSet getResultSet(String sql)
	{
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			return rs;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} 
		return null;
	}
	
	public void close() throws SQLException
	{
		conn.close();
		stmt.close();
		rs.close();
	}
	
	public boolean updateQuery(String sql)
	{
		try
		{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return true;
		}
		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} 
		return false;
	}
	
}
