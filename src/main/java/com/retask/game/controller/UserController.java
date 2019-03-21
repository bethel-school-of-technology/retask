package com.retask.game.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retask.game.model.User;
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
	public ResponseEntity<Object> uploadpic(@RequestParam("file") MultipartFile file, Model model, Principal principal)
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

		return ResponseEntity.ok("ok");
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

}