package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DataConnection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("uname");
		String password=request.getParameter("pass");

		PrintWriter out=response.getWriter();
		try{
			Connection con1=DataConnection.getConnection();
			String sql="Select count(*) from admin where admin_id=? and admin_pwd=?";
			PreparedStatement ps=con1.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery(); 
			
			rs.next();
			int count=rs.getInt(1);
			if(count>0){
				/*response.sendRedirect("adminHome.jsp?logname="+username);*/
				HttpSession session = request.getSession();
				username=username;
				session.setAttribute("logname",username);
				RequestDispatcher rd=request.getRequestDispatcher("adminHome.jsp");
				rd.forward(request, response);
				
			}
			else{
				//out.println("<h2 style='color:red'>Invalid username/password</h2>");
				//out.println("<a href='index.html'>Try again</a>");
				//response.sendRedirect("loginFail.jsp");
				username="Invalid user id or password";
				HttpSession session = request.getSession();
				session.setAttribute("logname",username);
				RequestDispatcher rd=request.getRequestDispatcher("adminLogin.jsp");
				rd.forward(request, response);
			}
			
			ps.close();
			con1.close();
			
		}catch (SQLException e) {
			out.println("SQL Error: "+e.getMessage());
		}
	
	}

}
