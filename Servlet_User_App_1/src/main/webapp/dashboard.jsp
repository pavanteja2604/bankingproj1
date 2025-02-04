<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>DashBoard</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("username") %></h2>
    <h3>Balance: $<%= session.getAttribute("balance") %></h3>

    <form action="DashboardServlet" method="post">
        Amount: <input type="number" name="amount" required><br>
        <input type="submit" name="action" value="Deposit">
        <input type="submit" name="action" value="Withdraw">
    </form>

    <% if (request.getParameter("success") != null) { %>
        <p style="color:green;"><%= request.getParameter("success") %></p>
    <% } %>
    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;"><%= request.getParameter("error") %></p>
    <% } %>
    
    <a href="logout.jsp">Logout</a>
</body>
</html>

