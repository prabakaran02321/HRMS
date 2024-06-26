package com.hrms.repository;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

public class LogRepo {

	private DatabaseRepo db;

	public LogRepo() {
		this.db = new DatabaseRepo();
	}

	public void logToDatabase(Timestamp requestTime, String clientIp, String method, String requestUri,
			Timestamp responseTime, int statusCode) {

		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("request_uri", requestUri);
		map.put("http_method", method);
		map.put("client_ip", clientIp);
		map.put("request_time", requestTime);
		map.put("response_time", responseTime);
		map.put("status_code", statusCode);

		db.insertDataIntoTable("api_logs", map);

	}

}
