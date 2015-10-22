package com.farmers.heroku.poc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmers.heroku.util.TestUtils;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String name = firstname.concat(lastname);
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String location = request.getParameter("location");

		String responseCode = TestUtils.checkCustomer(firstname, lastname,
				gender, phone, email, location);
		System.out.println("Response Code:" + responseCode);

		if ("400".equalsIgnoreCase(responseCode)) {
			System.out.println("Existing Customer hence redirecting");
			response.sendRedirect("ExistingCustomer.jsp");
		} else {
			System.out
					.println("Not an existing customer hence populating in DB");
			TestUtils.populateTable(response, name, gender, phone, email,
					location);
		}
	}
}
