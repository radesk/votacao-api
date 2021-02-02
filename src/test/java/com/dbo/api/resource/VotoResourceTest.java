package com.dbo.api.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.dbo.api.model.Votos;
import com.dbo.api.service.VotosService;
import com.dbo.api.util.VotosUtil;

@SpringJUnitConfig
class VotoResourceTest {

	
	@InjectMocks
	VotosResource resource;
	
	@Mock
	VotosService service;
	
	@BeforeAll
	public static void setUp() {
    }

	@Test
	void testList() {
		
		List<Votos> m1 = VotosUtil.buildList();
		Mockito.when(service.findAll()).thenReturn(m1);
		List<Votos> m2 = resource.list();
		
		assertEquals(m1.size(), m2.size());
		assertEquals(m1, m2);

	}

}
