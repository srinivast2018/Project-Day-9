package com.ibm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	public Statement connectDatabase()throws SQLException
	{
	Connection c=DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries","foodsonfinger_atoz","welcome@123");
	Statement stmt=c.createStatement();

	return stmt;
	}
}
