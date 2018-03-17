package com;

import java.sql.*;

public class DataConnection {
	public static Connection getConnection() throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");		
	}
}
