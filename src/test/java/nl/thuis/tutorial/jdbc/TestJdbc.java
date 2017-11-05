package nl.thuis.tutorial.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestJdbc {

	private static final String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
	private static final String user = "hbstudent";
	private static final String pass = "hbstudent";

	public static void main(String[] args) {

		try {
			System.out.println("Connecting to database: " + jdbcUrl);

			@SuppressWarnings("unused")
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

			System.out.println("Connection successful!!!");

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
