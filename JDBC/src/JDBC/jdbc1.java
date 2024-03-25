package JDBC;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//import rioter.Account;
class Account1
{
   
    Scanner s=new Scanner(System.in);
    void displaybal(int bal)
    {
        System.out.println("your current balance = "+ bal);
        
    }
    int credit(int bal)
    {
        int cashin;
        System.out.println("enter the cash to add:");
        cashin=s.nextInt();
        bal+=cashin;
        displaybal(bal);
        return bal;
    }
    
    int debit(int bal)
    {
        int cashout;
        System.out.println("your current balance = "+ bal+"\n");
        System.out.println("enter the cash to withdraw:");
        cashout=s.nextInt(); 
        if ((bal>0)&&(bal>cashout))
        {
            bal-=cashout;
            displaybal(bal);
            
        }
        else 
        {
            System.out.println("insufficent balance.");
        }
        return bal;
    }

}






public class jdbc1 {

	public static void main(String args[]) throws SQLException
	{
		
		// SQL CALLING
		 String uname ="root";
		 String url= "jdbc:mysql://localhost:3306/rioter";
		 String password = "1234";
		 String query = "select * from bank1";
		 int bal1 =0 ;
		 Connection con=DriverManager.getConnection(url, uname, password);
		  Statement statement=con.createStatement();
		  ResultSet result = statement.executeQuery(query);
		  
		  while (result.next())
		  {
			  bal1 =result.getInt(1);
			//  System.out.println("your current balance = "+ result.getInt("balance"));
		  }
			// System.out.println(bal1);
			 
			
		// MAIN PROGRAM	 
		  
			 
		 int choice, ch, pin, f=0;
		 Scanner s=new Scanner(System.in);
		 Account1 a=new Account1();
		 System.out.println("\tWelcome to ATM \n ");
		 do
		 {
		            
			 System.out.println("Enter your pin:");
			 pin=s.nextInt();
			 if (pin==3333)
			 {
		            	
				 System.out.println("\n 1. credit \n 2. debit \n 3. display balance\n");
				 System.out.println("enter your choice:");
				 choice=s.nextInt();
				 switch(choice)
				 {
				 case 1:
					 bal1=a.credit(bal1);
					 query="update bank1 set balance = "+bal1+" where name = 'boy' ";
				     statement.executeUpdate(query);  
					 break;
				 case 2:
					 bal1=a.debit(bal1);
					 query="update bank1 set balance = "+bal1+" where name = 'boy' ";
				     statement.executeUpdate(query);  
					 break;
				 case 3:
					 a.displaybal(bal1);
					 break;
				 default:  System.out.println("invlid input.");
				 break;
				 }
			 }
			 else
			 {
				 System.out.println("invalid pin");
				 f++;
			 }
			 
			 //checking for continuation
			 if (f>2)
			 {
				 ch=0;
				 System.out.println("POYI PANI EDUTH GIVIKKADA");
			 }
			 else
			 {
				 System.out.println("Do you want to continue(Y=1/N=0):");
				 ch=s.nextInt();
			 }
		 } while(ch==1);
		// query="update bank1 set balance = "+balance+" where name = 'boy' ";
		//  statement.executeUpdate(query);  
		 // ResultSet result = statement.executeQuery(query);
		 
		 System.out.println("\n\t THANK U (-_-)");
		 
		
	}
}





















/*

public class jdbc1 {

	public static void main(String args[]) throws SQLException
	{
		int bal1  ;
		 String uname ="root";
		 String url= "jdbc:mysql://localhost:3306/rioter";
		 String password = "1234";
		 String query = "select * from bank1";

*/
	/*try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
	 }catch(ClassNotFoundException e) {
		  //System.out.println(e);
		  e.printStackTrace();
	 }
*/

	//try {
		 
	//	  Connection con=DriverManager.getConnection(url, uname, password);
		//  Statement statement=con.createStatement();
	//	  ResultSet result = statement.executeQuery(query);
		/*  System.out.println("inserting records");
		  String sql="insert into fruit values(10)";
		  statement.executeUpdate(sql);    */
		  
		//  while (result.next())
		//  {
		/*  	  String UniversityData ="" ;
			  for (int i =1; i<=2; i++)
			  {
			  UniversityData +=result.getString(i) + ":";
			  }
			  System.out.println(UniversityData);
		*/  	 
	/*	  int i=1;
		  String UniversityData ="" ;
		  UniversityData =result.getString(1) +" "+ i;
		  System.out.println(UniversityData);
		  i++;  
		  }
		  */
		//	  int i=1;
			  
		//	  bal1 =result.getInt(1);
			
		//	 System.out.println(bal1);
		//	  i++;  
		//  }
			  
	//	 System.out.println(bal1);
			  
	/* }catch(SQLException e) {
		  System.out.println(e);
	 }*/
	//System.out.println(bal1);
	
	
/*	
	
	}
}


*/