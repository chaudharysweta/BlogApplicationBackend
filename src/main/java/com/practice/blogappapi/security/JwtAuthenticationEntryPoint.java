package com.practice.blogappapi.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !!");
    }
}

//@Component is used to mark the class as a Spring component, meaning it will be automatically detected and registered by the Spring container during component scanning.
// Components are typically used for declaring beans
//By implementing the AuthenticationEntryPoint interface, this class provides a strategy to handle authentication-related exceptions.
//commence method is an overridden method of AuthenticationEntryPoint.
//HttpServletRequest request: Represents the HTTP request that triggered the authentication exception.
//HttpServletResponse response: Represents the HTTP response to which the error message will be sent.
//AuthenticationException authException: Represents the authentication exception that occurred.
//We can customize the error message or behavior in the commence method based on their application requirements.
