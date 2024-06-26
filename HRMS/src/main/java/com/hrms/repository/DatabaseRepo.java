package com.hrms.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.hrms.utils.ConfigReader;

public class DatabaseRepo {

//	public  Connection getConnection() throws SQLException {
//		return DatabaseConfig.getDataSource().getConnection();
//	}
	
	private static final String URL = ConfigReader.getProperty("DB_URL");
	private static final String USER_NAME = ConfigReader.getProperty("DB_USERNAME");
	private static final String PASSWORD = ConfigReader.getProperty("DB_PASSWORD");

	public static Connection getConnection() {
		try {
			Class.forName(ConfigReader.getProperty("DB_CLASS"));
			return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static Connection getConnection() throws NamingException, SQLException {
//		Connection con = null;
//		Context initContext = new InitialContext();
//		Context envContext = (Context) initContext.lookup("java:/comp/env");
//		DataSource ds = (DataSource) envContext.lookup("jdbc/empManageDB");
//		con = ds.getConnection();
//		return con;
//	}

	private void closeResources(Connection con, PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insertDataIntoTable(String tableName, Map<String, Object> map) {
		Connection con = null;
		PreparedStatement ps = null;
		int status = 0;
		try {
			Set<Map.Entry<String, Object>> entries = map.entrySet();

			String keys = "";
			String values = "";
			for (Map.Entry<String, Object> entry : entries) {
				keys += entry.getKey() + ",";
				values += "'" + entry.getValue() + "',";
			}

			keys = keys.substring(0, keys.length() - 1);
			values = values.substring(0, values.length() - 1);

			String query = "INSERT INTO " + tableName + " (" + keys + ") VALUES (" + values + ")";
			con = getConnection();
			ps = con.prepareStatement(query);
			status = ps.executeUpdate();
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, ps);
		}
		return status;
	}

	public int insertFileIntoTable(String tableName, String fileColumnName, InputStream fileContent) {
		Connection con = null;
		PreparedStatement ps = null;
		int status = 0;
		try {

			String query = "INSERT INTO " + tableName + " (" + fileColumnName + ") VALUES (" + fileContent + ")";
			con = getConnection();
			ps = con.prepareStatement(query);
			status = ps.executeUpdate();
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, ps);
		}
		return status;

	}

	public int deleteDataFromTable(String tableName, String condition) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "DELETE FROM " + tableName + " WHERE " + condition;
			con = getConnection();
			ps = con.prepareStatement(query);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, ps);
		}
		return 0;
	}

	public int updateDataToTable(String tableName, Map<String, Object> map, String condition) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Set<Map.Entry<String, Object>> entries = map.entrySet();
			String setClause = "";
			for (Map.Entry<String, Object> entry : entries) {
				setClause += entry.getKey() + "='" + entry.getValue() + "',";
			}

			setClause = setClause.substring(0, setClause.length() - 1);
			String query = "UPDATE " + tableName + " SET " + setClause + " WHERE " + condition;
			con = getConnection();
			ps = con.prepareStatement(query);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, ps);
		}
		return 0;
	}

	public ResultSet selectStarFromTable(String tableName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM " + tableName;
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeResources(con, ps);
		}
		return rs;
	}
	
	public ResultSet selectQuery(String query) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeResources(con, ps);
		}
		return rs;
	}

	public ResultSet selectStarFromTablePagination(String tableName, int start, int total) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM " + tableName + " LIMIT " + start + "," + total;
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeResources(con, ps);
		}
		return rs;
	}

	public int getNoOfRecords(String tableName, String columnName) {
		int noOfRecords = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT COUNT(" + columnName + ") FROM " + tableName;
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				noOfRecords = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	public ResultSet selectStarFromTableWithWhere(String tableName, String condition) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM " + tableName + " WHERE " + condition;
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeResources(con, ps);
		}
		return rs;
	}

	public ResultSet selectStarFromTableWithWhereWildCard(String tableName, String columName, String wildCards) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM " + tableName + " WHERE " + columName + " LIKE ?";
			con = getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, wildCards);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, ps);
		}
		return rs;
	}

	public ResultSet selectColumnsFromTable(String tableName, ArrayList<String> cols) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String columns = String.join(",", cols);
			String query = "SELECT " + columns + " FROM " + tableName;
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeResources(con, ps);
		}
		return rs;
	}

	public ResultSet selectColumnsFromTableWithWhere(String tableName, ArrayList<String> cols, String condition) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String columns = String.join(",", cols);
			String query = "SELECT " + columns + " FROM " + tableName + " WHERE " + condition;
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeResources(con, ps);
		}
		return rs;
	}

	public ResultSet selectColumnsFromTableWithWhereLimit(String tableName, ArrayList<String> cols, String condition,
			int limitCount) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String columns = String.join(",", cols);
			String query = "SELECT " + columns + " FROM " + tableName + " WHERE " + condition + " LIMIT " + limitCount;
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeResources(con, ps);
		}
		return rs;
	}

	public ResultSet selectDistinctColumnsFromTable(String tableName, ArrayList<String> cols) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String columns = String.join(",", cols);
			String query = "SELECT DISTINCT " + columns + " FROM " + tableName;
			con = getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			closeResources(con, ps);
		}
		return rs;
	}

	public void printSelectQuery(ResultSet rs) {
		try {
			int columnCount = rs.getMetaData().getColumnCount();
			ArrayList<String> columns = new ArrayList<>();
			for (int i = 1; i <= columnCount; i++) {
				columns.add(rs.getMetaData().getColumnName(i));
			}

			while (rs.next()) {
				for (String str : columns) {
					System.out.println("\t" + str + ": " + rs.getString(str));
				}
				System.out.println("--------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
