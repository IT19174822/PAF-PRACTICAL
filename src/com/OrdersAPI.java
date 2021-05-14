package com;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;

/**
 * Servlet implementation class OrdersAPI
 */
@WebServlet("/OrdersAPI")
public class OrdersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// initialize a server-model class object
		Order orderObj = new Order();
    /**
     * Default constructor. 
     */
    public OrdersAPI() {
        // TODO Auto-generated constructor stub
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = orderObj.insertOrder(request.getParameter("Order_date"), 
				 request.getParameter("Project_ID"), 
				request.getParameter("Project_name"),
				request.getParameter("Sponsor_ID"),
				request.getParameter("Budget")); 
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		
		String output = orderObj.updateOrder(paras.get("hidOrderIDSave").toString(), 
				 paras.get("Order_date").toString(), 
				 paras.get("Project_ID").toString(), 
				 paras.get("Project_name").toString(), 
				 paras.get("Sponsor_ID").toString(),
		 		 paras.get("Budget").toString());
		 
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		
		String output = orderObj.deleteOrder(paras.get("Order_ID").toString()); 
		response.getWriter().write(output);
	}

	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request) {
			Map<String, String> map = new HashMap<String, String>();
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
				scanner.useDelimiter("\\A").next() : "";
				scanner.close();
						
				String[] params = queryString.split("&");
				for (String param : params)
				{
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			}
			catch (Exception e){
				
			}
			
		return map;
		}
	
}
