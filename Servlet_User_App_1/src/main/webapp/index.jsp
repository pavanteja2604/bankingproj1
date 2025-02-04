<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Banking System - Login</title>
</head>
<body>
    <h2>Login to Banking System</h2>
    <form action="loginServlet" method="post">
        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" required><br><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>
    <br>
    <a href="addCustomer.jsp">Register as a new customer</a>
</body>
</html>
