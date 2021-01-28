package com.dbo.api.integration;

import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dbo.api.model.CpfStatus;
import com.dbo.api.model.response.CpfResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Service
public class Terceiros {
	
	private HttpClient httpClient = HttpClient.create()
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 25000)
			.doOnConnected(conn -> 
			conn.addHandlerLast(new ReadTimeoutHandler(25000, TimeUnit.MILLISECONDS))
			.addHandlerLast(new WriteTimeoutHandler(25000, TimeUnit.MILLISECONDS)));

	private WebClient client = WebClient.builder()
			.baseUrl("https://user-info.herokuapp.com")
		    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		    .clientConnector(new ReactorClientHttpConnector(httpClient))
			.build();
	public Boolean validaCpf(String cpf) throws JsonMappingException, JsonProcessingException {
		
		WebClient.RequestBodySpec request = client
				.method(HttpMethod.GET)
				.uri(uriBuilder -> uriBuilder.path("/users/{cpf}").build(cpf));
		String response = request.retrieve()
				  .bodyToMono(String.class)
				  .block();
		
		CpfStatus en = CpfStatus.ABLE_TO_VOTE;
		if (response == "ABLE_TO_VOTE") {
			en = CpfStatus.ABLE_TO_VOTE;
		}else if (response == "UNABLE_TO_VOTE") {
			en = CpfStatus.UNABLE_TO_VOTE;
		}
		CpfResponse map = new CpfResponse(en);
		
		
		return map.getStatus().ordinal() == 1 ? true:false;
	}

}
