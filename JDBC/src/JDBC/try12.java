package JDBC;
import java.io.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.sql.*;
public class try12 {
	
	public static void main(String args[])
	{
		System.out.println("-----------------------------------------------------------");
		System.out.printf("%12s %10s %11s%n", "Account No", "Name", "Balance");
		//System.out.printf( "%12s %10s %11s%n",args);
		//System.out.printf("%B%n", 5.3);
		//System.out.printf("baeldung%nline f%nterminator", args);
		 System.out.printf("%12d %10s %11d.00\n",23332,"sfs",453);
		 //converter for upper/lower
		 System.out.printf("%c%n", 's');
		 System.out.printf("%C%n", 'a');
	}
}
