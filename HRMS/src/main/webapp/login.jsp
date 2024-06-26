<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
<link rel="icon" type="image/x-icon"
    href="<%=request.getContextPath()%>/image/hrms.jpg">

<style>
body {
    font-family: Arial, sans-serif;
    background-image: url('<%=request.getContextPath()%>/image/background/hrms-background.jpg');
    background-size: cover;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.login-container {
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    border: 1px solid #ccc;
    border-color: white;
    width: 400px;
    height: 355px;
    text-align: left;
    position: absolute;
    top: 50%;
    left: 150px;
    transform: translateY(-50%);
}

.login-container h1 {
    text-align: center;
    color: white;
    font-size: 24px;
}

.login-container .formbold-form-input {
    width: 89%;
    padding: 13px 22px;
    border-radius: 20px;
    border: 1px solid #dde3ec;
    background: #ffffff;
    font-weight: 500;
    font-size: 16px;
    color: #536387;
    outline: none;
    resize: none;
    margin-bottom: 15px;
}

.login-container .formbold-form-input::placeholder {
    color: rgba(83, 99, 135, 0.5);
}

.login-container .formbold-form-input:focus {
    border-color: #6a64f1;
    box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
}

.login-container .formbold-btn {
    text-align: center;
    width: 100%;
    font-size: 16px;
    border-radius: 20px;
    padding: 14px 25px;
    border: none;
    font-weight: 500;
    background-color: #4596af;
    color: white;
    cursor: pointer;
    margin-top: 20px;
    margin-bottom: 20px;
}

.login-container .formbold-btn:hover {
    box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
}

.login-container .remember-forgot-container {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    margin-top: 15px;
    color: white;
}

.login-container .forgot-password a {
    color: #007bff;
    text-decoration: none;
}

.sign-up {
    display: flex;
    justify-content: center;
    color: white;
}

.sign-up a {
    color: #007bff;
    text-decoration: none;
}

.error-message {
    color: #fe0500;
    font-size: 15px;
    font-weight: bold;
    margin-top: 20px;
    text-align: center;
}
</style>

<script>
    document
        .addEventListener(
            'DOMContentLoaded',
            function() {
                var inputs = document
                    .querySelectorAll('.login-container input[type="text"], .login-container input[type="password"]');
                inputs.forEach(function(input) {
                    input.addEventListener('input', function() {
                        var errorMessage = document.querySelector('.error-message');
                        if (errorMessage) {
                            errorMessage.style.display = 'none';
                        }
                    });
                });
            });
</script>

</head>
<body>

    <div class="login-container">
        <h1>Login</h1>
        <form action="auth/logIn" method="post">
            <input type="text" name="username" id="username"
                placeholder="Username" title="Enter your username" required class="formbold-form-input">
            <input type="password" name="password" id="password"
                placeholder="Password" title="Enter your password" required class="formbold-form-input">

            <div class="remember-forgot-container">
                <div class="remember-me">
                    <input type="checkbox" name="remember" id="remember"> <label for="remember">Remember Me</label>
                </div>
                <div class="forgot-password">
                    <a href="forgotPassword.jsp">Forgot Password?</a>
                </div>
            </div>

            <button type="submit" class="formbold-btn">Login</button>

            <div class="sign-up">
                <label for="username">Don't have an account? <a href="register.jsp">SignUp</a></label>
            </div>

        </form>

        <% String responseMessage = (String) session.getAttribute("responseMessage");
        if (responseMessage != null && !responseMessage.isEmpty()) { %>
        <div class="error-message">
            <%=responseMessage%>
        </div>
        <% session.removeAttribute("responseMessage"); } %>

    </div>

</body>
</html>
