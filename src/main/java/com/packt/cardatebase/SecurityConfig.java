package com.packt.cardatebase;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.packt.cardatebase.service.UserDetailServiceImpl;




//빈등록을 위한 어노테이션
@Configuration
//SpringSecurityFilterChain이 자동으로 포함 된다.
//기본 웹 보안 구성을 비활성화 하고 
//자체 구성을 정의하기위한 어노테이션
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//출처를 명시적으로 정의 하려면 출처주소를 명시해주면 된다 // localhost:3000허가 
	//* : 모든 주소 혀용 포트 번호가 다른경우 (스프링과 리액트,포스트맨이라던지 같이 사용할때 다른 포트 번호를 이어 줘야 해서)
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source =
				new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		//setAllowedOrigins("*")으로 모든 곳에서 자원 교환을 허락 하거나 밑에 처럼 따로 지정도 가능하다.
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(false);
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	//인증필터 의존주입
	@Autowired
	private AuthenticationFilter authenticationFilter;
	
	//AuthenticationManager를 LoginController클래스에 주입했으므로 
	//빈으로 등록해준다.
	@Bean 
	public AuthenticationManager getAuthenticationManager() throws Exception{
		return authenticationManager();
	}
	
	//예외처리 의존 주입 
	@Autowired
	private AuthEntryPoint exceptionHandler;
	
	
	//스프링에서 Securitycontext.xml로 정의 하는 것을 자바 파일로 정의 하는 방법 이다. 
	//사용자가 정의한 userDetailService를 통해서 사용 하고 패스워드 인코더를 해준다.
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception{
		auth.userDetailsService(userDetailService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	//보호되는 경로와 보호되지 않는 경로를 정의 
	//csrf비활성화
	protected void configure(HttpSecurity http) throws Exception {
		//전체를 보호되지 않는 경로로 정의 한다
//		http.csrf().disable().cors().and()
//		.authorizeRequests().anyRequest().permitAll();
		
		//프로미스 연결 전 주석 처리 //추가 하면서 추가.cors().and() 
		//CorsConfigurationSource  
		http.csrf().disable().cors().and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests()
		//login앤드 포인트에 대한 요청은 보호하지 않는다.
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.GET, "/tour/*").permitAll()
		.antMatchers(HttpMethod.GET, "/citytour/*").permitAll()
		//다른 요청은 보호 된다.
		.anyRequest().authenticated().and()
		//예외 처리 설정 
		.exceptionHandling()
		.authenticationEntryPoint(exceptionHandler).and()
		.addFilterBefore(authenticationFilter,
				UsernamePasswordAuthenticationFilter.class);
		
		
	}
	
	
}
