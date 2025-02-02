package Test;
import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
@WebServlet("/reg")
@SuppressWarnings("serial")
public class register extends HttpServlet
{
	protected void doPost(HttpServletRequest req , HttpServletResponse res) throws ServletException,IOException 
	{
		UserBean ub = new UserBean();
		ub.setUname(req.getParameter("uname"));
		ub.setPword(req.getParameter("pword"));
		ub.setCity(req.getParameter("city"));
		ub.setFname(req.getParameter("fname"));
		ub.setLname(req.getParameter("lname"));
		ub.setPhno(Long.parseLong(req.getParameter("phno")));
		ub.setMid(req.getParameter("mid"));
		int k = new RegisterDAO().insert(ub);
		if(k>0) 
		{
			req.setAttribute("msg","User registered successfully..<br>");
			req.getRequestDispatcher("RegSuccess.jsp").forward(req, res);
		}
		
		
	}

}
