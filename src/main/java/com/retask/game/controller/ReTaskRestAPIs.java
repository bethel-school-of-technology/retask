package com.retask.game.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retask.game.message.response.RetaskStatusResponse;
import com.retask.game.model.Reward;
import com.retask.game.model.User;
import com.retask.game.services.RewardService;
import com.retask.game.services.TaskService;
import com.retask.game.services.UserService;

@RestController
public class ReTaskRestAPIs {

	@Autowired
	UserService userService = new UserService();
	@Autowired
	TaskService taskService = new TaskService();
	@Autowired
	RewardService rewardService = new RewardService();

	@GetMapping("/api/gettasksbyusername")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Object> gettasksbyusername(Model model, Principal principal) {

		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
			
		return ResponseEntity.ok(taskService.getTasksbyUsername(user.getUsername()));
	}
	
	@GetMapping("/api/getrewardsbyusername")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Object> rewardsbyusername(Model model, Principal principal) {

		System.out.println("In here");
		
		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
			
		return ResponseEntity.ok(rewardService.getRewardsByUsername(user.getUsername()));
	}

	
	@PostMapping("/api/createupdaterewards")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<RetaskStatusResponse> createrewardsforusername(@Valid @RequestBody List<Reward> rewards, Model model, Principal principal) {

		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
		
		for (Reward reward: rewards) {	
			if (!(reward.getUsername().equals(user.getUsername()))) {
				return ResponseEntity.ok().body(new RetaskStatusResponse(-1, "Username on reward does not match to user logged in") );
			}
		}
		
		rewardService.createRewardsForUsername(rewards);
		
		return ResponseEntity.ok().body(new RetaskStatusResponse(0, "ok") );
			
	}
	
	

}