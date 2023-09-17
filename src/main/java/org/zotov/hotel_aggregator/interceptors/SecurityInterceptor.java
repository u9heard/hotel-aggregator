package org.zotov.hotel_aggregator.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zotov.hotel_aggregator.annotations.RoleRequired;
import org.zotov.hotel_aggregator.credentials.UserCredentials;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.exceptions.service.WrongCredentialsException;
import org.zotov.hotel_aggregator.models.User;
import org.zotov.hotel_aggregator.services.UserService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Autowired
    public SecurityInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        System.out.println(response.getCharacterEncoding());
        if(handler instanceof HandlerMethod handlerMethod){
            Method method = handlerMethod.getMethod();
            Class<?> controllerClass = handlerMethod.getBeanType();
            RoleRequired roleRequired = null;
            try {
                roleRequired = controllerClass.getAnnotation(RoleRequired.class);
            } catch (NullPointerException e) {
                return true;
            }

            UserCredentials credentials = getCredentialsByRequest(request);
            if(credentials == null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("WWW-Authenticate", "Basic realm=\"Realm\"");
                return false;
            }
            Long userId;
            try {
                userId = this.userService.authorize(credentials, roleRequired.value());
            } catch (ModelNotFoundException | WrongCredentialsException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("WWW-Authenticate", "Basic realm=\"Realm\""); // Todo подумать над тем как возвращать пользователю ошибку
                return false;
            }
            request.setAttribute("user_id", userId);
            return true;

        }
        return true;
    }

    private UserCredentials getCredentialsByRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null){
            return null;
        }

        String base64Credentials = authHeader.substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes);

        String[] credentials = decodedCredentials.split(":");
        String username = credentials[0];
        String password = credentials[1];

        System.out.println(username + " " + password); //TODO Delete this

        return new UserCredentials(username, password);
    }

}
