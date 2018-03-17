package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewCustomerProducts
 */
@WebServlet("/viewCustomerProducts.do")
public class ViewCustomerProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCustomerProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			HttpSession hs = request.getSession();
			String cust_id = (String) hs.getAttribute("logname");
			System.out.println("Ami "+ cust_id);
			ResultSet rs1=st.executeQuery("select * from user_product where usr_id = '"+cust_id+"'");
			List<CustomerProduct> custProducts = new ArrayList<CustomerProduct>();
			while(rs1.next()){
					String uprod_id=rs1.getString("uprod_id");
					String uprod_nm=rs1.getString("uprod_nm");
					String uprod_ctgry=rs1.getString("uprod_ctgry");
					String uprod_desc=rs1.getString("uprod_desc");
					int uprod_amt=rs1.getInt("uprod_amt");
					int uprod_price=rs1.getInt("uprod_price");
					System.out.println("Hii " +uprod_id);
					
					CustomerProduct up=new CustomerProduct();
					
					up.setUprod_id(uprod_id);
					
					up.setUprod_nm(uprod_nm);
					up.setUprod_ctgry(uprod_ctgry);
					up.setUprod_desc(uprod_desc);
					up.setUprod_amt(uprod_amt);
					up.setUprod_price(uprod_price);
					
					custProducts.add(up);
					
				}
			rs1.close();
			con.close();
			request.setAttribute("custProduct", custProducts);
			RequestDispatcher view1=request.getRequestDispatcher("customerProducts.jsp");
			view1.forward(request, response);
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("sql error!!");
		}	
	}

	}


