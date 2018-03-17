package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VendorProduct;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/addToCart.do")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String id=request.getParameter("vprod_id");
		String ord_amt=request.getParameter("amount");
		//out.println(id);
		//out.println(amount);
		java.util.Date date=new Date();
		//out.println(date);
		HttpSession hs1=request.getSession();
		String admin_name=(String) hs1.getAttribute("logname");
		//out.println(admin_name);
		
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from vendor_product where vprod_id='"+id+"'");
			while(rs.next()){
				String vnd_id=rs.getString("vnd_id");
				String vprod_nm=rs.getString("vprod_nm");
				String vprod_ctgry=rs.getString("vprod_ctgry");
				String vprod_desc=rs.getString("vprod_desc");
				int vprod_price=rs.getInt("vprod_price");
				int vprod_amt=rs.getInt("vprod_amt");
				
				//out.println(vprod_nm);
				
				VendorProduct vp1=new VendorProduct();
				vp1.setVprod_id(id);
				vp1.setVnd_id(vnd_id);
				vp1.setVprod_nm(vprod_nm);
				vp1.setVprod_ctgry(vprod_ctgry);
				vp1.setVprod_desc(vprod_desc);
				vp1.setVprod_price(vprod_price);
				vp1.setVprod_amt(vprod_amt);
				vp1.setOrd_amt(Integer.valueOf(ord_amt));
				
				
			//	Stack<VendorProduct> stk1=(Stack<VendorProduct>) hs.getAttribute("cart");
				//ConcurrentHashMap<K, V> stk=hs1.getAttribute("cart");
				List<VendorProduct> stk=(List<VendorProduct>) hs1.getAttribute("cart");
				
				if(stk==null)
					stk=new Stack<VendorProduct>();
				
				synchronized (stk) {
				stk.add(vp1);
				}
				hs1.setAttribute("cart",stk);
				RequestDispatcher rd=request.getRequestDispatcher("addToCart.jsp");
				rd.forward(request, response);
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
