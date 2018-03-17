package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChooseCategoryByCust
 */
@WebServlet("/chooseCategoryByCust.do")
public class ChooseCategoryByCust extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseCategoryByCust() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession hs=request.getSession();
		Set<String> vctgry=new HashSet<String>();

		try {
			Connection con=DataConnection.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select prod_ctgry from admin_product_sale where prod_amt>0");
			while(rs.next()){
				String s=rs.getString(1);
				vctgry.add(s);
			}
			
			hs.setAttribute("admin_ctgry", vctgry);
			RequestDispatcher rd=request.getRequestDispatcher("chooseCategoryByCust.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
