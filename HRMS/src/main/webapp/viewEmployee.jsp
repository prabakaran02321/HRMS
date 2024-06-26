<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/dashboard.jsp"%>
<%@ page import="com.hrms.models.Employee"%>
<jsp:useBean id="empRepo" class="com.hrms.repository.EmployeeRepo"/>  

<head>
<meta charset="ISO-8859-1">
<title>Employee Profile</title>
<style>
.content {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.employee-profile {
    background-color: #fff;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    width: 100%;
    max-width: 600px;
    text-align: center;
}

.employee-profile h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #07074D;
}

.employee-profile table {
    width: 100%;
    border-collapse: collapse;
}

.employee-profile table, .employee-profile th, .employee-profile td {
    border: 1px solid #ccc;
}

.employee-profile th, .employee-profile td {
    padding: 10px;
    text-align: left;
}

.employee-profile th {
    background-color: #f9f9f9;
    color: #07074D;
}

.formbold-btn {
    width: 40%;
    padding: 10px;
    border: none;
    border-radius: 20px;
    background-color: #4596af;
    color: white;
    cursor: pointer;
    margin-top: 20px;
}

.formbold-btn:hover {
    background-color: #367a8f;
}
</style>
</head>
<body>

<% String empId = request.getParameter("id");
   Employee employee = empRepo.findByEmpId(empId);%>

<div class="content">
    <div class="employee-profile">
        <h2>Employee Profile</h2>
        <table>
            <tr>
                <th>Name</th>
                <td><%= employee.getFirstName() %> <%= employee.getLastName() %></td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td><%= (employee.getDob() != null) ? employee.getDob() : " -" %></td>
            </tr>
            <tr>
                <th>Gender</th>
                <td><%= (employee.getGender() != null) ? employee.getGender() : " -" %></td>
            </tr>
            <tr>
                <th>Address</th>
                <td><%= (employee.getAddress() != null) ? employee.getAddress() : " -" %></td>
            </tr>
            <tr>
                <th>Phone</th>
                <td><%= (employee.getPhone() != null) ? employee.getPhone() : " -" %></td>
            </tr>
            <tr>
                <th>Email</th>
                <td><%= (employee.getEmail() != null) ? employee.getEmail() : " -" %></td>
            </tr>
            <tr>
                <th>Hire Date</th>
                <td><%= (employee.getHireDate() != null) ? employee.getHireDate() : " -" %></td>
            </tr>
            <tr>
                <th>Salary</th>
                <td><%= (employee.getSalary() != null) ? employee.getSalary() : " -" %></td>
            </tr>
            <tr>
                <th>Position</th>
                <td><%= (employee.getPosition() != null) ? employee.getPosition() : " -" %></td>
            </tr>
        </table>
        <button onclick="location.href='<%= request.getContextPath() %>/editEmployee.jsp?id=<%= empId %>';" class="formbold-btn">Edit</button>
    </div>
</div>
</body>
</html>
