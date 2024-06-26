package com.hrms.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.hrms.models.Department;

public class DepartmentRepo {

	private DatabaseRepo db;

	public DepartmentRepo() {
		this.db = new DatabaseRepo();
	}

	public int save(Department department) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		if (department.getDepartmentName() != null && !department.getDepartmentName().isEmpty()) {
			map.put("department_name", department.getDepartmentName());
		} else {
			throw new RuntimeException("Department name is a mandatory field");
		}

		if (department.getLocation() != null && !department.getLocation().isEmpty()) {
			map.put("location", department.getLocation());
		}

		if (department.getManagerId() != null) {
			map.put("manager_id", department.getManagerId());
		}

		return db.insertDataIntoTable("departments", map);
	}

	public List<Department> getDeptRecords(int start, int total) {
		List<Department> list = new ArrayList<>();
		try {
			ResultSet rs = db.selectStarFromTablePagination("departments", start, total);
			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("department_id"));
				department.setDepartmentName(rs.getString("department_name"));
				department.setLocation(rs.getString("location"));
				department.setManagerId(rs.getInt("manager_id"));
				list.add(department);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getNoOfRecords() {
		return db.getNoOfRecords("departments", "department_id");
	}

	public int delete(int departmentId) {
		return db.deleteDataFromTable("departments", "department_id='"+departmentId+"'");
	}
}

