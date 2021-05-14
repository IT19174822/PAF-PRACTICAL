/**
 **/
$.ajax(
{
 url : "OrdersAPI",
 type : type,
 data : $("#formOrder").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onOrderSaveComplete(response.responseText, status);
 }
});

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidOrderIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "OrdersAPI",
 type : type,
 data : $("#formOrder").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onOrderSaveComplete(response.responseText, status);
 }
 });
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidOrderIDSave").val($(this).data("Order_ID"));
 $("#Order_date").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Project_ID").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Project_name").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Sponsor_ID").val($(this).closest("tr").find('td:eq(3)').text());
$("#Budget").val($(this).closest("tr").find('td:eq(4)').text());
});
//DELETE===================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "OrdersAPI",
 type : "DELETE",
 data : "Order_ID=" + $(this).data("Order_ID"),
 dataType : "text",
 complete : function(response, status)
 {
 onOrderDeleteComplete(response.responseText, status);
 }
 });
});
// CLIENT-MODEL================================================================
function validateOrderForm()
{
// ORDER DATE
if ($("#Order_date").val().trim() == "")
 {
 return "Insert Order Date.";
 }
// PROJECT ID
if ($("#Project_ID").val().trim() == "")
 {
 return "Insert Project ID.";
 }
// PROJECT NAME
if ($("#Project_name").val().trim() == "")
 {
 return "Insert Project Name.";
 }
// SPONSOR ID
if ($("#Sponsor_ID").val().trim() == "")
 {
 return "Insert Sponsor ID.";
 }
// BUDGET-------------------------------
if ($("#Budget").val().trim() == "")
 {
 return "Insert Budget.";
 }
// is numerical value
var tmpBudget = $("#Budget").val().trim();
if (!$.isNumeric(tmpBudget))
 {
 return "Insert a numerical value for Budget.";
 }
// convert to decimal price
 $("#Budget").val(parseFloat(tmpBudget).toFixed(2));

return true;
}
//============================================================
function onOrderSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divOrdersGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
 $("#hidOrderIDSave").val("");
 $("#formOrder")[0].reset();
}
//==========================================================
function onOrderDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divOrdersGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}