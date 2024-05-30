package com.example.sec_jpa_thymeleaf.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sec_jpa_thymeleaf.config.CustomUserDetails;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("/")
	public String welcome(Model model, Principal principal,@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("admin welcome....");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
		
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("name", userDetails.getName());
		
		//Principal 객체 주입 : 현재 인증된 사용자의 정보를 담고 있는 객체
		model.addAttribute("username2", principal.getName());
		
		//현재 인증된 사용자의 세부 정보를 담고 있는 커스텀 객체(Principal객체는 username과 password 속성만 담고 있음)
		//@AuthenticationPrincipal을 사용하여 UserDetails 인터페이스의 구현체 CustomUserDetails 객체를 주입받아 사용하기
		model.addAttribute("username3", customUserDetails.getUsername());
		model.addAttribute("name3", customUserDetails.getName());
		model.addAttribute("role3", customUserDetails.getRole());
		
		return "admin/welcome";
	}
}
