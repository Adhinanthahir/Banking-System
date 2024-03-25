package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;


//import java.util.Random;
class Connect1
{
	 static Connection con;
	 public static  Connection getConnection() 
	    {	
		try
		{
			String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; 
		String uname ="root";
		 String url= "jdbc:mysql://localhost:3306/rioter";
		 String password = "1234";
//		 String query = "select * from bank1";
		 Class.forName(mysqlJDBCDriver);
		 Connection con =DriverManager.getConnection(url, uname, password);
//		 Statement statement=con.createStatement();
//		  ResultSet result = statement.executeQuery(query);
		 return con;
		}
		catch (Exception e) 
		{
            System.out.println("Connection Failed!");
        }
		return con;
	    }
}

	
	
class Account
{
		
	static Connection con = Connect1.getConnection();
    static String query = "", sql = "",  times="",query1="";
    int ch, g=0,acc_bal=0 , acc_no=0;
    textuser  d3=new textuser();
    textgames  d4=new textgames();
 boolean login(String name, int pin) throws SQLException
	{
		try
		{
			if (name=="" || pin==0 )
			{ System.out.println("All Field Required!");
            		return false;
			}
			
			int choice=5, bal, l_times,first=4;
	
			Scanner q=new  Scanner(System.in);
			query= "select * from bank where name ='"+name+"' and pin="+pin;
		    //PreparedStatement stmt= con.prepareStatement(query);
			times="UPDATE BANK SET logintimes= logintimes+1 where name='"+name+"'";
			Statement stmt = con.createStatement();
		
			ResultSet result = stmt.executeQuery(query);
			if (result.next())
			{
				l_times=result.getInt("logintimes");
				if (l_times==0)
				{
					first=l_times;
					}
				bal=result.getInt("balance");
				acc_bal=bal;
				acc_no=result.getInt("acc_no");
				
				d3.usertext("ACCOUNT PAGE",2,15);
				
				System.out.println("\nWelcome ,");//+ name);
//				d3.usertext("Welcome",2,10);
				//System.out.print("");
				d3.usertext(name,2,17);
				while(true)
				{
					try
					{
						
						//System.out.println("\n\tACCOUNT PAGE \nSELECT an option \n");
						System.out.println("\n \nSELECT an option \n");
						if( ((l_times==0)||(first==0))&&(acc_bal<50))
							System.out.println("FOR 1st time users please deposit minimum of 500 to finish account setup\n");
						
						if ( acc_no==10000)
						{	System.out.println("\n 	ADMIN CONTROL \n"
									+ " 11. display databse\n"
									+ " 12. delete user				30.admin TimePass\n"
									+ " 5 . Other\n"
									+ " 6 . logout\n  ");
									
						}
						
						else {
						System.out.println(" 1.credit\n"
										+ " 2.debit\n"
										+ " 3.view balance			   10.user Time pass\n"
										+ " 4.Transfer money		   20. X'mas\n"
										+ " 5.Other\n"
										+ " 6.logout\n  ");
						}
						System.out.print("Enter ur choice:");
						choice=q.nextInt();
						
						if (choice!=6)
							process1(1);
//						times(name);
						
						switch(choice)
						{
						case 1:
							int cashin=0;
							if ( ((l_times==0)||(first==0))&&(acc_bal<50))
								{
								System.out.println("Enter cash minimum of 500 to deposit: ");
								}	
							else
								{
								System.out.println("Enter cash to deposit\n");				
								}
							cashin=q.nextInt();
							 
							 if ((cashin>=500)&&(l_times==0)&&(cashin<=100000))
							 {
								 acc_bal+=cashin;
								 query="update bank set balance = "+acc_bal+" where acc_no ="+acc_no;
							     if(stmt.executeUpdate(query)==1)					
							    	 System.out.println("Success!");
							     first=1;
							 }  
							 else if((cashin>0)&&(cashin<=100000))
							 { 
								 acc_bal+=cashin;
							     query="update bank set balance = "+acc_bal+" where acc_no ="+acc_no;
							     if(stmt.executeUpdate(query)==1)					
							    	 d3.usertext("success !!",3,12);
							    	 //System.out.println("Success!");
							     if (acc_bal>499) 
							     {
									first = 1;
								}
							 }
							 else
								 System.out.println("ERR: Invalid");

							 break;
						case 20:
							System.out.println("\n ");
							santa t=new santa();
								t.santac();
								break;
						case 10:
							System.out.print("Enter the String :");
							String patern=q.next();
							 
							int cho=1;
							while(cho==1)
							{
							System.out.print("Enter the Symbol :");
							String symbol=q.next();
							
							d4.usertext(patern,2,17,symbol," ");
							System.out.print("\nDo you want to try another symbol (Y=1/N=0):");
							  cho=q.nextInt();
							}
							break;
						case 30:
							
							System.out.print("Enter the String :");
							String patern1=q.next();
							 
							int cho1=1;
							while(cho1==1)
							{
							System.out.print("Enter the Symbol :");
							String symbol=q.next();
							System.out.print("Enter the background :");
							String back=q.next();
							
							d4.usertext(patern1,2,17,symbol,back);
							System.out.print("\nDo you want to try another symbol (Y=1/N=0):");
							  cho1=q.nextInt();
							}
							break;
						case 2:
							 int cashout=0;
							 bal( acc_bal);
							 if (l_times!=0)
								 	System.out.println("Minimum withdrawal amt should be "+(acc_bal-500));
							 System.out.println("\nEnter cash to withdraw: ");
							 cashout=q.nextInt();
							 if(cashout<=0)
							 {
								 System.out.println("INVALID");
							 }
							 else if ((cashout<=(acc_bal-500))&&(acc_bal>500))//minimum bal of 500
							 {
								 acc_bal-=cashout;
								 query="update bank set balance = "+acc_bal+" where acc_no = "+acc_no;
								 if(stmt.executeUpdate(query)==1)
								 {
									 //System.out.println("Success!\n");
									 d3.usertext("success !!",3,12);
								 }
							 }
							
							 else
							 {
								 System.out.println("INSUFFICENT BALANCE!\n Can't withdraw amt less than minimum balance amt");
							 }
						     break;
						case 3:
							bal( acc_bal);
							break;
							
						//	TRANSACTION
							
						case 4:
							int rev_acc,trans_pin, tpin=0;
							
							System.out.println("ENTER YOUR TRANSACTION PIN:");
							trans_pin=q.nextInt();
							
							query= "select * from bank where acc_no="+ acc_no;
							PreparedStatement stmt1 = con.prepareStatement(query);
							ResultSet result1 = stmt1.executeQuery(query);
							
							while (result1.next())
							{	  
								tpin =result1.getInt("trans_pin");
							}
							
							if (tpin!=trans_pin)
							{
								//System.out.println("ERR:: REVICER AND SENDER CAN'T BE SAME !\n");
								System.out.println("ERR: WRONG PIN");
								break;
							}
							System.out.println("DO you know Acc no.of revicer(Y/N):");
							String ch;
							  ch=q.next();//.charAt(0);
							if ((ch.equals("N"))||(ch.equals("n"))||(ch.equals("no")))
							{	
								others(name,3);
								//System.out.println("efee.:");
							}
							System.out.println("Enter recevicer's account no.:");
							rev_acc=q.nextInt();
							if (rev_acc==acc_no)
							{
								System.out.println("ERR:: REVICER AND SENDER CAN'T BE SAME !\n");
								break;
							}
							if(transfer(rev_acc,trans_pin,0))
							{
			                    System.out.println(
			                        "MSG : Transaction successfully completed.\n");
			                    d3.usertext("success !!",3,12);
			                }
//							else
//							{
//								System.out.println("ERR:: INCORRECT DETAILS!\n");
//								break;
//			                }
							break;
							
							
							
						case 5: 
							//System.out.println("ERR : Failed!\n");
							if(others(name,0))
							{
								//return true;
								return true;
							}
							else
							{
								//System.out.println("ERR : something error!\n");
								break;
			                }
						case 11:
							others(name,1);
							break;
						case 12:
							if (acc_no==10000)
							{
								query1="Select * from bank";
								ResultSet result2 = stmt.executeQuery(query1);
								System.out.println("+--------+------------+");
								System.out.printf("|%5s  | %-10s |%n","acc_No","name" );
								System.out.println("+--------+------------+");
								while(result2.next())
								{
								
								  System.out.printf("|%5d   | %-10s |\n",result2.getInt("acc_no"),result2.getString("name")); 
								  System.out.println("+--------+------------+");
								}
								System.out.println("\nEnter account no to be deleted :");
								int del_acc= q.nextInt();
								query1="DELETE from bank where acc_no="+del_acc;
								if (stmt.executeUpdate(query1)==1)
									System.out.println("Succesfully Deleted");
								else
									System.out.println("Not Possible");
							}
							break;
						case 6:
							if (l_times>0) 
							{//dsf
								stmt.executeUpdate(times);
							}
	//thread		
							process1(2);
							return true;
						default: 
							System.out.println("invalid entry");
							break;
						}

					}
					catch (InputMismatchException e)
					{
						System.out.println("  WRONG ENTRY \nTry again by login");
						return false;
					}
					catch (Exception e)
					{
// 						e.printStackTrace();
// admin 141
						System.out.println("admin 141");
// 						break;
					}
					//return true;
				}
			}
//admins
//			else if (result.next())
//			{
//				try {
//					System.out.println("hello!");
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//			
			
			else
			{
				return false;
			}
		//	return true;
		}
		catch (SQLIntegrityConstraintViolationException e) 
		{
            System.out.println("Username Not Available!");
        }
        catch (Exception e) 
		{
//admin 142       
        	System.out.println("admin 142");
           // e.printStackTrace();
        }
		return false;

	}

