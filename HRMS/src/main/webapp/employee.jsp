<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/dashboard.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hrms.models.Employee"%>
<%@ page import="java.util.List"%>

<style>
.content {
	width: 60%;
	display: flex;
	justify-content: center;
	margin-top: 50px;
}

#mainContent {
	margin-left: 200px;
	padding: 20px;
	transition: margin-left 0.3s ease;
}

.table {
	width: 100%;
	border-collapse: collapse;
}

.table td {
	padding: 15px;
	text-align: left;
	border-bottom: 1px solid #ddd;
	cursor: pointer;
}

.table tbody tr:hover {
	color: blue;
	text-decoration: none;
}

.table th {
	background-color: #f2f2f2;
	padding: 15px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.table-striped tbody tr:nth-child(even) {
	background-color: #f2f2f2;
}

.content h2 {
	margin-bottom: 20px;
	text-align: center;
	font-size: 24px;
	color: #333;
}

.pagination {
    display: flex;
    justify-content: center;
    padding: 10px 0;
}

.page-item {
    margin: 0 5px;
}

.page-item a {
    text-decoration: none;
    padding: 5px 10px;
    border: 1px solid #ddd;
    color: #007bff;
    border-radius: 5px;
}

.page-item.active a {
    background-color: #007bff;
    color: white;
    border: 1px solid #007bff;
}

#page ul li a:hover {
	color: black;
	text-decoration: none;
}
</style>

<div class="content">
	<h2>Employee Records</h2>
</div>
<div id="mainContent">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>S.no</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>DOB</th>
				<th>Gender</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Hire Date</th>
				<th>Salary</th>
				<th>Position</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
			int recordsPerpage = (int) request.getAttribute("recordsPerPage");
			int currentP = (int) request.getAttribute("currentPage");
			int serialNo = (currentP-1)*recordsPerpage+1;
			String role = (String) session.getAttribute("role");
			for (Employee  employee : employeeList) {
				if(role.equals("admin") || role.equals("manager") || role.equals("human resources")) {
			%>
			<tr onclick="location.href='<%= request.getContextPath() %>/viewEmployee.jsp?id=<%= employee.getId() %>';">
				<td><%=serialNo++%></td>
				<td><%= (employee.getFirstName() != null) ? employee.getFirstName() : "-" %></td>
                <td><%= (employee.getLastName() != null) ? employee.getLastName() : "-" %></td>
                <td><%= (employee.getDob() != null) ? employee.getDob() : "-" %></td>
                <td><%= (employee.getGender() != null) ? employee.getGender() : "-" %></td>
                <td><%= (employee.getAddress() != null) ? employee.getAddress() : "-" %></td>
                <td><%= (employee.getPhone() != null) ? employee.getPhone() : "-" %></td>
                <td><%= (employee.getEmail() != null) ? employee.getEmail() : "-" %></td>
                <td><%= (employee.getHireDate() != null) ? employee.getHireDate() : "-" %></td>
                <td><%= (employee.getSalary() != null) ? employee.getSalary() : "-" %></td>
                <td><%= (employee.getPosition() != null) ? employee.getPosition() : "-" %></td>
			</tr>
			<%
				} else {
					
			%>
				<tr>
				<td><%=serialNo++%></td>
				<td><%= (employee.getFirstName() != null) ? employee.getFirstName() : "-" %></td>
                <td><%= (employee.getLastName() != null) ? employee.getLastName() : "-" %></td>
                <td><%= (employee.getDob() != null) ? employee.getDob() : "-" %></td>
                <td><%= (employee.getGender() != null) ? employee.getGender() : "-" %></td>
                <td><%= (employee.getAddress() != null) ? employee.getAddress() : "-" %></td>
                <td><%= (employee.getPhone() != null) ? employee.getPhone() : "-" %></td>
                <td><%= (employee.getEmail() != null) ? employee.getEmail() : "-" %></td>
                <td><%= (employee.getHireDate() != null) ? employee.getHireDate() : "-" %></td>
                <td><%= (employee.getSalary() != null) ? employee.getSalary() : "-" %></td>
                <td><%= (employee.getPosition() != null) ? employee.getPosition() : "-" %></td>
			</tr>
			<% 
				}
			}
			%>
		</tbody>
	</table>

	<nav id="page" aria-label="Page navigation example">
        <ul class="pagination">
            <%
            int noOfPages = (request.getAttribute("noOfPages") != null) ? (int) request.getAttribute("noOfPages") : 1;
            int currentPage = (request.getAttribute("currentPage") != null) ? (int) request.getAttribute("currentPage") : 1;
            String contextPath = request.getContextPath();
            for (int i = 1; i <= noOfPages; i++) {
            %>
            <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                <a class="page-link" href="<%= contextPath %>/api/employee/view?page=<%= i %>"><%= i %></a>
            </li>
            <%
            }
            %>
        </ul>
    </nav>
</div>