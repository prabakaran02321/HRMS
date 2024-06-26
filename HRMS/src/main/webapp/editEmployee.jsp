<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/dashboard.jsp"%>
<%@ page import="com.hrms.models.Employee"%>
<jsp:useBean id="empRepo" class="com.hrms.repository.EmployeeRepo"/>  

<style>

.content {
 width: 43%;
 padding-left: 500px;
 padding-top: 20px;
}

 .formbold-form-input {
    width: 80%;
    padding: 13px 22px;
    border-radius: 5px;
    border: 1px solid #dde3ec;
    background: #ffffff;
    font-weight: 500;
    font-size: 16px;
    color: #536387;
    outline: none;
    resize: none;
  }
  
  .formbold-input-flex {
   	display: flex;
    gap: 20px;
    margin-bottom: 15px;
  }
  
  .formbold-input-flex > div {
    width: 50%;
  }
  
   .formbold-form-input::placeholder,
  select.formbold-form-input,
  .formbold-form-input[type='date']::-webkit-datetime-edit-text,
  .formbold-form-input[type='date']::-webkit-datetime-edit-month-field,
  .formbold-form-input[type='date']::-webkit-datetime-edit-day-field,
  .formbold-form-input[type='date']::-webkit-datetime-edit-year-field {
    color: rgba(83, 99, 135, 0.5);
  }
  
 .formbold-form-input:focus {
    border-color: #6a64f1;
    box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
  }
 .formbold-form-label {
    color: #07074D;
    font-weight: 500;
    font-size: 14px;
    line-height: 24px;
    display: block;
    margin-bottom: 5px;
  }
  
  .formbold-btn {
    text-align: center;
    width: 58%;
    font-size: 16px;
    border-radius: 5px;
    padding: 14px 25px;
    border: none;
    font-weight: 500;
    background-color: #4596af;
    color: white;
    cursor: pointer;
    margin-top: 25px;
    margin-left: 110px;
  }
  .formbold-btn:hover {
    box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
  }
  
  .formbold-form-wrapper {
    margin: 0 auto;
    max-width: 570px;
    width: 100%;
    background: white;
    padding: 40px;
  }
  
    .formbold-main-wrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 48px;
  }
  
  .title {
  display: flex;
    justify-content: center;
    font-size: xx-large;
  }
  
</style>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var form = document.getElementById("editEmployeeForm");
        var changedFieldsInput = document.getElementById("changedFields");

        form.addEventListener("submit", function(event) {
            var changedFields = [];
            var formInputs = form.querySelectorAll("input, select");
            formInputs.forEach(function(input) {
                if (input.value !== input.defaultValue) {
                    changedFields.push(input.name);
                }
            });
            changedFieldsInput.value = changedFields.join(",");
        });
    });
</script>

<% String empId = request.getParameter("id");
   Employee employee = empRepo.findByEmpId(empId);%>

<div class="content" class="formbold-main-wrapper">
<div class="formbold-form-wrapper">
 <h2 class="title"><%=employee.getFirstName()%> <%=employee.getLastName()%></h2>
<form id="editEmployeeForm" action="api/employee/edit?id=<%=empId%>" method="post">
      <div class="formbold-input-flex">
        <div>
    <label for="firstname" class="formbold-form-label">First Name</label>
    <input
        type="text"
        id="firstname"
        name="firstname"
        placeholder="Your first name"
        class="formbold-form-input"
        value="<%= (employee.getFirstName() != null) ? employee.getFirstName() : "" %>"
    />
</div>
<div>
    <label for="lastname" class="formbold-form-label">Last Name</label>
    <input
        type="text"
        id="lastname"
        name="lastname"
        placeholder="Your last name"
        class="formbold-form-input"
        value="<%= (employee.getLastName() != null) ? employee.getLastName() : "" %>"
    />
</div>
</div>
<div class="formbold-input-flex">
<div>
    <label for="dob" class="formbold-form-label">DOB</label>
    <input
        type="text"
        id="dob"
        name="dob"
        placeholder="Date of birth"
        class="formbold-form-input"
        value="<%= (employee.getDob() != null) ? employee.getDob() : "" %>"
    />
</div>
<div>
            <label class="formbold-form-label">Gender</label>

            <select class="formbold-form-input" name="gender" id="gender">
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="others">Others</option>
            </select>
        </div>
      </div>
      <div class="formbold-input-flex">
<div>
    <label for="address" class="formbold-form-label">Address</label>
    <input
        type="text"
        id="address"
        name="address"
        placeholder="Address"
        class="formbold-form-input"
        value="<%= (employee.getAddress() != null) ? employee.getAddress() : "" %>"
    />
</div>
<div>
    <label for="phone" class="formbold-form-label">Phone</label>
    <input
        type="text"
        id="phone"
        name="phone"
        placeholder="Phone number"
        class="formbold-form-input"
        value="<%= (employee.getPhone() != null) ? employee.getPhone() : "" %>"
    />
</div>
</div>
<div class="formbold-input-flex">
<div>
    <label for="email" class="formbold-form-label">Email</label>
    <input
        type="email"
        id="email"
        name="email"
        placeholder="Email address"
        class="formbold-form-input"
        value="<%= (employee.getEmail() != null) ? employee.getEmail() : "" %>"
    />
</div>
<div>
    <label for="hiredate" class="formbold-form-label">Hire Date</label>
    <input
        type="text"
        id="hiredate"
        name="hiredate"
        placeholder="Hire date"
        class="formbold-form-input"
        value="<%= (employee.getHireDate() != null) ? employee.getHireDate() : "" %>"
    />
</div>
</div>
<div class="formbold-input-flex">
<div>
    <label for="salary" class="formbold-form-label">Salary</label>
    <input
        type="text"
        id="salary"
        name="salary"
        placeholder="Salary"
        class="formbold-form-input"
        value="<%= (employee.getSalary() != null) ? employee.getSalary() : "" %>"
    />
</div>
<div>
    <label for="position" class="formbold-form-label">Position</label>
    <input
        type="text"
        id="position"
        name="position"
        placeholder="Position"
        class="formbold-form-input"
        value="<%= (employee.getPosition() != null) ? employee.getPosition() : "" %>"
    />
</div>
</div>
<input type="hidden" id="changedFields" name="changedFields"/>
      <button type="submit" class="formbold-btn">Save</button>

      <button onclick="location.href='<%= request.getContextPath() %>/viewEmployee.jsp?id=<%= empId %>';" class="formbold-btn">Cancel</button>

</form>
</div>
</div>