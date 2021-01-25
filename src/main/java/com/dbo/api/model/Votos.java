package com.dbo.api.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "votos")
public class Votos {
	
	@EmbeddedId
	private VotosKey idVoto;
	
	@Enumerated(EnumType.ORDINAL)
	private Voto voto;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idUsuario")
	@JoinColumn(name = "id_usuario")
	private Usuario idUsuario;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idPauta")
	@JoinColumn(name = "id_pauta")
	private Pauta idPauta;

}
