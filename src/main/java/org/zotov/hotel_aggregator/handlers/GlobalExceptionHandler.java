package org.zotov.hotel_aggregator.handlers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.zotov.hotel_aggregator.exceptions.service.ModelConflictException;
import org.zotov.hotel_aggregator.exceptions.validation.DataValidationException;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;

import java.util.List;
import java.util.Locale;

/**
 *  This controller catching all exceptions from all @Controller and send response with message from thrown exception
 */


@ControllerAdvice(basePackages = "org.zotov.hotel_aggregator.controllers.defaultcontrollers")
public class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ModelAndView modelNotFound(ModelNotFoundException ex){
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject("errors", List.of(new FieldError("", "", ex.getMessage())));
        return modelAndView;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView validationException(MethodArgumentNotValidException exception){
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject("errors", exception.getAllErrors());
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ExceptionHandler(ModelConflictException.class)
    public ModelAndView modelConflict(ModelConflictException exception){
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject("errors", List.of(new FieldError("", "", exception.getMessage())));
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView internalServerError(RuntimeException e){
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("error_page");
        modelAndView.addObject("errors", List.of(new FieldError("", "", "Internal Server Error")));
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        return modelAndView;
    }
}
