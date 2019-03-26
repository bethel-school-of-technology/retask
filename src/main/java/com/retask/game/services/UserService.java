package com.retask.game.services;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.retask.game.model.User;
import com.retask.game.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * This method is used to get the user id by taking the username entered at
	 * login and then going to the db to extract the user id.
	 * 
	 * @param dao
	 * @param model
	 * @param principal
	 * @return
	 */
	public Long getUserId(Model model, Principal principal) {

		// this gets the user details from the principal
		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
		// model is used get the attribute
		model.addAttribute("userDetails", userDetails);
		// find the user in the userRepository
		Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
		// return the user id

		return user.get().getId();

	}

	/**
	 * Used to the get User object from the login informaiton
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	public Optional<User> getUser(Model model, Principal principal) {

		// this gets the user details from the principal
		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
		// model is used get the attribute
		model.addAttribute("userDetails", userDetails);
		// find the user in the userRepository
		Optional<User> user = getUser(userDetails.getUsername());

		// return the user
		return user;
	}

	/**
	 * Used to get the username from the login
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	public String getUsername(Model model, Principal principal) {

		// this gets the user details from the principal
		UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
		// model is used get the attribute
		model.addAttribute("userDetails", userDetails);
		// find the user in the userRepository
		return userDetails.getUsername();

	}

	/**
	 * Used to get the User object from the userName
	 * 
	 * @param userName
	 * @return
	 */
	public Optional<User> getUser(String userName) {

		// find the user in the userRepository
		Optional<User> user = userRepository.findByUsername(userName);

		// return the user
		return user;

	}

	/**
	 * Save the use
	 * 
	 * @param user
	 * @return
	 */
	public User saveUser(User user) {

		user.setUpdateDateTime();
		userRepository.save(user);

		return user;

	}

}
