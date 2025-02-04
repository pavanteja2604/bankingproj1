package Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String transactionType = request.getParameter("transactionType");

        try (Connection conn = DatabaseUtil.getConnection()) {
            // Update balance based on transaction type
            String balanceSql = "SELECT balance FROM Accounts WHERE account_id = ?";
            try (PreparedStatement psBalance = conn.prepareStatement(balanceSql)) {
                psBalance.setInt(1, accountId);
                ResultSet rs = psBalance.executeQuery();
                if (rs.next()) {
                    double currentBalance = rs.getDouble("balance");
                    double newBalance = transactionType.equals("Deposit") ? currentBalance + amount : currentBalance - amount;

                    // Update the account balance
                    String updateSql = "UPDATE Accounts SET balance = ? WHERE account_id = ?";
                    try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                        psUpdate.setDouble(1, newBalance);
                        psUpdate.setInt(2, accountId);
                        psUpdate.executeUpdate();
                    }

                    // Log the transaction
                    String transactionSql = "INSERT INTO Transactions (account_id, transaction_date, transaction_amount, transaction_type) VALUES (?, SYSDATE, ?, ?)";
                    try (PreparedStatement psTransaction = conn.prepareStatement(transactionSql)) {
                        psTransaction.setInt(1, accountId);
                        psTransaction.setDouble(2, amount);
                        psTransaction.setString(3, transactionType);
                        psTransaction.executeUpdate();
                    }
                }
            }
            response.sendRedirect("transactionSuccess.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
