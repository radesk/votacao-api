package com.dbo.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dbo.api.model.Pauta;
import com.dbo.api.model.Usuario;
import com.dbo.api.model.VotoRequest;
import com.dbo.api.model.Votos;
import com.dbo.api.model.VotosKey;
import com.dbo.api.repository.PautaRepository;

@Service
public class PautaService {
	@Autowired
	private PautaRepository pr;
	
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private VotosService vs;
	
	public Pauta searchByNome(String nome) {
		Optional<Pauta> savedPauta = Optional.ofNullable(pr.searchByNome(nome));
		if (savedPauta.isPresent())
			return savedPauta.get();
		else
			throw new EmptyResultDataAccessException(1);
	}

	public void setEncerramento(Pauta pauta) throws Exception {
		if (pauta.getEncerramento() == null) pauta.setEncerramento(LocalDateTime.now().plusMinutes(1));
		else {
			if (pauta.getEncerramento().isBefore(LocalDateTime.now())) {
				throw new Exception("Encerramento não pode ser antes do momento atual");
			}
		}
	}

	public void votar(VotoRequest votoRequest) throws Exception {
		Usuario usuario = us.searchByCpf(votoRequest.getCpf());
		Pauta pauta = pr.searchByNome(votoRequest.getNomePauta());
		VotosKey vk = VotosKey.builder()
				.idPauta(pauta.getId())
				.idUsuario(usuario.getId())
				.build();
				
		if(usuario.getVota() == null || !usuario.getVota()) {
			throw new Exception("Usuário não tem permissão para votar");
		}
		
		if(pauta.getEncerramento().isBefore(LocalDateTime.now())) {
			throw new Exception("A votação dessa pauta já foi encerrada");
		}
		
		if (vs.findById(vk).isPresent()) {
			throw new Exception("Usuario já votou nesta pauta");
		}
		
		vs.save(Votos.builder()
				.idPauta(pauta)
				.idUsuario(usuario)
				.voto(votoRequest.getVoto())
				.idVoto(vk)
				.build());
	}

}
