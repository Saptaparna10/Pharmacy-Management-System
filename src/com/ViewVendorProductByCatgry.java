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
 * Servlet implementation class ViewVendorProductByCatgry
 */
@WebServlet("/viewVendorProductByCatgry.do")
public class ViewVendorProductByCatgry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewVendorProductByCatgry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String vprod_ctgry=request.getParameter("vendors_ctgry");
		HttpSession hs=request.getSession();
		String vnd_id=(String) hs.getAttribute("vendor_id");
		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs1=st.executeQuery("select * from vendor_product where vnd_id='"+vnd_id+"' and vprod_ctgry='"+vprod_ctgry+"' and vprod_amt>0");
			List<VendorProduct> vendorProducts = new ArrayList<VendorProduct>();
			while(rs1.next()){
					String vndr_id=rs1.getString("vnd_id");
					String vprod_id=rs1.getString("vprod_id");
					String vprod_nm=rs1.getString("vprod_nm");
					String vnprod_ctgry=rs1.getString("vprod_ctgry");
					String vprod_desc=rs1.getString("vprod_desc");
					int vprod_amt=rs1.getInt("vprod_amt");
					int vprod_price=rs1.getInt("vprod_price");
					
					VendorProduct vp=new VendorProduct();
					
					vp.setVnd_id(vndr_id);
					vp.setVprod_id(vprod_id);
					vp.setVprod_nm(vprod_nm);
					vp.setVprod_ctgry(vnprod_ctgry);
					vp.setVprod_desc(vprod_desc);
					vp.setVprod_amt(vprod_amt);
					vp.setVprod_price(vprod_price);
					
					vendorProducts.add(vp);
					
				}
			rs1.close();
			con.close();
			request.setAttribute("vendorProduct", vendorProducts);
			RequestDispatcher view1=request.getRequestDispatcher("vendorProductByCtgry.jsp");
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
