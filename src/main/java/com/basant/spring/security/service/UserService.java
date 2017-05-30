package com.basant.spring.security.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.basant.spring.security.dto.User;

@Service
public class UserService {

	private static List<User> users = new CopyOnWriteArrayList<>();

	static {
		users.add(new User(25312, "Basant", "MW-DEV", 24));
		users.add(new User(36543, "Santosh", "FS-DEV", 27));
		users.add(new User(48093, "Rabindra", "UI-DEV", 28));
		users.add(new User(12341, "Saroj", "FS-DEV", 26));
		users.add(new User(65875, "Shaik", "MW-DEV", 24));
	}

	public List<User> getUsers() {
		return users;
	}

	public String addUser(User user) {
		users.add(user);
		return "user added successfully";
	}

	public String deleteUser(int id) {
		users.removeIf(user -> user.getId() == id);
		return "User Removed";
	}
}
