package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteVendorProductFromCart
 */
@WebServlet("/deleteVendorProductFromCart.do")
public class DeleteVendorProductFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVendorProductFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		HttpSession hs=request.getSession();
		@SuppressWarnings("unchecked")
		java.util.List<com.VendorProduct> stk=(java.util.List<com.VendorProduct>)hs.getAttribute("cart"); 
		
		try{
		for(com.VendorProduct p:stk){
			if(p.getVprod_id().equals(id)){
				stk.remove(p);
			}
			
		}
		}catch(Exception e)
		{
			
		}
		hs.setAttribute("cart", stk);
		RequestDispatcher view=request.getRequestDispatcher("checkOut.do");
		view.forward(request, response);
		
}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
