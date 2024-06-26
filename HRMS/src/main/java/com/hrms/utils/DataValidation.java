package com.hrms.utils;

import java.util.regex.Pattern;

public class DataValidation {

	public static boolean isValidPassword(String password) {
		if (password != null && !password.isEmpty()) {
			Pattern passwordPattern = Pattern
					.compile("^(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,}$");
			return passwordPattern.matcher(password).matches();
		} else {
			return false;
		}
	}

	public static boolean isValidName(String name) {
		if (name != null && !name.isEmpty()) {
			Pattern namePattern = Pattern.compile("^[a-zA-Z]+$");
			return namePattern.matcher(name).matches();
		} else {
			return false;
		}
	}
	
	public static boolean isValidRole(int role) {
	    if (role > 0) {
	        String roleString = String.valueOf(role);
	        Pattern rolePattern = Pattern.compile("^[1-5]$");
	        return rolePattern.matcher(roleString).matches();
	    } else {
	        return false;
	    }
	}

	public static boolean isValidEmail(String email) {
		if (email != null && !email.isEmpty()) {
			Pattern emailPattern = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
			return emailPattern.matcher(email).matches();
		} else {
			return false;
		}
	}

	public static boolean isValidUsername(String username) {
		if (username != null && !username.isEmpty()) {
			Pattern usernamePattern = Pattern.compile("^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
			return usernamePattern.matcher(username).matches();
		} else {
			return false;
		}
	}

	public static boolean isValidDate(String date) {
		if (date != null && !date.isEmpty()) {
			Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
			return datePattern.matcher(date).matches();
		} else {
			return false;
		}
	}
}