 public void process1(int th)  
 {
	 try {
		 if (th==1) {
	 System.out.print("\n \t processing ");
	 Thread.sleep(100);
	 System.out.print(" .");
	 Thread.sleep(100);
	 System.out.print(" .");
	 Thread.sleep(100);
	 System.out.print(" .\n");
	 System.out.println();}
		 else if (th==2)
		 {
			 System.out.print("\n \t SIGNING OFF ");
			 Thread.sleep(100);
			 System.out.print(" .");
			 Thread.sleep(1000);
			 System.out.print(" .");
			 Thread.sleep(1000);
			 System.out.print(" .");
			 Thread.sleep(2000);
			 System.out.print(" .\n");
			 System.out.println();
		 }
	 }
	 catch(Exception e) {
		 System.out.println("err:");
	 }
	  
 }
 boolean others(String acc_name, int im)   throws SQLException
{
 try {
				 											/*	im=0 frm others
				 												im=1	admin display
				 												im=3	transfer
				 											*/
	 Scanner w=new  Scanner(System.in);
	 Statement stmt1 = con.createStatement();
	 //if((acc_no!=10000)&&(im==0))
	 if((im==0))
	 {
		 System.out.println("\tOther options \n1. pin change "
		 		+ "\n2.transaction pin change "
		 		+ "\n3.view account details "
		 		+ "\n4.for ADMIN"
		 		+ "\n\n5.BACK to Account Page"
		 		+ "\n6.LogOut");
		 System.out.println("Enter ur choice:");
		 ch=w.nextInt();
	
	 }
	 else if(im==1)
		 ch=4;
	 
	 else if (im==3)
		 ch=3;
	 
	 int tr=0;
	switch(ch)
	{
	case 1:
//		try {
			tr=0;
			int l_pin,newpin=0;
			System.out.println("enter your current login pin:");
			l_pin=w.nextInt();
			System.out.println("enter the new login pin:");
			newpin=w.nextInt();
			//System.out.println("enter:==>"+newpin+acc_no+l_pin);
			sql="update bank set pin="+newpin+" where pin="+l_pin+" and  acc_no="+acc_no;
			if(stmt1.executeUpdate(sql)==1)
			{
				System.out.println("SUCCESSFULLY Login Pin changed\n");
				tr=1;
				break;
			}
			else{
				g=g+1;
				if (g<3)
				{
					System.out.println("ERR in details: Try again");
					break;
				}
				System.out.println("Err run out!:(");
				return true;
			}

	case 2:
		tr=0;
		int t_pin,newt_pin=0;
		System.out.println("enter your current t_login pin:");
		t_pin=w.nextInt();
		System.out.println("enter the new t_login pin:");
		newt_pin=w.nextInt();
		//System.out.println("enter:==>"+newt_pin+acc_no+t_pin);
		sql="update bank set trans_pin="+newt_pin+" where trans_pin="+t_pin+" and  acc_no="+acc_no;
		if(stmt1.executeUpdate(sql)==1)
		{
			System.out.println("SUCCESSFULLY Transaction Pin changed\n");
			tr=1;
			break;
		}
		else{
			g=g+1;
			if (g<3)
			{
				System.out.println("ERR in details: Try again");
				break;
			}
			System.out.println("Err run out!:(");
			return true;
		}

	case 3:
		//System.out.println(":( details under maintance , soon will available:)\n");
		if (im==3)
			query="select * from bank";
		else
			query="SELECT * from bank where name='"+acc_name+"'";
		
		ResultSet r=stmt1.executeQuery(query);
		String small="+--------+------------+";
		String large="+--------+----------+------------+------------+------------+";
		
		//System.out.println("---------------------------------------------------------------");//80-17		
		if(im==3)
		{	
			System.out.println(small);
			System.out.printf("|%5s  | %-10s |%n","acc_No","name" );
			System.out.println(small);//80-17
		}
		else
		{
			System.out.println(large);
			System.out.printf("|%7s | %-8s | %10s | %10s | %10s |%n","acc_No","name", "login pin","balance","trans_pin");
			System.out.println(large);
		}
		int h=0;
		while(r.next())
		  {
			if((h==0)&&(im==3))// used for hide 1st admin row
			{
//				System.out.println(small);
				h=1;
			}
			else
			{
			  if(im==3)
			  {  
				  System.out.printf("|%5d   | %-10s |\n",r.getInt("acc_no"),r.getString("name")); 
				  System.out.println(small);//80-17
				} 
			  else
			  {  
				  System.out.printf("|%7d | %-8s | %10d | %10d | %10d |\n",r.getInt("acc_no"),r.getString("name"),r.getInt("pin"),r.getInt("balance"),r.getInt("trans_pin"));
				  System.out.println(large);
			  }
			}
			//}  
		  }
		  //System.out.println("---------------------------------------------------------------");//80-
		break;
	case 4:
		int ad_pass=8989;
		if(acc_no!=10000) {
		System.out.println("Enter admin pass:");
		ad_pass=w.nextInt();
		}
		
		if ((acc_no==10000)||(ad_pass==8989))
		{
			query="SELECT * from bank";
			  ResultSet result = stmt1.executeQuery(query);
			  System.out.println("+--------+----------+------------+------------+------------+");//80-17
			 // System.out.println("acc_No | name   | login pin | balance \t|trans_pin|");
			  System.out.printf("|%7s | %-8s | %10s | %10s | %10s |%n","acc_No","name", "login pin","balance","trans_pin");
			 int h1=0;
			  while(result.next())
			  {
					if(h1==0)// used for hide 1st admin row
					{
//						System.out.println(small);
						h1=1;
					}
					else
					{
						System.out.println("+--------+----------+------------+------------+------------+");//80-17
						System.out.printf("|%7d | %-8s | %10d | %10d | %10d |\n",result.getInt("acc_no"),result.getString("name"),result.getInt("pin"),result.getInt("balance"),result.getInt("trans_pin"));
					}
			//good	 //System.out.printf("%12d | %10s | %10d | %10d | %10d\n",result.getInt("acc_no"),result.getString("name"),result.getInt("pin"),result.getInt("balance"),result.getInt("trans_pin"));
			//	  System.out.printf("%12s | %10s | %10s | %10s | %10s\n"+result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5));
			//	  System.out.println(" "+result.getString(1)+", "+result.getString(2)+"  ,  "+result.getString(3)
			//	  +","+result.getString(4)+"\t,"+result.getString(5));
			  }
			  System.out.println("+--------+----------+------------+------------+------------+");//80-
		}
		else
		{
			//System.out.println("RECORD NOT FOUND");
			System.out.println("GET OUT \nThis page is for admin executives..");
		}
		break;
	case 5:
		break;
	case 6:
		return true;
	default: 
		System.out.println("invalid entry");
		break;	
	}
	if (tr==1)
	{
		return true;
	}
	
 }
 catch (SQLIntegrityConstraintViolationException e) 
	{
	 System.out.println("falid:: invalid details");
 }
 catch (Exception e) 
  {

//Admin 301
	 
	 System.out.println("falid:: invalid details ADmin 301");// trial added
     //e.printStackTrace(); //org
  }
  return false;
  
}

public  static  boolean create(String name, int login_pin, int trans_pin) throws SQLException
{
	try
	{
		if (name=="" || login_pin<1000 || login_pin>=10000|| trans_pin==0 || trans_pin<1000 || trans_pin>=10000)
		{ System.out.println("All Field Required!");
        		return false;
		}
		
		int choice=5, acc_bal=0 , ranacc_no=0;
		//Scanner q=new  Scanner(System.in);
		Statement stmt = con.createStatement();
		sql="SELECT acc_no FROM bank ORDER BY acc_no DESC LIMIT 1";// gets the last column value 
		query= "select * from bank";
		ResultSet rslt = stmt.executeQuery(sql);
		
		if (rslt.next())
		  {
			  ranacc_no =rslt.getInt("acc_no");
			  ranacc_no+=1;
			  //System.out.println(rslt.getString("name"));
//			  if (ranacc_no==0)
//			  {
//				  ranacc_no=10001;
//			 
//			  //System.out.println(ranacc_no);
//			  }	 
//			  else
//			  {
//				  ranacc_no+=1;
//			  }
		  }
		ResultSet rt = stmt.executeQuery(query);
		String check="";
		while(rt.next())
		{
//			System.out.println("sorry already have this name\n"+name);
//			System.out.println(rt.getString("name"));
			check=rt.getString("name");
			 if (name.equals(check))
			{
					 System.out.println("MSG:: Sorry Username '"+check+"'  is already defined \n Please try another :)");	 
					 return false;
 
			}
		}
		//trans_pin=q.nextInt();
		query= "insert into bank values("+ranacc_no+", '"+name+"',"+login_pin+",0,"+ trans_pin+",0)";

		if(stmt.executeUpdate(query)==1) 
		{
			System.out.println("MSG: ACCOUNT CREATED SUCCESSFULLY\n");
			System.out.println("\nHere is your account no. "+ranacc_no);
			System.out.println("\n\nMSG: PLEASE DON'T SHARE ACCOUNT DETAILS WITH ANYONE\n" );
			return true;
		}
	}
	catch (SQLIntegrityConstraintViolationException e) 
	{
        System.out.println("Username Not Available!");
    }
    catch (Exception e) 
	{
    	
        e.printStackTrace();
    }
	
return false;
	}


boolean transfer(int rev_acc , int trans_pin, int emg) throws SQLException
{
	  try 
	   {	
		  if (rev_acc==0 || trans_pin==0 )
		   {
			  System.out.println("All Field Required!");
		    		return false;
			}
		  Scanner q=new  Scanner(System.in);
//		  query= "select * from bank where acc_no="+ acc_no+" and trans_pin="+ trans_pin;
//		   PreparedStatement stmt1 = con.prepareStatement(query);
//		   ResultSet result1 = stmt1.executeQuery(query);		   
		   String rev_name="";
		   query= "select * from bank where acc_no="+ rev_acc;
		   PreparedStatement stmt = con.prepareStatement(query);
		   ResultSet result = stmt.executeQuery(query);
		   int rev_bal=0, cash=0;
		   while (result.next())
		   {
			   rev_name=result.getString("name");
			   rev_bal=result.getInt("balance");
			   rev_acc=result.getInt("acc_no");
			   System.out.println("\tAccount owner is "+rev_name+"\n");
			   bal( acc_bal);
			   System.out.println("Maximum withdrawal amt should be "+(acc_bal-500));
			   System.out.println("\nenter the amount to be transfered: ");
			   cash=q.nextInt();
			   if(rev_acc==10000)
			   {
				   System.out.println("CAN'T transfer money to admins");
				   return false;
			   }
			
			   else if ((cash>(acc_bal-500)||(acc_bal<=0) )||(cash<1))//minimum bal of 5001
			   {
				   
				   System.out.println("INSUFFICENT BALANCE!\n Can't Transfer amt less than minimum balance amt");
				  // transfer(rev_acc,trans_pin);
				   return false;
			   }  
			   else if(cash>100000)
			   {
				   System.out.println("CAN'T transfer money greater than 1 lakh");
				   return false;
			   }
			   else 
			   {
				   acc_bal-=cash;
				   rev_bal+=cash;
				   query="update bank set balance = "+acc_bal+" where acc_no = "+acc_no;
				   stmt.executeUpdate(query);
				   query="update bank set balance = "+rev_bal+" where acc_no = "+rev_acc;
				    stmt.executeUpdate(query);	//con.commit();
				    return true;
			   }	   
		   }
		   System.out.println("ERR:: INCORRECT DETAILS!\n");
		   return false;
	   }
	  catch (SQLIntegrityConstraintViolationException e) 
		{
          System.out.println("TRANSACATION NOT POSSIBLE || PIN ERR! SICVE");
      }	
		catch (InputMismatchException e)
		{
			System.out.println("  WRONG ENTRY \nTry again by login");
			//return false;
			
		}
	   catch (Exception e)
	   {
		   System.out.println("TRANSACATION1224 NOT POSSIBLE || PIN ERR!");
		   //e.printStackTrace();
	   }
	  return false;
}

static void bal(int acc_bal) 
 {
    	/*   try 
    	   {	
    		   query= "select * from bank where acc_no="+ acc_no;
    		   PreparedStatement stmt = con.prepareStatement(query);
    		   ResultSet result = stmt.executeQuery(query);
    		   int bal;
    		   while (result.next())
    		   {
    			   bal=result.getInt("balance");*/
    			   System.out.println("your current balance = "+ acc_bal);
    		/*   }
    	   }
    	   catch (Exception e)
    	   {
    		   e.printStackTrace();
    	   }*/
    			 /*  System.out.println("-----------------------------------------------------------");

    	            System.out.printf("%12s %10s %10s\n", "Account No", "Name", "Balance");
    	 

    	            // Execution
    	 

    	            while (rs.next()) 
    	            {

    	                System.out.printf("%12d %10s %10d.00\n", rs.getInt("ac_no"), rs.getString("cname"), rs.getInt("balance"));

    	            }

    	            System.out.println("-----------------------------------------------------------\n");	   
    			   
    			  */ 
    			   

    }
}
class bank
{
	public static void main(String args[])
	{
		
		Account a=new Account();
		int choice=0,pin, t_pin;
		String name="";
		Scanner s=new Scanner(System.in);
		text1 d1=new text1();
		text2  d2=new text2();
		textuser  d3=new textuser();
		textgames  d4=new textgames();
		d1.ascci10();
//		System.out.println("\n");
		 
		d2.ascii20();
		while(choice!=5)
		{	
			try {
			//System.out.println("\n\tWELCOME TO E-BANK\n");
			
			System.out.printf("\n\n\t  %11s   %25s \n \n  %25s\n", "1. Login","2.create account","5.exit");
			
//			System.out.println("\t1. Login \t" + "2.create account\n"+ "\n\t     [ 5.exit ]");
			
			System.out.print ("\n  Enter your chocie:");
			choice=s.nextInt();
			//System.out.println("322:");
			switch(choice)
			{
//			case 11:
//				
//				System.out.print("Enter the String :");
//				String patern=s.next();
//				 
//				int cho=1;
//				while(cho==1)
//				{
//				System.out.print("Enter the Symbol :");
//				String symbol=s.next();
//				
//				d4.usertext(patern,2,17,symbol," ");
//				System.out.print("\nDo you want to try another symbol (Y=1/N=0):");
//				  cho=s.nextInt();
//				}
//				break;
//			case 10:
//				System.out.println("\n ");
//				santa t=new santa();
//					t.santac();
//					break;
			case 1:
				try{
					//System.out.println("\t LOGIN PAGE");
					d3.usertext("LOGIN PAGE",10, 16);
					System.out.println("\nenter ur user name:");
					name=s.next();
			
					System.out.println("enter the pin:");
					pin =s.nextInt();
					a.process1(1);
					if (/*Account or*/a.login(name,pin))
					{
	                    System.out.println(
	                        "MSG : Logout Successfully!\n");
	                }
					else
					{
						System.out.println("ERR : login Failed!\n");
	                }
				}
				
				catch (Exception e){
					System.out.println(" ERROR : Enter Valid Data::Login Failed!\n");
					//break;
					choice=5;
				}				
				break;
				
			case 2:
				//create
				try{
				//System.out.println("\tCREATION PAGE");
					d3.usertext("CREATION PAGE",2,14);
				System.out.println("\nenter a unique user name:");
				name=s.next();
				System.out.println("enter a 4 digit login pin:");
				pin =s.nextInt();
				System.out.println("enter a 4 digit transaction pin:");
				t_pin =s.nextInt();
				a.process1(1);
				if(Account.create( name, pin,  t_pin))
				{
					System.out.println("MSG: NOW YOU CAN LOGIN :)");
				}
				else
				{
					System.out.println("ERR: Account creation falid: Enter Valid Data");
				}
				}
				catch(Exception e) {
					System.out.println("ERR:: Enter Valid Data");
					choice=5;
				}
				//return false;
				break;
			case 5:
				//exit
				a.process1(2);
				System.out.println("Exited Successfully!\n\n Thank You :)");
				break;
			//	return false;
				
			default:
				System.out.println("invalid input.");
				break;
				
			}
			//System.out.println("8531:");
			 if (choice == 5) // FOR END THE WHILE LOOP WE NEED THIS ONE
			 {
				 //System.out.println("113:");
                 break;
             }
		}
		catch (Exception e) 
			{
                System.out.println("\n   Enter Valid Entry!\n    TRY AGAIN!!");
               break;
           }
	}
		//s.close();
}
}





































