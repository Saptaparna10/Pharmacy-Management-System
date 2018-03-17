package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerPlaceOrder
 */
@WebServlet("/customerPlaceOrder.do")
public class CustomerPlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerPlaceOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String price=request.getParameter("price");
		HttpSession hs=request.getSession();
		List<AdminProduct> stk=(List<AdminProduct>)hs.getAttribute("cart");
		String cust_id=(String) hs.getAttribute("logname");
		System.out.println(cust_id);
		java.sql.Date date=new java.sql.Date(new Date().getTime());
		
		int s=new Date().getSeconds();
		int m=new Date().getMinutes();
		int h=new Date().getHours();
		Long t=new Date().getTime();
		int dt=new Date().getDate();
		
		String corder_id=cust_id+date+s+m+h;
		int av_amt = 0;
		
		
		String sql="insert into user_order values(?,?,to_date(?,'dd-mon-yyyy'),?,?)";
		String sql1="insert into user_order_prod values(?,?,?)";
		String sql2="insert into user_tran_det(usr_id,uord_id,money) values(?,?,?)";
		String sql3="insert into user_curr_tran_det values(?,?,?)";
		String sql4 = "update admin_product_sale set prod_amt = ? where prod_id = ?";
		String sql5 = "select prod_amt from admin_product_sale where prod_id = ?";
		System.out.println(price+" "+cust_id+" "+date+" "+corder_id);
		Connection con;
		try {
			con = DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			PreparedStatement ps1=con.prepareStatement(sql1);
			PreparedStatement ps4 = con.prepareStatement(sql4);
			PreparedStatement ps5 = con.prepareStatement(sql5);
			ps.setString(1, cust_id);
			ps.setString(2, corder_id);
			ps.setDate(3, date);
			ps.setInt(4,Integer.valueOf(price));
			ps.setInt(5, 0);
			ps.executeUpdate();
			System.out.println("User_order updated");
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, cust_id);
			ps2.setString(2, corder_id);
			ps2.setInt(3, Integer.valueOf(price));
			ps2.executeUpdate();
			System.out.println("2nd one");
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps3.setString(1, cust_id);
			ps3.setString(2, corder_id);
			ps3.setInt(3, Integer.valueOf(price));
			ps3.executeUpdate();
			System.out.println("3rd one");
			
			for(AdminProduct p:stk){
				System.out.println("for loop");
				System.out.println(corder_id);
				ps1.setString(1, corder_id);
				
				//ps1.setString(2, p.getVnd_id());
				System.out.println(p.getProd_id());
				ps1.setString(2, p.getProd_id());

				System.out.println(p.getOrd_amt());	
				ps1.setInt(3, p.getOrd_amt());
				ps1.executeUpdate();
				System.out.println("User_order updated finally");
				ps5.setString(1, p.getProd_id());
				ResultSet rss = ps5.executeQuery();
				while(rss.next()){
					av_amt = rss.getInt("prod_amt");
					System.out.println("Ache" + av_amt +"ta");
				}
				int new_amt = av_amt - p.getOrd_amt();
				System.out.println("kinchi " +p.getOrd_amt());
				ps4.setInt(1, new_amt);
				 
				ps4.setString(2, p.getProd_id());
				ps4.executeUpdate();
				System.out.println("Amount update holo "+ p.getProd_id() +" er");
				System.out.println("Ache" + new_amt +"ta");
			}
			
			
			
			
			con.close();
			ps.close();
			ps1.close();
			
			request.setAttribute("corder_id", corder_id);
			RequestDispatcher view=request.getRequestDispatcher("customerPlaceOrder.jsp");
			view.forward(request, response);
		//	response.sendRedirect("adminPlaceOrder.jsp?order_id='"+order_id+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("SQL error!!");
			e.printStackTrace();
		}
		
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
