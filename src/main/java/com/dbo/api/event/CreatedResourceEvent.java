package com.dbo.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class CreatedResourceEvent extends ApplicationEvent{
	
	private HttpServletResponse response;
	private String id;

	public CreatedResourceEvent(Object source, HttpServletResponse response, String id) {
		super(source);
		this.response = response;
		this.id = id;
	}
	private static final long serialVersionUID = 1L;
	

}
