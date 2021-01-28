package com.dbo.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dbo.api.constants.MessageUtil;
import com.dbo.api.exceptionhandler.exceptions.PautaStatusException;
import com.dbo.api.exceptionhandler.exceptions.PautaTimeException;
import com.dbo.api.exceptionhandler.exceptions.UsuarioPermissionException;
import com.dbo.api.model.Pauta;
import com.dbo.api.model.Usuario;
import com.dbo.api.model.Voto;
import com.dbo.api.model.Votos;
import com.dbo.api.model.VotosKey;
import com.dbo.api.model.request.VotoRequest;
import com.dbo.api.repository.PautaRepository;

@Service
public class PautaService {
	@Autowired
	private PautaRepository pr;
	
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private VotosService vs;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PautaService.class);
	
	public Pauta searchByNome(String nome) {
		Optional<Pauta> savedPauta = Optional.ofNullable(pr.searchByNome(nome));
		if (savedPauta.isPresent())
			return savedPauta.get();
		else
			throw new EmptyResultDataAccessException(1);
	}

	public void setEncerramento(Pauta pauta) throws PautaTimeException {
		LOGGER.info("Método setEncerramento");
		if (pauta.getEncerramento() == null) pauta.setEncerramento(LocalDateTime.now().plusMinutes(1));
		else {
			if (pauta.getEncerramento().isBefore(LocalDateTime.now())) {
				throw new PautaTimeException(MessageUtil.ENCERRAMENTO_INVALIDO);
			}
		}
	}

	public void votar(VotoRequest votoRequest) throws UsuarioPermissionException, PautaStatusException {
		Usuario usuario = us.searchByCpf(votoRequest.getCpf());
		Pauta pauta = pr.searchByNome(votoRequest.getNomePauta());
		VotosKey vk = VotosKey.builder()
				.idPauta(pauta.getId())
				.idUsuario(usuario.getId())
				.build();
				
		if(usuario.getVota() == null || !usuario.getVota()) {
			throw new UsuarioPermissionException(MessageUtil.SEM_PERMISSAO);
		}
		
		if(pauta.getEncerramento().isBefore(LocalDateTime.now())) {
			throw new PautaStatusException(MessageUtil.ENCERRADA);
		}
		
		if (vs.findById(vk).isPresent()) {
			throw new UsuarioPermissionException(MessageUtil.JA_VOTOU);
		}
		
		vs.save(Votos.builder()
				.idPauta(pauta)
				.idUsuario(usuario)
				.voto(votoRequest.getVoto())
				.idVoto(vk)
				.build());
	}

	public String contabilizarVotos(String nomePauta) {
		Pauta pauta = this.searchByNome(nomePauta);
		
		if(pauta.getEncerramento().isBefore(LocalDateTime.now())) {
			String resultado;
			int total = pauta.getVotos().size();
			if(total == 0) {
				return MessageUtil.NAO_FOI_VOTADA;
			}else {
				List<Votos> votos = pauta.getVotos();
				int contra = (int) votos.stream().filter( v -> v.getVoto() == Voto.Não).count();
				int favor = total - contra;
				if (favor == contra) resultado = MessageUtil.EMPATADA;
				else {
					resultado = favor>contra ? MessageUtil.APROVADA : MessageUtil.REPROVADA;
				}

				return String.format(MessageUtil.RESULTADO, nomePauta, resultado, favor, contra, total);
			}
			
		}else {
			return MessageUtil.EM_ANDAMENTO;
		}
		
	}

}
