package com.dbo.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;


@Data
@Embeddable
public class VotosKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_usuario")
    String idUsuario;

    @Column(name = "id_pauta")
    String idPauta;

}
