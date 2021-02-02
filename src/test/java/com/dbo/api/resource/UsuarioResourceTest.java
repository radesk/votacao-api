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

import com.dbo.api.model.Usuario;
import com.dbo.api.model.request.UsuarioRequest;
import com.dbo.api.repository.UsuarioRepository;
import com.dbo.api.service.UsuarioService;
import com.dbo.api.util.UsuarioUtil;
import com.dbo.api.util.request.UsuarioRequestUtil;

import lombok.SneakyThrows;

@SpringJUnitConfig
class UsuarioResourceTest {

	@InjectMocks
	UsuarioResource resource;
	
	@Mock
	UsuarioService service;
	
	@Mock
	UsuarioRepository repository;
	
	private static final String CPF_VALIDO = "67203243060";
	private static final String ID = "5d41e95f-480a-410b-a88a-3cdf96b0467d";

	private static final Boolean VOTA = false;
	
	@BeforeEach()
    public void setUp() {
    }

	@Test
	void testList() {
		
		List<Usuario> m1 = UsuarioUtil.buildList();
		Mockito.when(this.repository.findAll()).thenReturn(m1);
		List<Usuario> m2 = resource.list();
		
		assertEquals(m1.size(), m2.size());

	}
	
	@Test
	void testSearchByCpf() {
		Usuario p1 = UsuarioUtil.buildUsuario();
		Mockito.when(this.service.searchByCpf(CPF_VALIDO)).thenReturn(p1);
		ResponseEntity<?> response = resource.searchByCpf(CPF_VALIDO);
		
		assertEquals(p1, response.getBody());
		assertThat(response.getBody()).isNotNull();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	@Test
	void testUpdate() {
		Usuario p1 = UsuarioUtil.buildUsuario();
		Usuario p2 = p1;
		p2.setVota(VOTA);
		Mockito.when(this.service.updateVoto(CPF_VALIDO, VOTA)).thenReturn(p2);
		ResponseEntity<Usuario> response = resource.update(CPF_VALIDO, VOTA);
		assertThat(response.getBody().getVota()).isFalse();
	}
	
	@Test
	@SneakyThrows
	void testSave() {
		Usuario p1 = UsuarioUtil.buildUsuario();
		UsuarioRequest request = UsuarioRequestUtil.buildRequest();
		HttpServletResponse serv = new MockHttpServletResponse();
		Mockito.when(service.save(Mockito.any(), Mockito.any())).thenReturn(p1);
		ResponseEntity<Usuario> response = resource.save(request, serv);
		
		assertEquals(p1.getCpf(), response.getBody().getCpf());
		assertEquals(p1.getId(), response.getBody().getId());
		assertEquals(p1.getVota(), response.getBody().getVota());
		assertEquals(p1.getVotos(), response.getBody().getVotos());
		response.getStatusCode().is2xxSuccessful();
		
	}
	
	@Test
	void delete() {
		Mockito.doNothing().when(this.repository).deleteById(ID);
		ResponseEntity<?> response = resource.delete(ID);
		response.getStatusCode().is2xxSuccessful();
	}

}
