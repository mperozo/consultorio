package com.mperozo.consultorio.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "CONSULTA", schema = "CONSULTORIO")
@Data
@Builder
public class Consulta {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID")
	private Usuario usuarioAgendador;

	@ManyToOne
	@JoinColumn(name = "ID")
	private Usuario medico;

	@ManyToOne
	@JoinColumn(name = "ID")
	private Paciente paciente;

	@Column(name = "DATA_CRIACAO")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCriacao;

}
