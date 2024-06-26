package com.hrms.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.hrms.models.ProjectAssignment;

public class ProjectAssignmentRepo {

	private DatabaseRepo db;

	public ProjectAssignmentRepo() {
		this.db = new DatabaseRepo();
	}

	public int save(ProjectAssignment projectAssignment) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		if (projectAssignment.getProjectId() != null) {
			map.put("project_id", projectAssignment.getProjectId());
		} else {
			throw new RuntimeException("Project ID is a mandatory field");
		}

		if (projectAssignment.getEmployeeId() != null) {
			map.put("employee_id", projectAssignment.getEmployeeId());
		} else {
			throw new RuntimeException("Employee ID is a mandatory field");
		}

		if (projectAssignment.getRole() != null && !projectAssignment.getRole().isEmpty()) {
			map.put("role", projectAssignment.getRole());
		}

		if (projectAssignment.getStartDate() != null && !projectAssignment.getStartDate().isEmpty()) {
			map.put("start_date", projectAssignment.getStartDate());
		}

		if (projectAssignment.getEndDate() != null && !projectAssignment.getEndDate().isEmpty()) {
			map.put("end_date", projectAssignment.getEndDate());
		}

		return db.insertDataIntoTable("project_assignments", map);
	}

	public List<ProjectAssignment> getProjectAssignmentRecords(int start, int total) {
		List<ProjectAssignment> list = new ArrayList<>();
		try {
			ResultSet rs = db.selectStarFromTablePagination("project_assignments", start, total);
			while (rs.next()) {
				ProjectAssignment projectAssignment = new ProjectAssignment();
				projectAssignment.setId(rs.getInt("assignment_id"));
				projectAssignment.setProjectId(rs.getInt("project_id"));
				projectAssignment.setEmployeeId(rs.getInt("employee_id"));
				projectAssignment.setRole(rs.getString("role"));
				projectAssignment.setStartDate(rs.getString("start_date"));
				projectAssignment.setEndDate(rs.getString("end_date"));
				list.add(projectAssignment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getNoOfRecords() {
		return db.getNoOfRecords("project_assignments", "assignment_id");
	}

	public int delete(int assignmentId) {
		return db.deleteDataFromTable("project_assignments", "assignment_id='" + assignmentId + "'");
	}
}
