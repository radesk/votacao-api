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

}
