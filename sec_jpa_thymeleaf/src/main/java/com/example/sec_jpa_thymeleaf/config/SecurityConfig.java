package com.example.sec_jpa_thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/","/registForm", "/registProc").permitAll()
				.requestMatchers("/members/**").hasAnyRole("ADMIN", "MEMBER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				);
		
		http.formLogin((auth) -> auth
				.loginPage("/login") //로그인 폼 지정 - 쓰지 않으면 Spring Security가 제공하는 로그인 폼 사용
				.loginProcessingUrl("/loginProc") //로그인 폼 지정 후 폼 파라미터 보내는 경로지정 - 컨트롤러에 직접 만들지 않는다. (Spring Security가 알아서 처리함)
				.defaultSuccessUrl("/success") 
				.permitAll()
				);
		
		http.csrf(AbstractHttpConfigurer::disable); //csrf기능 끄기
		
		return http.build();
	}
}
