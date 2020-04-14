package com.hakalab.api;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(ApiApplication.class, args);
//		Class.forName("com.mysql.jdbc.Driver");  
//		Connection con=DriverManager.getConnection(  
//		"jdbc:mysql://localhost:3306/hakalab","root","root");  
//		//here sonoo is database name, root is username and password  
//		Statement stmt=con.createStatement();  
//		ResultSet rs=stmt.executeQuery("select * from feature");  
//		while(rs.next())  
//		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//		con.close();  
	}
	   
//	public static void main(String[] args) {
//	        String connectionUrl =
//	                "jdbc:sqlserver://hakalabdb.database.windows.net:1433;"
//	                        + "database=hakalab;"
//	                        + "user=hakalab@hakalabdb;"
//	                        + "password=Feature2020;"
//	                        + "encrypt=true;"
//	                        + "trustServerCertificate=false;"
//	                        + "hostNameInCertificate=*.database.windows.net;"
//	                        + "loginTimeout=30;";
//
//	        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
//	            // Code here.
//	        }
//	        // Handle any errors that may have occurred.
//	        catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	}

}
