<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>User Register</title>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/hrms.jpg">
<style>
body {
	background: rgb(2, 0, 36);
	background: linear-gradient(186deg, rgba(2, 0, 36, 1) 37%, rgba(248, 38, 6, 1) 56%, rgba(0, 212, 255, 1) 73%);
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.register-container {
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	width: 100%;
	max-width: 400px;
	text-align: left;
}

.register-container h1 {
	margin-bottom: 20px;
	text-align: center;
}

.register-container .label {
	display: block;
	margin: 10px 0 5px;
}

.register-container input[type="text"],
.register-container input[type="password"],
.register-container input[type="email"] {
	width: 100%;
	padding: 10px;
	margin: 5px 0;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 18px;
}

.register-container input[type="submit"],
.register-container input[type="reset"] {
	width: 100%;
	padding: 10px;
	margin: 10px 0;
	border: none;
	border-radius: 4px;
	background-color: #5cb85c;
	color: white;
	cursor: pointer;
}

.register-container input[type="reset"] {
	background-color: #d9534f;
}

.register-container .forgot-password {
	margin-top: 10px;
	text-align: center;
}

.register-container .forgot-password a {
	color: #007bff;
	text-decoration: none;
}

.error-message {
	color: red;
	display: none;
	text-align: left;
	margin: 5px 0 10px;
	aria-live: polite;
}

input[title]:hover::before,
input[title]:hover::after {
	content: attr(title);
	position: absolute;
	background-color: #000;
	color: #fff;
	padding: 5px;
	border-radius: 5px;
	font-size: 12px;
	z-index: 1;
}

input[title]:hover::after {
	top: 100%;
	left: 50%;
	margin-left: -5px;
	border-width: 5px;
	border-style: solid;
	border-color: #000 transparent transparent transparent;
}
</style>

<script>
function showErrorMessage(id, message) {
	const errorElement = document.getElementById(id);
	errorElement.textContent = message;
	errorElement.style.display = "block";
}

function hideErrorMessage(id) {
	document.getElementById(id).style.display = "none";
}

function validateName(name, id) {
	const nameRegex = /^[a-zA-Z]+$/;
	if (!nameRegex.test(name)) {
		showErrorMessage(id, "Name should contain only letters.");
		return false;
	}
	hideErrorMessage(id);
	return true;
}

function validateEmail() {
	const email = document.getElementById("email").value;
	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	if (!emailRegex.test(email)) {
		showErrorMessage("email-error", "Invalid email address.");
		return false;
	}
	hideErrorMessage("email-error");
	return true;
}

function validateUsername() {
	const username = document.getElementById("username").value;
	const userRegex = /^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/;
	if (!userRegex.test(username)) {
		showErrorMessage("user-error", "Invalid username.");
		return false;
	}
	hideErrorMessage("user-error");
	return true;
}

function validatePassword() {
	const password = document.getElementById("password").value;
	const passRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=!])(?=\S+$).{8,}$/;
	if (!passRegex.test(password)) {
		showErrorMessage("pass-error", "Password is weak.");
		return false;
	}
	hideErrorMessage("pass-error");
	return true;
}

function validateConfirmPassword() {
	const password = document.getElementById("password").value;
	const confirmPassword = document.getElementById("cpassword").value;
	if (password !== confirmPassword) {
		showErrorMessage("pass-mismatch", "Passwords do not match.");
		return false;
	}
	hideErrorMessage("pass-mismatch");
	return true;
}

function validateForm() {
	const firstName = document.getElementById("firstname").value;
	const lastName = document.getElementById("lastname").value;
	let isValid = true;

	isValid = validateName(firstName, "fn-error") && isValid;
	isValid = validateName(lastName, "ln-error") && isValid;
	isValid = validateEmail() && isValid;
	isValid = validateUsername() && isValid;
	isValid = validatePassword() && isValid;
	isValid = validateConfirmPassword() && isValid;

	return isValid;
}
</script>

</head>
<body>

<div class="register-container">
	<h1>Register</h1>
	<form action="auth/signUp" method="post" onsubmit="return validateForm()">
		<label for="firstname" class="label">First Name</label>
		<input type="text" name="firstname" id="firstname" placeholder="First Name" title="Enter your first name" required oninput="validateName(this.value, 'fn-error')">
		<div id="fn-error" class="error-message"></div>
		
		<label for="lastname" class="label">Last Name</label>
		<input type="text" name="lastname" id="lastname" placeholder="Last Name" title="Enter your last name" required oninput="validateName(this.value, 'ln-error')">
		<div id="ln-error" class="error-message"></div>
		
		<label for="email" class="label">Email</label>
		<input type="email" name="email" id="email" placeholder="Email" title="Enter your email" required oninput="validateEmail()">
		<div id="email-error" class="error-message"></div>
		
		<label for="username" class="label">Username</label>
		<input type="text" name="username" id="username" placeholder="Username" title="Enter your username" required oninput="validateUsername()">
		<div id="user-error" class="error-message"></div>
		
		<label for="password" class="label">Password</label>
		<input type="password" name="password" id="password" placeholder="Password" title="Enter your password" required oninput="validatePassword()">
		<div id="pass-error" class="error-message"></div>
		
		<label for="cpassword" class="label">Confirm Password</label>
		<input type="password" name="cpassword" id="cpassword" placeholder="Confirm Password" title="Confirm your password" required oninput="validateConfirmPassword()">
		<div id="pass-mismatch" class="error-message"></div>
		
		<input type="submit" value="Sign Up">
	</form>
	
	<div class="forgot-password">
		<label>Already have an account? <a href="login.jsp">Login</a></label>
	</div>
</div>

</body>
</html>
