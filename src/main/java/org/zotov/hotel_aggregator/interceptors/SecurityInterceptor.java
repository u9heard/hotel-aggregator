package org.zotov.hotel_aggregator.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.credentials.UserCredentials;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.exceptions.service.WrongCredentialsException;
import org.zotov.hotel_aggregator.interfaces.services.AuthService;

import java.lang.reflect.Method;
import java.util.Base64;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Autowired
    public SecurityInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod){
            Method method = handlerMethod.getMethod();
            RoleRequired roleRequired;
            try {
                roleRequired = method.getAnnotation(RoleRequired.class);
            } catch (NullPointerException e) {
                return true;
            }

            UserCredentials credentials = getCredentialsByRequest(request);
            if(credentials == null){
                setUnauthorizedError(response, "Secure layer");
                return false;
            }
            Long userId;
            try {
                userId = this.authService.authorize(credentials, roleRequired.value());
            } catch (ModelNotFoundException | WrongCredentialsException e) {
                setUnauthorizedError(response, "Secure layer");
                return false;
            }
            request.setAttribute("user_id", userId);
            return true;
        }
        return true;
    }

    private void setUnauthorizedError (HttpServletResponse response, String realm){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", String.format("Basic realm=\"%s\"", realm));
    }

    private UserCredentials getCredentialsByRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null){
            return null;
        }

        String base64Credentials = authHeader.substring(6);

        return new UserCredentials(base64Credentials);
    }

}
