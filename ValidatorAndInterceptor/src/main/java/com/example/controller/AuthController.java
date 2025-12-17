package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.User;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "login";
		}
		System.out.println(">>> POST /auth/login <<<");

		User loginUser = userService.login(user.getUsername(), user.getPassword());
		if (loginUser == null) {
			result.rejectValue("username", "error.user", "Sai tài khoản hoặc mật khẩu");
			return "login";
		}

		session.setAttribute("user", loginUser);

		if (loginUser.getRole().equals("ADMIN")) {
			return "redirect:/admin/home";
		}
		return "redirect:/user/home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // ❗ XÓA TOÀN BỘ SESSION
	    return "redirect:/auth/login";
	}
}