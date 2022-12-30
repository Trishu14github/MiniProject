package com.ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class registration{
	private static Connection con;
    private static PreparedStatement ps;
	public static void setData() {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter customer name");
		String name=sc.next();
		System.out.println("enter mobile number");
		String number=sc.next();
		System.out.println("enter email");
		String email=sc.next();
		System.out.println("enter password");
		String password=sc.next();
		
		try {
		 con=MyConnection.getConnection();
  ps=con.prepareStatement("insert into customerinfo(Email,Password,CustomerName,MobileNumber) values(?,?,?,?) ");
			ps.setString(1,email);
			ps.setString(2,password);
			ps.setString(3,name);
			ps.setString(4,number);
			ps.executeUpdate();
			System.out.println("Registration successful");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			Login.checkData();
		}	
	}
}
class Login{
	private static Connection con;
    private static PreparedStatement ps;
public static void checkData()  {
		Scanner sc=new Scanner(System.in);
		System.out.println(" For Login");
		System.out.println("Enter Email Id");
		String email=sc.next();
		System.out.println("Enter password");
		String pass=sc.next();
	   try {
			con=MyConnection.getConnection();
			ps=con.prepareStatement("select Email,Password from customerinfo");
			ResultSet rs=ps.executeQuery();
			int count=0;
			while(rs.next()) {
				if(email.equalsIgnoreCase(rs.getString("Email")) && pass.equals(rs.getString("Password"))) {
					count++;
					break;
				}		
		   	}
			if(count>0) {
				System.out.println("Login successful");
			}
			else {
				Login.checkData();
			}
			
		 } 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				ps.close();
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		 }
	   System.out.println("If you want to buy product from cart then press YES otherwise press NO");
		String s=sc.next();
if(s.equalsIgnoreCase("yes")) {
			try {
				BuyProduct.getBuyProduct(email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
  }
else {		
	   try {
		con=MyConnection.getConnection();
		ps=con.prepareStatement("select * from productinfo  Order by price ");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		    {
			//System.out.printf("%14s,%15s,%21s,%16s,%15s","p_id","p_name","p_description","price","quantity");
			System.out.print("productid:"+rs.getInt( "p_id")+" ,");
			System.out.print("productname:"+rs.getString("p_name")+" ,");
			System.out.print("description:"+rs.getString("p_description")+" ,");
			System.out.print("price:"+rs.getInt("price")+" ,");
			System.out.println("quantity:"+rs.getString("quantity")+".");
			System.out.println("================***====================");
		    }
	     } 
	   catch (SQLException e) {
		e.printStackTrace();
	   }
	   finally {
		     try {
			  con.close();
			  ps.close();
	    	 }
		     catch (Exception e2) {
			e2.printStackTrace();
		    }
	      }
	    System.out.println("For buying select the product which will be added to cart ");
	    Cart.addCart(email);
     }
   }
}
class Cart{
	private static Connection con;
    private static PreparedStatement ps;
	public static void addCart(String email) {
		Scanner sc=new Scanner(System.in);
		System.out.println("select how many products you want to add to cart");
		int products=sc.nextInt();
		for(int i=1;i<=products;i++) {
		 System.out.println("enter product name");
		String productName=sc.next();
		con=MyConnection.getConnection();
		try {
			ps=con.prepareStatement("select p_id from productinfo where"+" p_name"+"="+"'"+productName+"'");
			ResultSet rs=ps.executeQuery();                              
			while(rs.next()) {
				int x=rs.getInt("p_id");
				ps=con.prepareStatement("insert into cart(pro_Id,email) values(?,?)");
				  ps.setInt(1, x);
				  ps.setString(2, email);
			    ps.executeUpdate();
				/*ps1=con.prepareStatement("insert into addtocart(Proid) values(?)");
				ps1.setInt(1,x);
			   int count=ps1.executeUpdate();
			   System.out.println(count+" product added to cart");*/
			}
		 	
		 } catch (SQLException e) {
			e.printStackTrace();
		 }	
	  }
		
    System.out.println("All products added successfully");
    System.out.println("============***=================");	    
	  try {
		ps=con.prepareStatement("select sum(Price) from productinfo inner join Cart on p_id=pro_Id where "+"Cart.email"+"="+"'"+email+"'");
		ResultSet rs=ps.executeQuery();
	    while(rs.next()) {
	    	int sum=rs.getInt(1);
	    System.out.println(" Total payable amount of added products is "+sum);
	    }
	  } catch (SQLException e) {
		  e.printStackTrace();
	   }
	  finally {
		  try {
			con.close();
			ps.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	  }
	  System.out.println("=================***=====================");
  	System.out.println("For Buying press >> B"); 
    String s=sc.next();
        if(s.equalsIgnoreCase("B"))
	   {
    	try {
			BuyProduct.getBuyProduct(email);
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	   }
        else {
        	System.out.println("Thank you for adding products to cart");
        }
    
	}
}
public class Main {
    
	public static void main(String[] args) throws SQLException  {
		Scanner sc=new Scanner(System.in);
		System.out.println("FOR USER PRESS 1 & FOR ADMIN PRESS 2");
		int i=sc.nextInt();
	if(i==1)
	{
		System.out.println("FOR NEW ACCOUNT PRESS YES & FOR OLD ACCOUNT PRESS NO");
		String st=sc.next();
		if(st.equalsIgnoreCase("yes")) {
			registration.setData();
		}
		if(st.equalsIgnoreCase("no")) {
			Login.checkData();
		}
	}
	else if(i==2)
	   {
			System.out.println("enter admin id");
			int id=sc.nextInt();
			if(id==10) {
				Admin.adminDetails();
			   }
	        else {  
				System.out.println("invalid input");
				Main.main(null);
		      }	
		}
	}
    
}

