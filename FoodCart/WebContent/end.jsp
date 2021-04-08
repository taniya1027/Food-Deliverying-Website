<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
  
  
   int i = (int)session.getAttribute("sum");
   
   out.println("Total price : "+i);
   
   

%>

do you want to logout?<br>



<a href = "Order.jsp"> order more</a>

<form action = "LogoutServlet">
    <input type = "submit" value = "logout">
</form>


</form>

</body>
</html>