package DB;

import java.sql.*;

public class dbMngST {
	private static dbMngST dbm = null; // 1
	private Connection con = null;

	private dbMngST() { // 2
		System.out.println("dbMngST.CTOR()>>");
		

		String userName = "root";
		String password = "eranadi822";
		String url = "jdbc:mysql://localhost:3306/MBank_DB";

		
		

			try {
				System.out.println("Trying To Load MySQL Driver....");
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				System.out.println("Succesfully Loaded MySQL Driver!");
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			}
			try {
				System.out.println("Trying To Connect To MySQL DB....");
				con = DriverManager.getConnection(url, userName, password);
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(0);
			}
			if (con != null) {
				System.out
						.println("*******************************************");
				System.out
						.println("A Connection To MySQL Has Been Established");
				System.out
						.println("*******************************************");

			}// if		

	}// CTOR

	public static dbMngST instance() { // 3
		if (dbm == null) {
			dbm = new dbMngST();
		}// if
		return dbm;
	}// instance

	public Connection getCon() {
		return con;
	}// getCon
}// class
