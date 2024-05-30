package com.example.sec_jpa_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sec_jpa_thymeleaf.dto.MemberDto;
import com.example.sec_jpa_thymeleaf.entity.Member;
import com.example.sec_jpa_thymeleaf.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryPasswordEncoder;
	
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
	public @ResponseBody String registProc(MemberDto memberDto) {
		log.info("registProc....." + memberDto);
		
		Member member = new Member();
		member.setUsername(memberDto.getUsername());
		
		String newPw = bCryPasswordEncoder.encode(memberDto.getPassword());//암호화 메소드(기존패스워드)
		member.setPassword(newPw);
		
		member.setName(memberDto.getName());
		member.setRole("ROLE_MEMBER"); //역할 부여
		
		memberRepository.save(member);
		
		return "회원가입 완료됨.";
	}
	
}
