package com.hrms.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.hrms.models.Project;

public class ProjectRepo {

	private DatabaseRepo db;

	public ProjectRepo() {
		this.db = new DatabaseRepo();
	}

	public int save(Project project) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		if (project.getProjectName() != null && !project.getProjectName().isEmpty()) {
			map.put("project_name", project.getProjectName());
		} else {
			throw new RuntimeException("Project name is a mandatory field");
		}

		if (project.getStartDate() != null && !project.getStartDate().isEmpty()) {
			map.put("start_date", project.getStartDate());
		}

		if (project.getEndDate() != null && !project.getEndDate().isEmpty()) {
			map.put("end_date", project.getEndDate());
		}

		if (project.getBudget() != null) {
			map.put("budget", project.getBudget());
		}

		if (project.getDepartmentId() != null) {
			map.put("department_id", project.getDepartmentId());
		}

		return db.insertDataIntoTable("projects", map);
	}

	public List<Project> getProjectRecords(int start, int total) {
		List<Project> list = new ArrayList<>();
		try {
			ResultSet rs = db.selectStarFromTablePagination("projects", start, total);
			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("project_id"));
				project.setProjectName(rs.getString("project_name"));
				project.setStartDate(rs.getString("start_date"));
				project.setEndDate(rs.getString("end_date"));
				project.setBudget(rs.getBigDecimal("budget"));
				project.setDepartmentId(rs.getInt("department_id"));
				list.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getNoOfRecords() {
		return db.getNoOfRecords("projects", "project_id");
	}

	public int delete(int projectId) {
		return db.deleteDataFromTable("projects", "project_id='"+projectId+"'");
	}
}
