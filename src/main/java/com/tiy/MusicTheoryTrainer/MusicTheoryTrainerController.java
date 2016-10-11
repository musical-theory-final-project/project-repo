package com.tiy.MusicTheoryTrainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by RdDvls on 10/10/16.
 */
@Controller
public class MusicTheoryTrainerController {
@Autowired
UserRepository users;

	@RequestMapping(path = "/midi", method = RequestMethod.GET)
	public String midiPage(Model model, HttpSession session) {
		return "midi";
	}

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		return "home";
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String newPlayer(HttpSession session, String firstName, String lastName, String email, String password){
		User user = users.findByEmail(email);
		if(user == null){
			user = new User(firstName,lastName,email,password);
			users.save(user);
		}
		session.setAttribute("user",user);
		return "redirect:/gamemenu";
	}
}
