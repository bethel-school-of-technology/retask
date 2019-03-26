package com.retask.game.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retask.game.model.User;
import com.retask.game.services.TaskService;
import com.retask.game.services.UserService;

@RestController
public class ReTaskRestAPIs {

	@Autowired
	UserService userService = new UserService();
	@Autowired
	TaskService taskService = new TaskService();

	@GetMapping("/api/gettasksbyusername")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Object> userAccess(Model model, Principal principal) {

		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
			
		return ResponseEntity.ok(taskService.getTasksbyUsername(user.getUsername()));
	}


//	@GetMapping("/api/test/pm")
//	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
//	public String projectManagementAccess() {
//		return ">>> Board Management Project";
//	}
//
//	@GetMapping("/api/test/admin")
//	@PreAuthorize("hasRole('ADMIN')")
//	public String adminAccess() {
//		return ">>> Admin Contents";
//	}
}