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

import com.retask.game.message.request.RewardRequest;
import com.retask.game.message.request.TaskRequest;
import com.retask.game.message.response.RetaskStatusResponse;
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

	
	/**
	 * gets the tasks by username.  Returns all the tasks associated with a user.
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping("/api/gettasksbyusername")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Object> gettasksbyusername(Model model, Principal principal) {

		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
			
		return ResponseEntity.ok(taskService.getTasksbyUsername(user.getUsername()));
	}
	
	/**
	 * creates tasks 
	 * @param rewardRequests
	 * @param model
	 * @param principal
	 * @return
	 */
	@PostMapping("/api/createtasks")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<RetaskStatusResponse> createtasksforusername(@Valid @RequestBody List<TaskRequest> taskRequests, Model model, Principal principal) {

		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
		
		
		taskService.createTasksForUsername(taskRequests, user);
		
		return ResponseEntity.ok().body(new RetaskStatusResponse(0, "ok") );
			
	}
	
	/**
	 * updates tasks 
	 * @param rewardRequests
	 * @param model
	 * @param principal
	 * @return
	 */
	@PostMapping("/api/updatetasks")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<RetaskStatusResponse> updatetasksforusername(@Valid @RequestBody List<TaskRequest> taskRequests, Model model, Principal principal) {

		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
		
		
		taskService.updateTasksForUsername(taskRequests, user);
		
		return ResponseEntity.ok().body(new RetaskStatusResponse(0, "ok") );
			
	}
	
	/**
	 * get rewards by username.  Returns all the rewards associated with a user
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping("/api/getrewardsbyusername")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Object> rewardsbyusername(Model model, Principal principal) {
		
		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
			
		return ResponseEntity.ok(rewardService.getRewardsByUsername(user.getUsername()));
	}

	
	/**
	 * creates a new reward for a specific user.
	 * @param rewardRequests
	 * @param model
	 * @param principal
	 * @return
	 */
	@PostMapping("/api/createrewards")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<RetaskStatusResponse> createrewardsforusername(@Valid @RequestBody List<RewardRequest> rewardRequests, Model model, Principal principal) {

		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
		
		System.out.println("In here");
		
		for (RewardRequest rewardRequest: rewardRequests) {	
			if (!(rewardRequest.getUsername().equals(user.getUsername()))) {
				return ResponseEntity.ok().body(new RetaskStatusResponse(-1, "Username on reward does not match to user logged in") );
			}
		}
		
		rewardService.createRewardsForUsername(rewardRequests);
		
		return ResponseEntity.ok().body(new RetaskStatusResponse(0, "ok") );
			
	}
	
	/**
	 * updates a specific reward.  This is done by user.
	 * @param rewardRequests
	 * @param model
	 * @param principal
	 * @return
	 */
	@PostMapping("/api/updaterewards")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<RetaskStatusResponse> updaterewardsforusername(@Valid @RequestBody List<RewardRequest> rewardRequests, Model model, Principal principal) {
		
		User user = userService.getUser(model, principal).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with -> username or email : "));
		
		
		for (RewardRequest rewardRequest: rewardRequests) {	
			if (!(rewardRequest.getUsername().equals(user.getUsername()))) {
				return ResponseEntity.ok().body(new RetaskStatusResponse(-1, "Username on reward does not match to user logged in") );
			}
		}
		
		rewardService.updateRewardsForUsername(rewardRequests);
		
		return ResponseEntity.ok().body(new RetaskStatusResponse(0, "ok") );
			
	}
	

}