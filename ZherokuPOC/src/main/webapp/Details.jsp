<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Farmers | Heroku POC</title>
</head>
<body>
	<center>
		<div style="color: teal; font-size: 30px">Farmers | Heroku POC</div>
		<table border="1">
			<tr>
				<td>Name :</td>
				<td><%=request.getParameter("firstname")%></td>
				<td><%=request.getParameter("lastname")%></td>
			</tr>
			<tr>
				<td>Gender :</td>
				<td><%=request.getParameter("gender")%></td>
			</tr>
			<tr>
				<td>Phone :</td>
				<td><%=request.getParameter("phone")%></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><%=request.getParameter("email")%></td>
			</tr>
			<tr>
				<td>City :</td>
				<td><%=request.getParameter("city")%></td>
			</tr>
		</table>
	</center>
</body>
</html>
