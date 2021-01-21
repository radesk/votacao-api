package com.dbo.api.listener;

import java.net.URI;
import java.util.function.BiConsumer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dbo.api.event.CreatedResourceEvent;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent>{
	BiConsumer<HttpServletResponse, String> addHeader = (response, id) ->{
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	};
	
	@Override
	public void onApplicationEvent(CreatedResourceEvent event) {
		HttpServletResponse response = event.getResponse();
		String id = event.getId();
		addHeader.accept(response, id);;

	}
	
}
