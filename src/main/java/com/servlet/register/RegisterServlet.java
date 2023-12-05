package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	//create the query
	private static final String INSERT_QUERY="INSERT INTO data(NAME,CITY,MOBILE,DOB) VALUES(?,?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = resp.getWriter();
		// set content type
		resp.setContentType("text/html");
		// read the form values
		String name = req.getParameter("name");
		String city = req.getParameter("city");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");

//		//load the JDBC Driver
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// create the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtobackend", "root", "root");

				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);) {

			// set the values
			ps.setString(1, name);
			ps.setString(2, city);
			ps.setString(3, mobile);
			ps.setString(4, dob);

			// execute the query
			int count = ps.executeUpdate();
			if (count == 0) {
				pw.println("Record not inserted");
			} else
				pw.println("Record Stored in DB");
		} catch (SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		// close the stream
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("post called");
		Enumeration<String> reqParams = req.getParameterNames();
		while (reqParams.hasMoreElements()) {
			System.out.println("param value " + req.getParameter(reqParams.nextElement()));
		}
		doGet(req, resp);
		System.out.println("after get method called");
	}
}
