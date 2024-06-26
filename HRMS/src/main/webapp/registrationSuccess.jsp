<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Success</title>
<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/image/hrms.jpg">
<style>
body {
	background: rgb(175, 255, 172);
	background: linear-gradient(186deg, rgba(175, 255, 172, 1) 42%,
		rgba(99, 219, 94, 1) 56%, rgba(255, 12, 54, 1) 69%,
		rgba(255, 12, 54, 1) 69%);
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	width: 400px;
	text-align: center;
}

.container h1 {
	margin-bottom: 20px;
}

.container p {
	font-size: 16px;
	margin-bottom: 20px;
}

.container a {
	display: inline-block;
	padding: 10px 20px;
	background-color: #5cb85c;
	color: white;
	text-decoration: none;
	border-radius: 4px;
	transition: background-color 0.3s ease;
}

.container a:hover {
	background-color: #4cae4c;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Registration Successful</h1>
		<p>You have successfully registered in the HRMS application.</p>
		<a href="<%=request.getContextPath()%>/login.jsp">Click here to
			login</a>
	</div>
</body>
</html>
