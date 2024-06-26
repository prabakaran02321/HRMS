package com.hrms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hrms.models.Users;
import com.hrms.utils.DataValidation;

public class UsersRepo {

	private DatabaseRepo db;

	public UsersRepo() {
		this.db = new DatabaseRepo();
	}

	public Users findByUsername(String username) {

		ResultSet rs = db.selectStarFromTableWithWhere("user", "user_name ='" + username + "'");

		try {
			if (rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setRoleId(rs.getInt("role_id"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUserRole(String username) {
		ResultSet rs = db
				.selectQuery("SELECT r.role_name FROM user u JOIN roles r ON u.role_id = r.role_id WHERE u.user_name ='"
						+ username + "'");
		try {
			if (rs.next()) {
				return rs.getString("role_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getUserId(String username, String password) {
		ResultSet rs = db.selectQuery(
				"SELECT user_id FROM user WHERE user_name ='" + username + "' AND password='" + password + "'");
		try {
			if (rs.next()) {
				return rs.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getRoleName(int roleId) {
		ResultSet rs = db.selectQuery("SELECT role_name FROM roles WHERE role_id='" + roleId + "'");
		try {
			if (rs.next()) {
				return rs.getString("role_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean authValid(int roleId, String urlPath, String httpMethod) {
		ResultSet rs = db.selectQuery("SELECT role_permission_id FROM role_permissions WHERE role_id='" + roleId
				+ "' AND url='" + urlPath + "' AND method='" + httpMethod + "'");
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean validateUsername(String username) {
		boolean isValid = false;

		ArrayList<String> al = new ArrayList<String>();
		al.add("user_id");

		ResultSet resultSet = db.selectColumnsFromTableWithWhere("user", al, "user_name ='" + username + "'");
		try {
			if (resultSet.next() && resultSet.getInt(1) > 0) {
				isValid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isValid;
	}

	public int save(Users user) {

		Map<String, Object> map = new LinkedHashMap<>();

		if (DataValidation.isValidUsername(user.getUsername())) {
			map.put("user_name", user.getUsername());
		} else {
			throw new RuntimeException("Username should contain both letters and numbers but minimum 5 character");
		}

		if (DataValidation.isValidPassword(user.getPassword())) {
			map.put("password", user.getPassword());
		} else {
			throw new RuntimeException("Password is weak!!!");
		}

		if (DataValidation.isValidRole(user.getRoleId())) {
			map.put("role_id", user.getRoleId());
		} else {
			throw new RuntimeException("Role is invalid");
		}

		return db.insertDataIntoTable("user", map);
	}

}
