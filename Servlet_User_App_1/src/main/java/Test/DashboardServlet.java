package Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String action = request.getParameter("action");

        try {
            Connection con = DBConnection.getCon();
            double balance = (double) session.getAttribute("balance");

            if ("Deposit".equals(action)) {
                balance += amount;
            } else if ("Withdraw".equals(action) && balance >= amount) {
                balance -= amount;
            } else {
                response.sendRedirect("dashboard.jsp?error=Insufficient Funds");
                return;
            }

            PreparedStatement ps = con.prepareStatement("UPDATE users SET balance=? WHERE username=?");
            ps.setDouble(1, balance);
            ps.setString(2, username);
            ps.executeUpdate();

            session.setAttribute("balance", balance);
            response.sendRedirect("dashboard.jsp?success=Transaction Successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
