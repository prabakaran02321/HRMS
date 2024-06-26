<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/image/hrms.jpg">
<style>
body {
    background: rgb(255,172,172);
background: linear-gradient(186deg, rgba(255,172,172,1) 42%, rgba(255,12,54,1) 60%, rgba(219,94,94,1) 73%, rgba(255,12,54,1) 82%);
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.forgot-password-container {
    background-color: #fff;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    width: 400px;
    text-align: left;
}

.forgot-password-container h1 {
    margin-bottom: 20px;
    text-align: center;
}

.forgot-password-container .label {
    display: block;
    margin: 10px 0 5px;
}

.forgot-password-container input[type="email"] {
    width: 100%;
    padding: 10px 5px;
    margin: 5px 0 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.forgot-password-container input[type="submit"] {
    width: 100%;
    padding: 10px;
    margin: 5px 1%;
    border: none;
    border-radius: 4px;
    background-color: #5cb85c;
    color: white;
    cursor: pointer;
}

.forgot-password-container input[type="submit"]:hover {
    background-color: #4cae4c;
}

.forgot-password-container .back-to-login {
    margin-top: 10px;
    text-align: center;
}

.forgot-password-container .back-to-login a {
    color: #007bff;
    text-decoration: none;
}

.forgot-password-container .back-to-login a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>

    <div class="forgot-password-container">
        <h1>Forgot Password</h1>
        <form action="auth/forgotPassword" method="post">
            <label class="label" for="email">Email Address</label> 
            <input type="email" name="email" id="email" placeholder="Enter your email address" required> 

            <input type="submit" value="Reset Password"> 

            <div class="back-to-login">
                <a href="login.jsp">Back to Login</a>
            </div>
        </form>
    </div>

</body>
</html>
