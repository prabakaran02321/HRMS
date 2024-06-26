package com.hrms.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.hrms.models.TrainingSession;

public class TrainingSessionRepo {

	private DatabaseRepo db;

	public TrainingSessionRepo() {
		this.db = new DatabaseRepo();
	}

	public int save(TrainingSession session) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();

		if (session.getTrainingName() != null && !session.getTrainingName().isEmpty()) {
			map.put("training_name", session.getTrainingName());
		} else {
			throw new RuntimeException("Training name is a mandatory field");
		}

		if (session.getDescription() != null && !session.getDescription().isEmpty()) {
			map.put("description", session.getDescription());
		}

		if (session.getTrainerId() != null) {
			map.put("trainer_id", session.getTrainerId());
		}

		if (session.getDate() != null && !session.getDate().isEmpty()) {
			map.put("date", session.getDate());
		}

		if (session.getLocation() != null && !session.getLocation().isEmpty()) {
			map.put("location", session.getLocation());
		}

		return db.insertDataIntoTable("training_sessions", map);
	}

	public List<TrainingSession> getTrainingSessions(int start, int total) {
		List<TrainingSession> list = new ArrayList<>();
		try {
			ResultSet rs = db.selectStarFromTablePagination("training_sessions", start, total);
			while (rs.next()) {
				TrainingSession session = new TrainingSession();
				session.setTrainingId(rs.getInt("training_id"));
				session.setTrainingName(rs.getString("training_name"));
				session.setDescription(rs.getString("description"));
				session.setTrainerId(rs.getInt("trainer_id"));
				session.setDate(rs.getString("date"));
				session.setLocation(rs.getString("location"));
				list.add(session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getNoOfRecords() {
		return db.getNoOfRecords("training_sessions", "training_id");
	}

	public int delete(int trainingId) {
		return db.deleteDataFromTable("training_sessions", "training_id='" + trainingId + "'");
	}
}
