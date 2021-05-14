package com;

import model.Order;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Orders")
public class OrderService {

	Order orderObj = new Order(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() { 
			return orderObj.readOrders(); 
	 }
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertOrder(@FormParam("Order_date") String order_date,
	 @FormParam("Project_ID") String project_id, 
	 @FormParam("Project_name") String project_name,
	 @FormParam("Sponsor_ID") String sponsor_id,
	 @FormParam("Budget") String budget) 
	{ 
	 String output = orderObj.insertOrder( order_date, project_id, project_name, sponsor_id, budget); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateOrder(String orderData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String orderID = orderObject.get("Order_ID").getAsString();
	 String order_date = orderObject.get("Order_date").getAsString();
	 String project_id = orderObject.get("Project_ID").getAsString(); 
	 String project_name = orderObject.get("Project_name").getAsString(); 
	 String sponsor_id = orderObject.get("Sponsor_ID").getAsString();
	 String budget = orderObject.get("Budget").getAsString();
	 
	 String output = orderObj.updateOrder( orderID, order_date, project_id, project_name, sponsor_id, budget); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String orderData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(orderData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <orderID>
	 String orderID = doc.select("Order_ID").text(); 
	 String output = orderObj.deleteOrder( orderID); 
	return output; 
	}
}
