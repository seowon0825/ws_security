package com.example.sec_jpa_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sec_jpa_thymeleaf.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByUsername(String username);

	
}
