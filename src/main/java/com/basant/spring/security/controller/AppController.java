package com.basant.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.basant.spring.security.dto.User;
import com.basant.spring.security.service.UserService;

@Controller
public class AppController {
	@Autowired
	private UserService service;

	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}

	@RequestMapping("/foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	@RequestMapping("/getUsers")
	public String getUsers(Model model) {
		List<User> users = service.getUsers();
		model.addAttribute("users", users);
		return "home";
	}

	@RequestMapping(value = "/deleteUser")
	public String deleteUser(@RequestParam("id") int id, Model model) {
		String message = service.deleteUser(id);
		model.addAttribute("message", message);
		model.addAttribute("users", service.getUsers());
		return "home";
	}

	@RequestMapping(value = "/updateUser")
	public String updateUser(@RequestParam("id") int id, Model model) {

		User userById = service.getUsers().stream()
				.filter(u -> u.getId() == id).findAny().get();
		model.addAttribute("userById", userById);
		service.deleteUser(id);
		return "registration";
	}

	@RequestMapping(value = "/addUser")
	public String addUser(@ModelAttribute User user, Model model) {
		String message="updated successfully";
		service.addUser(user);
		model.addAttribute("users", service.getUsers());
		model.addAttribute("message", message);
		return "home";

	}
}
