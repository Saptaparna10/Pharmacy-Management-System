package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewCustomerOrder
 */
@WebServlet("/viewCustomerOrder.do")
public class ViewCustomerOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCustomerOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		Set<UserIdOrderIdDisplay> user_order=new HashSet<UserIdOrderIdDisplay>();
		
		int flag=0;
		
		HttpSession hs=request.getSession();
		String admin_id=(String) hs.getAttribute("logname");
		try{
			Connection con1=DataConnection.getConnection();
			Statement st=con1.createStatement();
			ResultSet rs=st.executeQuery("select * from user_order where uord_stat='0'");
			while(rs.next()){
				String uord_id=rs.getString("uord_id");
				String usr_id=rs.getString("usr_id");
				System.out.println("inside while");
				for(UserIdOrderIdDisplay temp:user_order){
					if(usr_id.equals(temp.getUsr_id()) && uord_id.equals(temp.getUord_id())){
						System.out.println("match found");
						flag=1;
						break;
					}
					System.out.println("Outside if");
					flag=0;
				}
				System.out.println("outside for");
				if(flag==0){
				UserIdOrderIdDisplay uioid=new UserIdOrderIdDisplay();
				uioid.setUsr_id(usr_id);
				uioid.setUord_id(uord_id);
				user_order.add(uioid);
				
				System.out.println("Data inserted");
				}
			}
			
			request.setAttribute("user_order_ids",user_order);
			RequestDispatcher view=request.getRequestDispatcher("approveCustomerOrder.jsp");
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
