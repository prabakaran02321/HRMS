package com.hrms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hrms.models.Employee;
import com.hrms.utils.DataValidation;

public class EmployeeRepo {

	private DatabaseRepo db;

	public EmployeeRepo() {
		this.db = new DatabaseRepo();
	}

	public int save(Employee employee) {

		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		if (DataValidation.isValidName(employee.getFirstName())) {
			map.put("first_name", employee.getFirstName());
		} else {
			throw new RuntimeException("First name should contain only letters");
		}

		if (DataValidation.isValidName(employee.getLastName())) {
			map.put("last_name", employee.getLastName());
		} else {
			throw new RuntimeException("Last name should contain only letters");
		}

		if (employee.getDob() != null && !employee.getDob().isEmpty()) {
			if (DataValidation.isValidDate(employee.getDob())) {
				map.put("dob", employee.getDob());
			} else {
				throw new RuntimeException("Dob format wrong");
			}
		}

		if (employee.getGender() != null && !employee.getGender().isEmpty()) {
			map.put("gender", employee.getGender());
		}

		if (employee.getAddress() != null && !employee.getAddress().isEmpty()) {
			map.put("address", employee.getAddress());
		}

		if (employee.getPhone() != null && !employee.getPhone().isEmpty()) {
			map.put("phone", employee.getPhone());
		}

		if (DataValidation.isValidEmail(employee.getEmail())) {
			map.put("email", employee.getEmail());
		} else {
			throw new RuntimeException("Invalid Email!!!");
		}

		if (employee.getPosition() != null && !employee.getPosition().isEmpty()) {
			map.put("position", employee.getPosition());
		}

		if (employee.getHireDate() != null && !employee.getHireDate().isEmpty()) {
			map.put("hire_date", employee.getHireDate());
		}

		if (employee.getSalary() != null && !employee.getSalary().isEmpty()) {
			map.put("salary", employee.getSalary());
		}

		if (employee.getUserId() != 0) {
			map.put("user_id", employee.getUserId());
		}

		return db.insertDataIntoTable("employees", map);
	}

	public List<Employee> getEmpRecords(int start, int total) {
		List<Employee> list = new ArrayList<Employee>();
		try {
			ResultSet rs = db.selectStarFromTablePagination("employees", start, total);
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("employee_id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setDob(rs.getString("dob"));
				employee.setGender(rs.getString("gender"));
				employee.setAddress(rs.getString("address"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setHireDate(rs.getString("hire_date"));
				employee.setSalary(rs.getString("salary"));
				employee.setPosition(rs.getString("position"));
				list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getNoOfRecords() {
		return db.getNoOfRecords("employees", "employee_id");
	}

	public Employee findByEmpId(String empId) {

		ResultSet rs = db.selectStarFromTableWithWhere("employees", "employee_id ='" + empId + "'");

		try {
			if (rs.next()) {
				Employee employee = new Employee();
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setDob(rs.getString("dob"));
				employee.setGender(rs.getString("gender"));
				employee.setAddress(rs.getString("address"));
				employee.setPhone(rs.getString("phone"));
				employee.setEmail(rs.getString("email"));
				employee.setHireDate(rs.getString("hire_date"));
				employee.setSalary(rs.getString("salary"));
				employee.setPosition(rs.getString("position"));
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getEmpId(String email) {
		ArrayList<String> al = new ArrayList<>();
		al.add("employee_id");
		ResultSet resultSet = db.selectColumnsFromTableWithWhere("employees", al, "email='" + email + "'");
		try {
			if (resultSet.next() && resultSet.getInt(1) > 0) {
				return resultSet.getInt("employee_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int updateById(String empId, Map<String, Object> map) {
		return db.updateDataToTable("employees", map, "employee_id='" + empId + "'");
	}

}
