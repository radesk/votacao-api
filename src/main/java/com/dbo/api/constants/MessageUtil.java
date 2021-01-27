package com.dbo.api.constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageUtil {
	
	public final static String EM_ANDAMENTO = "A votação está em andamento!";
	public static String RESULTADO = "A votação da pauta %s foi %s com:"
			+ "\n%s votos a favor."
			+ "\n%s votos contra."
			+ "\nCom um total de %s votos.";
	public static final String NAO_FOI_VOTADA = "A votação encerrou sem votos!";
	public static final String EMPATADA = "emaptada";
	public static final String APROVADA = "aprovada";
	public static final String REPROVADA = "reprovada";
	public static final String VOTO_REALIZADO = "Voto realizado com sucesso!";
	public static final String ENCERRAMENTO_INVALIDO = "Encerramento não pode ser antes do momento atual";
	public static final String SEM_PERMISSAO = "Usuário não tem permissão para votar";
	public static final String ENCERRADA = "A votação dessa pauta já foi encerrada";
	public static final String JA_VOTOU = "Usuario já votou nesta pauta";

}
