package com.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	private static Connection con;
	public static Connection getConnection() {
	    try {
	    	
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Trishu@14");
	    	
		    } 
	    catch (Exception e) {
			e.printStackTrace();
		 }
	
      return con;
      
	}
}