//Random rand = new Random();
//for (int i = 1; i <= 100; i++) {
//int randomNum = rand.nextInt((999 - 100) + 1) + 100;
//System.out.println(randomNum);
//


//package JDBC;
//import java.util.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//
////import rioter.Account;
//class Accou
//{
//   
//    Scanner s=new Scanner(System.in);
//    void displaybal(int bal)
//    {
//        System.out.println("your current balance = "+ bal);
//        
//    }
//    int credit(int bal)
//    {
//        int cashin;
//        System.out.println("enter the cash to add:");
//        cashin=s.nextInt();
//        bal+=cashin;
//        displaybal(bal);
//        return bal;
//    }
//    
//    int debit(int bal)
//    {
//        int cashout;
//        System.out.println("your current balance = "+ bal+"\n");
//        System.out.println("enter the cash to withdraw:");
//        cashout=s.nextInt(); 
//        if ((bal>0)&&(bal>cashout))
//        {
//            bal-=cashout;
//            displaybal(bal);
//            
//        }
//        else 
//        {
//            System.out.println("insufficent balance.");
//        }
//        return bal;
//    }
//
//    
//}
//
//
//
//
//
//
//public class bank {
//
//	public static void main(String args[]) throws SQLException
//	{
//		
//		// SQL CALLING
//		try {
//			  Class.forName("com.mysql.cj.jdbc.Driver");
//			  
//		 }catch(ClassNotFoundException e) {
//			  //System.out.println(e);
//			  e.printStackTrace();
//		 }
//		
//		 String uname ="root";
//		 String url= "jdbc:mysql://localhost:3306/rioter";
//		 String password = "1234";
//		 String query = "select * from bank1";
//		 int bal1 =0 ;
//		 Connection con=DriverManager.getConnection(url, uname, password);
//		  Statement statement=con.createStatement();
//		  ResultSet result = statement.executeQuery(query);
//		  
//		  while (result.next())
//		  {
//			  bal1 =result.getInt(1);
//		  }
//			// System.out.println(bal1);
//			 
//			
//		// MAIN PROGRAM	 
//			 
//		 int choice, ch, pin, f=0;
//		 Scanner s=new Scanner(System.in);
//		 Account a=new Account();
//		 System.out.println("\tWelcome to ATM \n ");
//		 do
//		 {
//		            
//			 System.out.println("Enter your pin:");
//			 pin=s.nextInt();
//			 if (pin==3333)
//			 {
//		            	
//				 System.out.println("\n 1. credit \n 2. debit \n 3. display balance\n");
//				 System.out.println("enter your choice:");
//				 choice=s.nextInt();
//				 switch(choice)
//				 {
//				 case 1:
//					 bal1=a.credit(bal1);
//					 query="update bank1 set balance = "+bal1+" where name = 'boy' ";
//				     statement.executeUpdate(query);  
//					 break;
//				 case 2:
//					 bal1=a.debit(bal1);
//					 query="update bank1 set balance = "+bal1+" where name = 'boy' ";
//				     statement.executeUpdate(query);  
//					 break;
//				 case 3:
//					 a.displaybal(bal1);
//					 break;
//				 default:  System.out.println("invlid input.");
//				 break;
//				 }
//			 }
//			 else
//			 {
//				 System.out.println("invalid pin");
//				 f++;
//			 }
//			 
//			 //checking for continuation
//			 if (f>2)
//			 {
//				 ch=0;
//				 System.out.println("POYI PANI EDUTH GIVIKKADA");
//			 }
//			 else
//			 {
//				 System.out.println("Do you want to continue(Y=1/N=0):");
//				 ch=s.nextInt();
//			 }
//		 } while(ch==1);
//		// query="update bank1 set balance = "+balance+" where name = 'boy' ";
//		//  statement.executeUpdate(query);  
//		 // ResultSet result = statement.executeQuery(query);
//		 
//		 System.out.println("\n\t THANK U (-_-)");
//		 
//		
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
///*
//
//public class jdbc1 {
//
//	public static void main(String args[]) throws SQLException
//	{
//		int bal1  ;
//		 String uname ="root";
//		 String url= "jdbc:mysql://localhost:3306/rioter";
//		 String password = "1234";
//		 String query = "select * from bank1";
//
//*/
//	/*try {
//		  Class.forName("com.mysql.cj.jdbc.Driver");
//		  
//	 }catch(ClassNotFoundException e) {
//		  //System.out.println(e);
//		  e.printStackTrace();
//	 }
//*/
//
//	//try {
//		 
//	//	  Connection con=DriverManager.getConnection(url, uname, password);
//		//  Statement statement=con.createStatement();
//	//	  ResultSet result = statement.executeQuery(query);
//		/*  System.out.println("inserting records");
//		  String sql="insert into fruit values(10)";
//		  statement.executeUpdate(sql);    */
//		  
//		//  while (result.next())
//		//  {
//		/*  	  String UniversityData ="" ;
//			  for (int i =1; i<=2; i++)
//			  {
//			  UniversityData +=result.getString(i) + ":";
//			  }
//			  System.out.println(UniversityData);
//		*/  	 
//	/*	  int i=1;
//		  String UniversityData ="" ;
//		  UniversityData =result.getString(1) +" "+ i;
//		  System.out.println(UniversityData);
//		  i++;  
//		  }
//		  */
//		//	  int i=1;
//			  
//		//	  bal1 =result.getInt(1);
//			
//		 	 System.out.println(bal1);
//		//	  i++;  
//		//  }
//			  
//	//	 System.out.println(bal1);
//			  
//	/* }catch(SQLException e) {
//		  System.out.println(e);
//	 }*/
//	//System.out.println(bal1);
//	
//	
///*	
//	
//	}
//}
//
//
//*/