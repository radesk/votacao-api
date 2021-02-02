package com.dbo.api.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.dbo.api.constants.MessageUtil;
import com.dbo.api.model.Pauta;
import com.dbo.api.model.request.PautaRequest;
import com.dbo.api.model.request.VotoRequest;
import com.dbo.api.repository.PautaRepository;
import com.dbo.api.service.PautaService;
import com.dbo.api.util.PautaUtil;
import com.dbo.api.util.request.PautaRequestUtil;
import com.dbo.api.util.request.VotoRequestUtil;

@SpringJUnitConfig
public class PautaResourceTest {
	
	@InjectMocks
	PautaResource resource;
	
	@Mock
	PautaService service;
	
	@Mock
	PautaRepository repository;
	
	private static final String NOME = "Teste";
	
	@BeforeEach()
    public void setUp() {
    }

	@Test
	void testList() {
		
		List<Pauta> m1 = PautaUtil.buildList();
		Mockito.when(this.repository.findAll()).thenReturn(m1);
		List<Pauta> m2 = resource.list();
		
		assertEquals(m1.size(), m2.size());

	}

	@Test
	void testSearchByName() {
		Pauta p1 = PautaUtil.buildPauta();
		Mockito.when(this.service.searchByNome(NOME)).thenReturn(p1);
		ResponseEntity<?> response = resource.searchByName(NOME);
		
		assertEquals(p1, response.getBody());
		assertThat(response.getBody()).isNotNull();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

	@Test
	void testSave() {
		Pauta p1 = PautaUtil.buildPauta();
		PautaRequest request = PautaRequestUtil.buildRequest();
		HttpServletResponse serv = new MockHttpServletResponse();
		Mockito.when(service.save(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(p1);
		ResponseEntity<Pauta> response = resource.save(request, serv);
		
		assertEquals(p1.getDescricao(), response.getBody().getDescricao());
		assertEquals(p1.getEncerramento(), response.getBody().getEncerramento());
		assertEquals(p1.getNome(), response.getBody().getNome());
		assertEquals(p1.getVotos(), response.getBody().getVotos());
		assertEquals(p1.getId(),response.getBody().getId());
		
	}

	@Test
	void testVotar() {
		VotoRequest vr = VotoRequestUtil.buildRequest();
		Mockito.doNothing().when(service).votar(Mockito.any());
		ResponseEntity<?> response = resource.votar(vr);
		assertThat(response.getBody()).isNotNull();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

	@Test
	void testContabilizarVotos() {
		 Mockito.when(service.contabilizarVotos(NOME)).thenReturn(MessageUtil.APROVADA);
		 ResponseEntity<?> response = resource.contabilizarVotos(NOME);
		 assertEquals(MessageUtil.APROVADA, response.getBody());
		 
	}

}
