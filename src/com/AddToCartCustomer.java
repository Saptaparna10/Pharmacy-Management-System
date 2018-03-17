package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartCustomer
 */
@WebServlet("/addToCartCustomer.do")
public class AddToCartCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String id=request.getParameter("prod_id");
		String ord_amt=request.getParameter("amount");
		//out.println(id);
		//out.println(amount);
		java.util.Date date=new Date();
		//out.println(date);
		HttpSession hs1=request.getSession();
		String cust_name=(String) hs1.getAttribute("logname");
		//out.println(admin_name);
		
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from admin_product_sale where prod_id='"+id+"'");
			while(rs.next()){
				//String vnd_id=rs.getString("vnd_id");
				String prod_nm=rs.getString("prod_nm");
				String prod_ctgry=rs.getString("prod_ctgry");
				String prod_desc=rs.getString("prod_desc");
				int prod_price=rs.getInt("prod_price");
				int prod_amt=rs.getInt("prod_amt");
				
				//out.println(vprod_nm);
				
				AdminProduct ap1=new AdminProduct();
				ap1.setProd_id(id);
				//ap1.setVnd_id(vnd_id);
				ap1.setProd_nm(prod_nm);
				ap1.setProd_ctgry(prod_ctgry);
				ap1.setProd_desc(prod_desc);
				ap1.setProd_price(prod_price);
				ap1.setProd_amt(prod_amt);
				ap1.setOrd_amt(Integer.valueOf(ord_amt));
				
				
			//	Stack<VendorProduct> stk1=(Stack<VendorProduct>) hs.getAttribute("cart");
				//ConcurrentHashMap<K, V> stk=hs1.getAttribute("cart");
				List<AdminProduct> stk=(List<AdminProduct>) hs1.getAttribute("cart");
				
				if(stk==null)
					stk=new Stack<AdminProduct>();
				
				stk.add(ap1);
				System.out.println("Done");

				hs1.setAttribute("cart",stk);
				RequestDispatcher rd=request.getRequestDispatcher("addToCartCustomer.jsp");
				rd.forward(request, response);
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	}


