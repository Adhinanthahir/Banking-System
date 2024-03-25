package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class jdbc {

	public static void main(String args[]) throws SQLException
	{
		 String uname ="root";
		 String url= "jdbc:mysql://localhost:3306/rioter";
		 String password = "1234";
		 String query = "select * from bankmain";


	try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
	 }catch(ClassNotFoundException e) {
		  //System.out.println(e);
		  e.printStackTrace();
	 }

	//int UniversityData =0 ;

	try {
		 
		  Connection con=DriverManager.getConnection(url, uname, password);
		  Statement statement=con.createStatement();
		//  ResultSet result = statement.executeQuery(query);
		/*System.out.println("updating records");
		  String sql="update bank1 set balance = 112 where name = 'boy' ";
		  statement.executeUpdate(sql);  
		*/ ResultSet result = statement.executeQuery(query);
		  String UniversityData ="" ;
		  while (result.next())
		  {
			  
			  for (int i =1; i<=4; i++)
			  {
			  UniversityData =result.getString(i) ;
			  System.out.println(UniversityData);
			  }
			  //System.out.println(UniversityData);
		  }
		 //System.out.println(UniversityData);
	 }catch(SQLException e) {
		  System.out.println(e);
	 }
/*	  System.out.println(UniversityData);
	  int a;
	  a= UniversityData +10;
	  System.out.println(a);
*/	}
}