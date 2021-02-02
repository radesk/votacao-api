package com.dbo.api.util;
import java.util.Collections;
import java.util.List;

import com.dbo.api.model.Pauta;
import com.dbo.api.model.Usuario;
import com.dbo.api.model.Voto;
import com.dbo.api.model.Votos;
import com.dbo.api.model.VotosKey;

public class VotosUtil {
	
	private static final VotosKey VK = VotosKeyUtil.buildKey();
	private static final Voto VOTO = Voto.Sim;
	private static final Usuario USUARIO = UsuarioUtil.buildUsuario();
	private static final Pauta PAUTA = PautaUtil.buildPauta();
	
	public static Votos buildVotos() {
		return Votos.builder()
				.idPauta(PAUTA)
				.idUsuario(USUARIO)
				.idVoto(VK)
				.voto(VOTO)
				.build();
	}
	
	public static List<Votos> buildList(){
		return Collections.singletonList(buildVotos());
	}

}
