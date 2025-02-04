<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession session1 = request.getSession(false); // Prevents creating a new session if none exists
    if (session1 != null) {
        session1.invalidate(); // Destroy session
    }
    response.sendRedirect("login.jsp?message=You have been logged out successfully");
%>
