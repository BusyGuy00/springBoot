package com.packt.cardatebase;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

	@Override 
	//예외를 매개 변수로 받는 메소드 구현 
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		//응답 상태는 401로 설정 할것이다. 
		//SC_UNAUTHORIZED 401
		//SC_NOT_FOUND 404
		//SC_FORBIDDEN 403
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		//PrintWriter로 적어서 보낼 수 있고 받는 쪽에서 나타난다.
		PrintWriter write = response.getWriter();
		write.println("Error: " + authException.getMessage());
	}

}










