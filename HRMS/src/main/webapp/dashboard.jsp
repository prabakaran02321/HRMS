<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HRMS Dashboard</title>
<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/image/hrms.jpg">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 0;
}

header {
	background-color: #2d7d9b;
	color: #fff;
	padding: 10px 0;
	width: 100%;
	position: fixed;
}

.container {
	width: 97%;
	margin: 12px auto;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.logo {
	font-size: 24px;
}

nav ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

nav ul li {
	display: inline-block;
	margin-left: 20px;
}

nav ul li a {
	color: #fff;
	text-decoration: none;
	transition: color 0.3s ease;
}

nav ul li a:hover {
	color: red;
	text-decoration: none;
}

#sidebar {
	width: 200px;
	background: #333;
	color: #fff;
	height: 100vh;
	padding-top: 20px;
	position: fixed;
	left: 0;
	top: 75px;
	transition: transform 0.3s ease;
	transform: translateX(0);
}

#sidebar.active {
	transform: translateX(-200px);
}

#sidebar ul {
	list-style-type: none;
	padding: 0;
}

#sidebar ul li {
	padding: 15px 20px;
}

aside ul li a {
	color: #fff;
	text-decoration: none;
	display: block;
	padding: 10px;
	background-color: #444;
	border-radius: 4px;
	transition: background-color 0.3s ease;
}

.slider-icons {
	display: flex;
	align-items: center;
	cursor: pointer;
}

.slider-icons i {
	color: #fff;
	font-size: 24px;
	margin-right: 20px;
}

main {
	flex: 1;
	padding: 20px;
	margin-left: 200px;
	transition: margin-left 0.3s ease;
}

main.collapsed {
	margin-left: 0;
}
</style>
</head>
<body>
	<header>
		<div class="container">
			<div class="slider-icons">
				<i id="sidebarToggle" class="fas fa-bars"></i>
			</div>
			<div class="logo">HRMS</div>
			<nav>
				<ul>
					<li><a href="<%=request.getContextPath()%>/auth/logOut">Logout</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<aside id="sidebar">
		<ul>
			<li><a href="<%=request.getContextPath()%>/api/employee/view">Employee</a></li>
			<li><a href="<%=request.getContextPath()%>/api/project/view">Project</a></li>
			<li><a href="<%=request.getContextPath()%>/api/assignment/view">Assignment</a></li>
			<li><a href="<%=request.getContextPath()%>/api/training/view">Training</a></li>
		</ul>
	</aside>

	<main>
	<%--<jsp:include page="employee.jsp"/> --%> 
	</main>

	<script>
		document.getElementById('sidebarToggle').addEventListener(
				'click',
				function() {
					document.getElementById('sidebar').classList
							.toggle('active');
					document.getElementById('mainContent').classList
							.toggle('collapsed');
				});
	</script>
</body>
</html>
