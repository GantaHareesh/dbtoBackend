package com.servlet.register;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tester {

	public static void main(String[] args) throws SQLException {
		
		//create the connection		
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtobackend","root","root");
				
			PreparedStatement ps=con.prepareStatement("select * from data");){
			
			//set the values
		
			ps.execute();
			System.out.println("after execute");
			//execute the query
		
		//close the stream
		
	}

}
}