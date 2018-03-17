package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckOutCustomer
 */
@WebServlet("/checkOutCustomer.do")
public class CheckOutCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int price=0;
		PrintWriter out=response.getWriter();
		HttpSession hs=request.getSession();
		try{
		List<AdminProduct> stk=(List<AdminProduct>)hs.getAttribute("cart");
		//String admin_id=(String) hs.getAttribute("logname");
		
		for(AdminProduct p:stk){
			price = price + p.getOrd_amt()*p.getProd_price();
			
			/*out.println(p.getVprod_nm());
			out.println(p.getVprod_price());
			out.println(p.getVprod_desc());
			out.println(p.getVprod_id());
			out.println(p.getOrd_amt());
			out.println();*/
			
		}
		request.setAttribute("totalPrice", price);
		RequestDispatcher rd=request.getRequestDispatcher("checkOutCustomer.jsp");
		rd.forward(request, response);
		System.out.println("total price"+price);
		}catch(Exception e){
			
		}
	}

	}


