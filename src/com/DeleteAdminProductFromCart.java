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
 * Servlet implementation class DeleteAdminProductFromCart
 */
@WebServlet("/deleteAdminProductFromCart.do")
public class DeleteAdminProductFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAdminProductFromCart() {
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
		java.util.List<com.AdminProduct> stk=(java.util.List<com.AdminProduct>)hs.getAttribute("cart"); 
		
		try{
		for(com.AdminProduct p:stk){
			if(p.getProd_id().equals(id)){
				stk.remove(p);
			}
			
		}
		}catch(Exception e)
		{
			
		}
		hs.setAttribute("cart", stk);
		RequestDispatcher view=request.getRequestDispatcher("checkOutCustomer.do");
		view.forward(request, response);
		

		
	}

}
