package com.farmers.heroku.poc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		/*
		 * System.out.println("Name : " + request.getParameter("name"));
		 * System.out.println("Gender : " + request.getParameter("gender"));
		 * System.out.println("Email : " + request.getParameter("email"));
		 * System.out.println("Phone : " + request.getParameter("phone"));
		 * System.out.println("City : " + request.getParameter("city"));
		 * 
		 * request.getRequestDispatcher("Details.jsp").forward(request,
		 * response);
		 */
		System.out.println("PostgreSQL Inserstion");

		try {
			Connection connection = getConnection();

			String insertTableSQL = "INSERT INTO \"HerokuTestSample\" (firstname, gender) VALUES" + "(?,?)";

			PreparedStatement preparedStatement = connection
					.prepareStatement(insertTableSQL);
			preparedStatement.setString(1,request.getParameter("name"));
			preparedStatement.setString(2,request.getParameter("gender"));
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			System.out.println("Record is inserted into HerokuTestSample table!");
		} catch (URISyntaxException | SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

	}

	private static Connection getConnection() throws URISyntaxException,
			SQLException {
		/*URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		System.out.println("PostgreSQL username:" + username);
		String password = dbUri.getUserInfo().split(":")[1];
		System.out.println("PostgreSQL password:" + password);
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
				+ dbUri.getPort() + dbUri.getPath();*/
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:postgresql://ec2-54-83-29-15.compute-1.amazonaws.com:5432/dalm5sdeofmi5l?user=otypbvcaocgaqu&password=WWnD4GBvjB7qBNNjTJyN6VfO_F&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		return DriverManager.getConnection(url/*dbUrl, username, password*/);                                                                              
	}

}
