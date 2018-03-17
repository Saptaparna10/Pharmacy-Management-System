package com;

import java.io.IOException;
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
 * Servlet implementation class ViewAdminProductByCategry
 */
@WebServlet("/viewAdminProductByCategry.do")
public class ViewAdminProductByCategry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAdminProductByCategry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String prod_ctgry=request.getParameter("admin_ctgry");
		HttpSession hs=request.getSession();
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs1=st.executeQuery("select * from admin_product_sale where prod_ctgry='"+prod_ctgry+"' and prod_amt>0");
			List<AdminProduct> adminProducts = new ArrayList<AdminProduct>();
			while(rs1.next()){
					String prod_id=rs1.getString("prod_id");
					String prod_nm=rs1.getString("prod_nm");
					prod_ctgry=rs1.getString("prod_ctgry");
					String prod_desc=rs1.getString("prod_desc");
					int prod_amt=rs1.getInt("prod_amt");
					int prod_price=rs1.getInt("prod_price");
					
					AdminProduct vp=new AdminProduct();
					
					
					vp.setProd_id(prod_id);
					vp.setProd_nm(prod_nm);
					vp.setProd_ctgry(prod_ctgry);
					vp.setProd_desc(prod_desc);
					vp.setProd_amt(prod_amt);
					vp.setProd_price(prod_price);
					
					adminProducts.add(vp);
					
				}
			rs1.close();
			con.close();
			request.setAttribute("adminProduct",adminProducts);
			RequestDispatcher view1=request.getRequestDispatcher("adminProductByCtgry.jsp");
			view1.forward(request, response);
	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("sql error!!");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
