package com.weather.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication
        .preauth.AbstractPreAuthenticatedProcessingFilter;

public class APIKeyFilter
        extends AbstractPreAuthenticatedProcessingFilter {

    private String authHeaderName;

    public APIKeyFilter(String authHeaderName) {
        this.authHeaderName = authHeaderName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(authHeaderName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}