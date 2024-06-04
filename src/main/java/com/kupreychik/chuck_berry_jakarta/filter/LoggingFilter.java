package com.kupreychik.chuck_berry_jakarta.filter;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialization needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("Request received: {} {}", request.getRemoteAddr(), request.getRemotePort());
        chain.doFilter(request, response);
        log.info("Response sent to: {} {}", request.getRemoteAddr(), request.getRemotePort());
    }

    @Override
    public void destroy() {
        // No cleanup needed
    }
}
