<%@ page import="model.Order" %>
<%@ page import="com.OrderService" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Order Management </title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Orders.js"></script>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-8">
		
			<h1> Order Management </h1>
		
			<form id="formOrder" name="formOrder" >
 				Enter date:
 				<input id="Order_date" name="Order_date" type="date" class="form-control form-control-sm" placeholder="yyyy-mm-dd">
 				<br> 
 				Enter Project ID:
 				<input id="Project_ID" name="Project_ID" type="text" class="form-control form-control-sm">
 				<br> 
 				Enter Project name:
 				<input id="Project_name" name="Project_name" type="text" class="form-control form-control-sm" placeholder="Project name">
 				<br> 
 				Enter Sponsor ID:
 				<input id="Sponsor_ID" name="Sponsor_ID" type="text" class="form-control form-control-sm">
 				<br> 
 				Enter Budget:
 				<input id="Budget" name="Budget" type="text" class="form-control form-control-sm">
 				<br> 
 		
 				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 				<input type="hidden" id="hidOrderIDSave" name="hidOrderIDSave" value="">
 		
			</form>
	
			<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				</div> 
				<br>
					<div id="divOrdersGrid" class="col-12">
 						<%
 							Order orderObj = new Order();
 							out.print(orderObj.readOrders());
 						%>
					</div>
			
		</div> 
	</div>

</body>
</html>