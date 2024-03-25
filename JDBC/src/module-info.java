package rock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pac {
		public static void main(String args[]) throws SQLException
		{
			 String uname ="root";
			 String url= "jdbc:mysql://localhost:3306/bank";
			 String password = "1234";
			 String query = "select * from bank";


		try {
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  
		 }catch(ClassNotFoundException e) {
			  //System.out.println(e);
			  e.printStackTrace();
		 }


		try {
			 
			  Connection con=DriverManager.getConnection(url, uname, password);
			  Statement statement=con.createStatement();
			  ResultSet result = statement.executeQuery(query);
			/*  System.out.println("inserting records");
			  String sql="insert into fruit values(10)";
			  statement.executeUpdate(sql);    */
			  
			  while (result.next())
			  {
				  String UniversityData ="" ;
				  for (int i =1; i<=6; i++)
				  {
				  UniversityData +=result.getString(i) + ":";
				  }
				  System.out.println("UniversityData");
			  }
		 
		 }catch(SQLException e) {
			  System.out.println(e);
		 }
		}
	}

