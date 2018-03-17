package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCustomerInfo
 */
@WebServlet("/addCustomerInfo.do")
public class AddCustomerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerInfo() {
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
		String usr_id=request.getParameter("uid");
		String pass=request.getParameter("pass");	
		System.out.println(usr_id+pass);
		String sql="insert into users(usr_id,usr_pwd) values(?,?)";
		try {
			System.out.println("Inside try");
			Connection con=DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, usr_id);
			ps.setString(2, pass);
			ps.executeUpdate();
			System.out.println("updated");
			ps.close();
			con.close();
			
			String msg="User added";
			request.setAttribute("logname", msg);
			RequestDispatcher rd=request.getRequestDispatcher("adminHome.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
