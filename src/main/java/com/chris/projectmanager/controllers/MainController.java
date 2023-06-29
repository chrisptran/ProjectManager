package com.chris.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chris.projectmanager.models.Project;
import com.chris.projectmanager.models.User;
import com.chris.projectmanager.services.ProjectService;
import com.chris.projectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	private ProjectService projectServ;
	
	@Autowired
	private UserService userServ;
	
	@RequestMapping("/dashboard")
	public String main(HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		Long user_id = (Long) session.getAttribute("user_id");
		User user = userServ.getOne(user_id);
		
		model.addAttribute("user", user);
		model.addAttribute("unassignedProjects", projectServ.getUnassignedProjects(user));
		model.addAttribute("assignedProjects", projectServ.getAssignedProjects(user));
		return "project/dashboard.jsp";
	}
	
	@RequestMapping("/projects/new")
	public String newProject(@ModelAttribute("project") Project project, HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/users/logout";
		}
		Long user_id = (Long) session.getAttribute("user_id");
		
		model.addAttribute("user_id", user_id);
		return "project/newProject.jsp";
	}
	
	@PostMapping("/projects/new")
	public String addProject(@Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/users/logout";
		}
		if(result.hasErrors()) {
			return "project/newProject.jsp";
		} else {
			projectServ.addProject(project);
			
			Long user_id = (Long) session.getAttribute("user_id");
			User user = userServ.getOne(user_id);
			user.getProjects().add(project);
			userServ.updateUser(user);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/dashboard/join/{id}")
	public String joinTeam(@PathVariable("id") Long id, HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/users/logout";
		}
		Long user_id = (Long) session.getAttribute("user_id");
		
		Project project = projectServ.findById(id);
		User user = userServ.findById(user_id);
		
		user.getProjects().add(project);
		userServ.updateUser(user);
		
		model.addAttribute("user", user);
		model.addAttribute("unassignedProjects", projectServ.getUnassignedProjects(user));
		model.addAttribute("assignedProjects", projectServ.getAssignedProjects(user));
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/dashboard/leave/{id}")
	public String leaveTeam(@PathVariable("id") Long id, HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/users/logout";
		}
		
		Long user_id = (Long) session.getAttribute("user_id");
		
		Project project = projectServ.findById(id);
		User user = userServ.findById(user_id);
		
		user.getProjects().remove(project);
		userServ.updateUser(user);
		
		model.addAttribute("user", user);
		model.addAttribute("unassignedProjects", projectServ.getUnassignedProjects(user));
		model.addAttribute("assignedProjects", projectServ.getAssignedProjects(user));
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/projects/{id}")
	public String viewProject(@PathVariable("id") Long id, HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/users/logout";
		}
		Project project = projectServ.findById(id);
		model.addAttribute("project", project);
		return "project/viewProject.jsp";
	}
	
	@RequestMapping("/projects/edit/{id}")
	public String editProject(@PathVariable("id") Long id, HttpSession session, Model model) {
		if(session.getAttribute("user_id")  == null) {
			return "redirect:/users/logout";
		}
		Project project = projectServ.findById(id);
		model.addAttribute("project", project);
		return "project/editProject.jsp";
	}
	
	@PostMapping("/projects/edit/{id}")
	public String updateProject(@PathVariable("id") Long id, @Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {
		if(session.getAttribute("user_id")  == null) {
			return "redirect:/users/logout";
		}
		
		Long user_id = (Long) session.getAttribute("user_id");
		
		User user = userServ.findById(user_id);
		
		if(result.hasErrors()) {
			return "project/editProject.jsp";
		} else {
			Project thisProject = projectServ.findById(id);
			project.setUsers(thisProject.getUsers());
			project.setLead(user);
			projectServ.updateProject(project);
			return "redirect:/dashboard";
		
		}
	}
	
	@RequestMapping("/projects/delete/{id}")
	public String deleteProject(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("user_id")  == null) {
			return "redirect:/users/logout";
		}
		Project project = projectServ.findById(id);
		projectServ.deleteProject(project);
		
		return "redirect:/dashboard";
	}

}
