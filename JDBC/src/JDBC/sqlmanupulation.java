package JDBC;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlmanupulation {
	public static void main(String args[]) throws SQLException
	{
		int bal1  ;
		String name;
		Scanner s=new Scanner(System.in);
		 String uname ="root";
		 String url= "jdbc:mysql://localhost:3306/rioter";
		 String password = "1234";
		 String name1= s.next();
		 String query ;
		 
		 Connection con=DriverManager.getConnection(url, uname, password);
		
		  Statement statement=con.createStatement();
//		  String sd ="insert into bank1 values(12,'girl')";
//		  statement.execute(sd);
		  query = "select * from bank where name ='"+name1+"'";
		   System.out.println("-----------------------------------------------------------");
		  ResultSet result = statement.executeQuery(query);
		 // System.out.printf("%12s %10s %10s\n", "Account No", "Name", "Balance");
			 
		  while(result.next())
		  {
			 // for(bal1=1;bal1<3;bal1++)
			  System.out.println(result.getString(1)+","+result.getString(2));
			  //+","+result.getString(3)+","+result.getString(4)+","+result.getString(5));
			  //bal1 =result.getInt(1);
			  //System.out.println( bal1);
			   System.out.println("-----------------------------------------------------------");

	           

	            // Execution
	 

	         
	      /*          System.out.printf("%12d %10s %10d.00\n", rs.getInt("ac_no"), rs.getString("cname"), rs.getInt("balance"));

	            }

	            System.out.println("-----------------------------------------------------------\n");	   
			   
*/
		  }
		  //OR
//		 while (result.next())
//		  {
//			  bal1 =result.getInt(1);
//			  name =result.getString(2);
//				 
//				  
			  }

	}

