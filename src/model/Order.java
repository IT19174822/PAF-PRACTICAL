package model;

import java.sql.*;
import java.sql.Connection;


public class Order {
	
	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
	 
	//For testing
	 System.out.println("Successfully Connected");
	 } 
	 catch (Exception e) 
	 {
		 e.printStackTrace();
	 } 
	 return con; 
	 } 
	
	public String insertOrder(String Order_date, String Project_ID, String Project_name, String Sponsor_ID, String Budget) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting.";
				}
			
			// create a prepared statement
			String query = " insert into orders(`Order_ID`,`Order_date`,`Project_ID`,`Project_name`,`Sponsor_ID`,`Budget`)" + " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Order_date);
			preparedStmt.setString(3, Project_ID);
			preparedStmt.setString(4, Project_name);
			preparedStmt.setString(5, Sponsor_ID);
			preparedStmt.setDouble(6, Double.parseDouble(Budget));
	 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			String newOrders = readOrders(); 
			output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}";
			} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the order.\"}"; 
			System.err.println(e.getMessage()); 
			} 
		return output; 
		} 
	
	public String readOrders() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Order ID</th>" 
			+ "<th>Order Date</th>" 
					+ "<th>Project ID</th>" 
			+ "<th>Project name</th>" 
					+ "<th>Sponsor</th>" 
			+ "<th>Budget</th>" 
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from orders"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String Order_ID = Integer.toString(rs.getInt("Order_ID"));
				String Order_date = rs.getString("Order_date");
				String Project_ID = rs.getString("Project_ID"); 
				String Project_name = rs.getString("Project_name");
				String Sponsor_ID = Integer.toString(rs.getInt("Sponsor_ID"));
				String Budget = Double.toString(rs.getDouble("Budget"));
				
				// Add into the html table
				output += "<tr><td>" + Order_ID + "</td>";
				output += "<td>" + Order_date + "</td>";
				output += "<td>" + Project_ID + "</td>"; 
				output += "<td>" + Project_name + "</td>"; 
				output += "<td>" + Sponsor_ID + "</td>";
				output += "<td>" + Budget + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate'"
						+ "type='button' value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove'"
						+ "type='button' value='Remove'"
						+ "class='btnRemove btn btn-danger'"
						+ "data-orderid='"
						+ Order_ID + "'>" + "</td></tr>";
				
			} 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the order details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String updateOrder(String Order_ID, String Order_date, String Project_ID, String Project_name, String Sponsor_ID, String Budget)
	{
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for updating.";
			} 
			
			// create a prepared statement
			String query = "UPDATE orders SET Order_date=?,Project_ID=?,Project_name=?,Sponsor_ID=?,Budget=? WHERE Order_ID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, Order_date);
			preparedStmt.setString(2, Project_ID);
			preparedStmt.setString(3, Project_name);
			preparedStmt.setString(4, Sponsor_ID);
			preparedStmt.setDouble(5, Double.parseDouble(Budget));
			preparedStmt.setInt(6, Integer.parseInt(Order_ID));
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			String newOrders = readOrders(); 
			output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}"; 
			
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the order details.\"}";  
			 System.err.println(e.getMessage()); 
		 } 
		
		return output;
    } 
	
		public String deleteOrder(String Order_ID) 
		{ 
			String output = "";
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for deleting.";
				} 
				
				// create a prepared statement
				String query = "delete from orders where Order_ID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(Order_ID));
				
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				
				String newOrders = readOrders(); 
				output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}"; 
					
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the order details.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			
			return output; 
		 } 
}
