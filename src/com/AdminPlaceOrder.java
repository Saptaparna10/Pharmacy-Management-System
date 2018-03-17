package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

/**
 * Servlet implementation class AdminPlaceOrder
 */
@WebServlet("/adminPlaceOrder.do")
public class AdminPlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPlaceOrder() {
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
		List<VendorProduct> stk=(List<VendorProduct>)hs.getAttribute("cart");
		String admin_id=(String) hs.getAttribute("logname");
		java.sql.Date date=new java.sql.Date(new Date().getTime());
		
		int s=new Date().getSeconds();
		int m=new Date().getMinutes();
		int h=new Date().getHours();
		Long t=new Date().getTime();
		int dt=new Date().getDate();
		
		String order_id=admin_id+date+s+m+h;
		int av_amt = 0;
		
		
		String sql="insert into admin_order values(?,?,to_date(?,'yyyy-mm-dd'),?,?)";
		String sql1="insert into admin_order_prod values(?,?,?,?)";
		String sql2="insert into admin_tran_det(admin_id,aord_id,money) values(?,?,?)";
		String sql3="insert into admin_curr_tran_det values(?,?,?)";
		String sql4 = "update vendor_product set vprod_amt = ? where vnd_id = ? and vprod_id = ?";
		String sql5 = "select vprod_amt from vendor_product where vnd_id = ? and vprod_id = ?";
		//out.println(price+" "+admin_id+" "+date+" "+order_id);
		Connection con;
		try {
			con = DataConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			PreparedStatement ps1=con.prepareStatement(sql1);
			PreparedStatement ps4 = con.prepareStatement(sql4);
			PreparedStatement ps5 = con.prepareStatement(sql5);
			ps.setString(1, admin_id);
			ps.setString(2, order_id);
			ps.setDate(3, date);
			ps.setInt(4,Integer.valueOf(price));
			ps.setInt(5, 0);
			ps.executeUpdate();
			System.out.println("1st one");
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, admin_id);
			ps2.setString(2, order_id);
			ps2.setInt(3, Integer.valueOf(price));
			ps2.executeUpdate();
			System.out.println("2nd one");
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps3.setString(1, admin_id);
			ps3.setString(2, order_id);
			ps3.setInt(3, Integer.valueOf(price));
			ps3.executeUpdate();
			System.out.println("3rd one");
			for(VendorProduct p:stk){
				ps1.setString(1, order_id);
				ps1.setString(2, p.getVnd_id());
				ps1.setString(3, p.getVprod_id());
				ps1.setInt(4, p.getOrd_amt());
				ps1.executeUpdate();
				ps5.setString(1, p.getVnd_id());
				ps5.setString(2, p.getVprod_id());
				System.out.println("Amount update hobe "+ p.getVprod_id() +" er");
				ResultSet rss = ps5.executeQuery();
				while(rss.next()){
					av_amt = rss.getInt("vprod_amt");
					System.out.println("Ache" + av_amt +"ta");
				}
				int new_amt = av_amt - p.getOrd_amt();
				System.out.println("kinchi " +p.getOrd_amt());
				ps4.setInt(1, new_amt);
				 
				ps4.setString(2, p.getVnd_id());
				ps4.setString(3, p.getVprod_id());
				ps4.executeUpdate();
				System.out.println("Amount update holo "+ p.getVprod_id() +" er");
				System.out.println("Ache" + new_amt +"ta");
			}
			
			
			
			
			con.close();
			ps.close();
			ps1.close();
			ps2.close();
			ps3.close();
			
			request.setAttribute("order_id", order_id);
			RequestDispatcher view=request.getRequestDispatcher("adminPlaceOrder.jsp");
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
