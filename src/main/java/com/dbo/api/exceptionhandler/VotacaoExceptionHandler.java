package com.dbo.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class VotacaoExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource msgSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){

		String userMessage = msgSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String devMsg = ex.getCause()!= null? ex.getCause().toString(): ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMsg));
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = buildErrorList(ex.getBindingResult());
		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	private ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String userMessage = msgSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String devMsg = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, devMsg));
		
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	private List<Error> buildErrorList(BindingResult br){
		List<Error> errors = new ArrayList<>();
		for(FieldError fe: br.getFieldErrors()) {
			String userMessage = msgSource.getMessage(fe, LocaleContextHolder.getLocale());
			String devMsg = fe.toString();
			errors.add(new Error(userMessage,devMsg));
		}
		
		return errors;
	}
	
	@AllArgsConstructor
	@Data
	public static class Error{
		private String userMsg;
		private String devMsg;
		
	}
	
}
