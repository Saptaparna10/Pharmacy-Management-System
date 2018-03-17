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

/**
 * Servlet implementation class VendorLogin
 */
@WebServlet("/vendorLogin.do")
public class VendorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendorLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("uname");
		String password=request.getParameter("pass");
		HttpSession session = request.getSession();

		PrintWriter out=response.getWriter();
		try{
			Connection con1=DataConnection.getConnection();
			String sql="Select vnd_stat from vendor_master where vnd_id=? and vnd_pwd=?";
			PreparedStatement ps=con1.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery(); 
			
			if(rs.next()){
			int count=rs.getInt(1);
			System.out.println(count);
			if(count==1){
				/*response.sendRedirect("adminHome.jsp?logname="+username);*/
				//username="Welcome "+username;
				session.setAttribute("logname",username);
				RequestDispatcher rd=request.getRequestDispatcher("vendorHome.jsp");
				rd.forward(request, response);
				
			}
			else if(count==0){
				/*response.sendRedirect("adminHome.jsp?logname="+username);*/
				username="Waiting for admin approval";
				session.setAttribute("logname",username);
				RequestDispatcher rd=request.getRequestDispatcher("vendorLogin.jsp");
				rd.forward(request, response);
				
			}
			else if(count==2){
				/*response.sendRedirect("adminHome.jsp?logname="+username);*/
				username="Your application has been rejected";
				session.setAttribute("logname",username);
				RequestDispatcher rd=request.getRequestDispatcher("vendorLogin.jsp");
				rd.forward(request, response);
				
			}
			}
			
			else{	//out.println("<h2 style='color:red'>Invalid username/password</h2>");
				//out.println("<a href='index.html'>Try again</a>");
				username="Invalid username or password";
				request.setAttribute("logname",username);
				RequestDispatcher rd=request.getRequestDispatcher("vendorLogin.jsp");
				rd.forward(request, response);
			}
			
			ps.close();
			con1.close();
			
		}catch (SQLException e) {
			out.println("SQL Error: "+e.getMessage());
		}
	
	}

}
