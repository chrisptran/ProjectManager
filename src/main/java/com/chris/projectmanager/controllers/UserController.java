package com.chris.projectmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chris.projectmanager.models.LoginUser;
import com.chris.projectmanager.models.User;
import com.chris.projectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserService userServ;
	
	public UserController(UserService userServ) {
		this.userServ = userServ;
	}
	
	@RequestMapping("/")
	public String loginReg(@ModelAttribute("newUser") User user, @ModelAttribute("loginUser") LoginUser  loginUser, HttpSession session) {
		if(session.getAttribute("user_id") != null) {
			return "redirect:/dashboard";
		}
		return "user/loginReg.jsp";
	}
	
	@PostMapping("/users/process")
	public String processRegister(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "Match", "passwords do not match");
		}
		if(userServ.getOneEmail(newUser.getEmail()) != null) {
			result.rejectValue("email", "Unique", "email is already taken");
		}
		if(result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "user/loginReg.jsp";
		}
		User newlyCreatedUser = userServ.registerUser(newUser);
		session.setAttribute("user_id", newlyCreatedUser.getId());
		return "redirect:/";
	}
	
	@PostMapping("/users/login")
	public String processLogin(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
		User loggingUser = userServ.login(loginUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "user/loginReg.jsp";
		}
		session.setAttribute("user_id", loggingUser.getId());
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
