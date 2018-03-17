package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerSignupMid
 */
@WebServlet("/customerSignupMid.do")
public class CustomerSignupMid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerSignupMid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		//String id=request.getParameter("id");
		HttpSession hs = request.getSession();
		String cust_id = (String) hs.getAttribute("logname");
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs1=st.executeQuery("select * from users where usr_id='"+cust_id+"'");
			while(rs1.next()){
				String cnm=rs1.getString("usr_nm");
				String cpwd=rs1.getString("usr_pwd");
				String cadd=rs1.getString("usr_add");
				double cphn=rs1.getInt("usr_phn");
				String cmail=rs1.getString("usr_mail");
				//String vdesc=rs1.getString("vnd_desc");
				
				CustomerDetails cd=new CustomerDetails();
				cd.setUsr_id(cust_id);
				cd.setUsr_pwd(cpwd);
				cd.setUsr_nm(cnm);
				cd.setUsr_add(cadd);
				cd.setUsr_phn(cphn);
				cd.setUsr_mail(cmail);
				//cd.setDesc(vdesc);
				request.setAttribute("custo", cd);
				RequestDispatcher view=request.getRequestDispatcher("customerSignup.jsp");
				view.forward(request, response);
			}
			st.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("sql error!!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
