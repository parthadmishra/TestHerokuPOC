package com.farmers.heroku.util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

public class TestUtils {

	public static Connection getConnection() throws URISyntaxException,
			SQLException {
		/*
		 * URI dbUri = new URI(System.getenv("DATABASE_URL"));
		 * 
		 * String username = dbUri.getUserInfo().split(":")[0];
		 * System.out.println("PostgreSQL username:" + username); String
		 * password = dbUri.getUserInfo().split(":")[1];
		 * System.out.println("PostgreSQL password:" + password); String dbUrl =
		 * "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() +
		 * dbUri.getPath();
		 */
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("getConnection:IOException");
			e.printStackTrace();
		}
		String url = "jdbc:postgresql://ec2-54-83-29-15.compute-1.amazonaws.com:5432/dalm5sdeofmi5l?user=otypbvcaocgaqu&password=WWnD4GBvjB7qBNNjTJyN6VfO_F&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		return DriverManager.getConnection(url/* dbUrl, username, password */);
	}

	public static String checkCustomer(String firstname, String lastname,
			String gender, String phone, String email, String location) {
		System.out.println("Calling URL Service");
		URL url = null;
		try {
			url = new URL(
					"http://macmini4.farmersinsurance.com:3000/registerService/register");
			HttpURLConnection hurl = (HttpURLConnection) url.openConnection();
			hurl.setRequestMethod("POST");
			hurl.setDoOutput(true);
			hurl.setRequestProperty("Content-Type", "application/json");
			hurl.setRequestProperty("Accept", "application/json");
			String payload = prepareRequest(firstname, lastname, gender, phone,
					email, location);
			OutputStreamWriter osw = new OutputStreamWriter(
					hurl.getOutputStream());
			osw.write(payload);
			osw.flush();
			osw.close();
		} catch (MalformedURLException e) {
			System.out.println("checkCustomer:MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("checkCustomer:IOException");
			e.printStackTrace();
		}

		return null;
	}

	private static String prepareRequest(String firstname, String lastname,
			String gender, String phone, String email, String location) {

		StringBuffer payloadBuffer = new StringBuffer();
		payloadBuffer.append("{");
		payloadBuffer.append("\"firstName\"");
		payloadBuffer.append(":");
		payloadBuffer.append(firstname);
		payloadBuffer.append(",");
		payloadBuffer.append("\"lastName\"");
		payloadBuffer.append(":");
		payloadBuffer.append(lastname);
		payloadBuffer.append(",");
		payloadBuffer.append("\"gender\"");
		payloadBuffer.append(":");
		payloadBuffer.append(gender);
		payloadBuffer.append(",");
		payloadBuffer.append("\"phone\"");
		payloadBuffer.append(":");
		payloadBuffer.append(phone);
		payloadBuffer.append(",");
		payloadBuffer.append("\"eMail\"");
		payloadBuffer.append(":");
		payloadBuffer.append(email);
		payloadBuffer.append(",");
		payloadBuffer.append("\"workLocation\"");
		payloadBuffer.append(":");
		payloadBuffer.append(location);
		payloadBuffer.append("}");

		String payload = payloadBuffer.toString();
		System.out.println("Prepared Request: " + payload);
		return payload;

	}

	public static void populateTable(HttpServletResponse response, String name,
			String gender, String phone, String email, String location)
			throws IOException {
		PrintWriter wr = response.getWriter();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		int i = 0;
		try {
			/*
			 * System.out.println("Name : " + request.getParameter("name"));
			 * System.out.println("Gender : " + request.getParameter("gender"));
			 * System.out.println("Email : " + request.getParameter("email"));
			 * System.out.println("Phone : " + request.getParameter("phone"));
			 * System.out.println("Work Location : " +
			 * request.getParameter("location"));
			 * 
			 * request.getRequestDispatcher("Details.jsp").forward(request,
			 * response);
			 */

			System.out.println("PostgreSQL Inserstion");
			connection = TestUtils.getConnection();
			String insertTableSQL = "INSERT INTO \"HerokuTestSample\" (fullname,gender,phonenumber,email,location) VALUES"
					+ "(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, gender);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, location);

			i = preparedStatement.executeUpdate();
			System.out
					.println("Record is inserted into HerokuTestSample table!");

		} catch (URISyntaxException | SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			wr.println("Failed to uplod into database");
		} finally {
			try {
				if (null != preparedStatement) {
					preparedStatement.close();
				}
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				wr.println("Failed to uplod into database");
			}
			if (i == 1) {
				wr.println("Successfully uploded into database");
			}
		}

	}
}
