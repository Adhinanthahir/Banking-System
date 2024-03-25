package rock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pac{

	public static void main(String args[]) throws SQLException
	{
		int bal1  ;
		 String uname ="root";
		 String url= "jdbc:mysql://localhost:3306/rioter";
		 String password = "1234";
		 String query = "select * from bank1";
		 
		 Connection con=DriverManager.getConnection(url, uname, password);
		  Statement statement=con.createStatement();
		  ResultSet result = statement.executeQuery(query);
		  
		  while (result.next())
		  {
			  bal1 =result.getInt(1);
				
				 System.out.println(bal1);
				  
			  }
		  
	
	
	
	}
}