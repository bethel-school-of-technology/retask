package com.retask.game.controller;

import java.io.IOException;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retask.game.message.request.UserChangePassword;
import com.retask.game.message.request.UserUpdateForm;
import com.retask.game.message.response.RetaskStatusResponse;
import com.retask.game.model.User;
import com.retask.game.repository.UserRepository;
import com.retask.game.services.ToolsService;
import com.retask.game.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService = new UserService();
	@Autowired
	ToolsService toolsService = new ToolsService();
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AuthenticationManager authenticationManager;


	/**
	 * Upload a picture for the user profile.
	 * 
	 * @param file
	 * @param model
	 * @param principal
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	@PostMapping(value = "/uploadPic")
	public ResponseEntity<RetaskStatusResponse> uploadpic(@RequestParam("file") MultipartFile file, Model model, Principal principal)
			throws IOException {

		// get the user from the UserDetail in principal
		User user = userService.getUser(model, principal).orElseThrow(() -> new UsernameNotFoundException(
				"User Not Found with -> username or email : " + userService.getUsername(model, principal)));

		// set the picture on the User Object
		user.setPic(file.getBytes());
		user.setPicfileType(file.getContentType());
		user.setPicfileName(file.getOriginalFilename());

		// save the user
		userService.saveUser(user);

		return ResponseEntity.ok().body(new RetaskStatusResponse(0, "ok") );
	}

	/**
	 * Download profile picture
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping("/downloadPic")
	public ResponseEntity<Resource> downloadFile(Model model, Principal principal) {

		// get the user from the UserDetail in principal
		User user = userService.getUser(model, principal).orElseThrow(() -> new UsernameNotFoundException(
				"User Not Found with -> username or email : " + userService.getUsername(model, principal)));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(user.getPicfileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + user.getPicfileName() + "\"")
				.body(new ByteArrayResource(user.getPic()));
	}

	/**
	 * Send Password Email
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping("/sendResetPasswordEmail")
	public ResponseEntity<Resource> sendResetPasswordEmail(Model model, Principal principal) {

		// get the user from the UserDetail in principal
		User user = userService.getUser(model, principal).orElseThrow(() -> new UsernameNotFoundException(
				"User Not Found with -> username or email : " + userService.getUsername(model, principal)));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(user.getPicfileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + user.getPicfileName() + "\"")
				.body(new ByteArrayResource(user.getPic()));
	}

	@PostMapping("/updateUser")
	public ResponseEntity<RetaskStatusResponse> registerUser(@Valid @RequestBody UserUpdateForm updateUserForm, Model model,
			Principal principal) {

		User user = userService.getUser(model, principal).orElseThrow(() -> new UsernameNotFoundException(
				"User Not Found with -> username or email : " + userService.getUsername(model, principal)));

//		user.setEmail(updateUserForm.getEmail());
		user.setFirstName(updateUserForm.getFirstName());
		user.setLastName(updateUserForm.getLastName());
		user.setPhoneNbr(updateUserForm.getPhoneNbr());
		user.setUpdateDateTime();
		
		userRepository.save(user);

		return ResponseEntity.ok().body(new RetaskStatusResponse(0, "ok") );
	}

	@PostMapping("/changePassword")
	public ResponseEntity<RetaskStatusResponse> changePassword(@Valid @RequestBody UserChangePassword userChangePassword,
			Model model, Principal principal) {
		
		System.out.println(userChangePassword.getPassword());
		
		
		User user = userService.getUser(model, principal).orElseThrow(() -> new UsernameNotFoundException(
				"User Not Found with -> username or email : " + userService.getUsername(model, principal)));
		
		System.out.println(user.getUsername());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken( user.getUsername(), userChangePassword.getPassword()));

		if (authentication.isAuthenticated()) {
			user.setPassword(encoder.encode(userChangePassword.getNewPassword()));

			userRepository.save(user);

			return ResponseEntity.ok().body(new RetaskStatusResponse(0, "ok") );
		} else {
			return ResponseEntity.ok().body(new RetaskStatusResponse(-1, "Invalid Password"));
		}
	}

}