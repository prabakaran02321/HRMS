package com.hrms.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.hrms.models.Attendance;

public class AttendanceRepo {

	private DatabaseRepo db;

	public AttendanceRepo() {
		this.db = new DatabaseRepo();
	}

	public int save(Attendance attendance) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		if (attendance.getEmployeeId() != null) {
			map.put("employee_id", attendance.getEmployeeId());
		} else {
			throw new RuntimeException("Employee ID is a mandatory field");
		}

		if (attendance.getDate() != null && !attendance.getDate().isEmpty()) {
			map.put("date", attendance.getDate());
		}

		if (attendance.getStatus() != null && !attendance.getStatus().isEmpty()) {
			map.put("status", attendance.getStatus());
		}

		if (attendance.getNotes() != null && !attendance.getNotes().isEmpty()) {
			map.put("notes", attendance.getNotes());
		}

		return db.insertDataIntoTable("attendance", map);
	}

	public List<Attendance> getAttendanceRecords(int start, int total) {
		List<Attendance> list = new ArrayList<>();
		try {
			ResultSet rs = db.selectStarFromTablePagination("attendance", start, total);
			while (rs.next()) {
				Attendance attendance = new Attendance();
				attendance.setId(rs.getInt("attendance_id"));
				attendance.setEmployeeId(rs.getInt("employee_id"));
				attendance.setDate(rs.getString("date"));
				attendance.setStatus(rs.getString("status"));
				attendance.setNotes(rs.getString("notes"));
				list.add(attendance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getNoOfRecords() {
		return db.getNoOfRecords("attendance", "attendance_id");
	}

	public int delete(int attendanceId) {
		return db.deleteDataFromTable("attendance", "attendance_id='" + attendanceId + "'");
	}
}
