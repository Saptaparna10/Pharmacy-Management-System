package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApproveAdminOrder
 */
@WebServlet("/viewAdminOrder.do")
public class ViewAdminOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAdminOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		Set<AdminIdOrderIdDisplay> admin_order=new HashSet<AdminIdOrderIdDisplay>();
		
		int flag=0;
		
		HttpSession hs=request.getSession();
		String vnd_id=(String) hs.getAttribute("logname");
		try{
			Connection con1=DataConnection.getConnection();
			Statement st=con1.createStatement();
			ResultSet rs=st.executeQuery("select * from admin_order ao,admin_order_prod aop where ao.aord_id=aop.aord_id and aord_stat='0' and aop.vnd_id='"+vnd_id+"'");
			while(rs.next()){
				String admin_id=rs.getString("admin_id");
				String aord_id=rs.getString("aord_id");
				System.out.println("inside while");
				for(AdminIdOrderIdDisplay temp:admin_order){
					if(admin_id.equals(temp.getAdmin_id()) && aord_id.equals(temp.getAord_id())){
						System.out.println("match found");
						flag=1;
						break;
					}
					System.out.println("Outside if");
					flag=0;
				}
				System.out.println("outside for");
				if(flag==0){
				AdminIdOrderIdDisplay aioid=new AdminIdOrderIdDisplay();
				aioid.setAdmin_id(admin_id);
				aioid.setAord_id(aord_id);
				admin_order.add(aioid);
				
				System.out.println("Data inserted");
				}
			}
			
			request.setAttribute("admin_order_ids",admin_order);
			RequestDispatcher view=request.getRequestDispatcher("approveAdminOrder.jsp");
			view.forward(request, response);
			st.close();
			con1.close();
			
		}catch(SQLException e){
			out.println("SQL Error: "+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
