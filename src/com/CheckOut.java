package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VendorProduct;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/checkOut.do")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int price=0;
		PrintWriter out=response.getWriter();
		HttpSession hs=request.getSession();
		try{
		List<VendorProduct> stk=(List<VendorProduct>)hs.getAttribute("cart");
		//String admin_id=(String) hs.getAttribute("logname");
		
		for(VendorProduct p:stk){
			price = price + p.getOrd_amt()*p.getVprod_price();
			
			/*out.println(p.getVprod_nm());
			out.println(p.getVprod_price());
			out.println(p.getVprod_desc());
			out.println(p.getVprod_id());
			out.println(p.getOrd_amt());
			out.println();*/
			
		}
		request.setAttribute("totalPrice", price);
		RequestDispatcher rd=request.getRequestDispatcher("checkOut.jsp");
		rd.forward(request, response);
		//out.println("total price"+price);
		}catch(Exception e){
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
