<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to HRMS</title>
    <style>
        body {
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
            text-align: center;
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            margin-bottom: 10px;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to HRMS</h1>
        <%
            String username = (String) request.getSession().getAttribute("username");
            String role = (String) request.getSession().getAttribute("role");
        
            if (username != null && !username.isEmpty() && role != null && !role.isEmpty()) {
        %>
                <p>Welcome, <%= username %>!</p>
                <p>Your role: <%= role %></p>
        <%
            } else {
                // Redirect to login page if user is not authenticated
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        %>
    </div>
</body>
</html>
