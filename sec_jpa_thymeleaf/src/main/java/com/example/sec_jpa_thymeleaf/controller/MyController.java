package com.example.sec_jpa_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sec_jpa_thymeleaf.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {
	
	@RequestMapping("/")
	public String root() {
		log.info("root........");
		return "index";
	}
	
	@RequestMapping("/registForm")
	public String registForm() {
		log.info("registForm.....");
		return "registForm";
	}
	
	@RequestMapping("/registProc")
	public @ResponseBody String registProc(Member member) {
		log.info("registProc....." + member);
		
		return "회원가입 완료됨.";
	}
	
}
