package com.mperozo.consultorio.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "ATENDIMENTO", schema = "CONSULTORIO")
@Data
@Builder
public class Atendimento {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_AGENDADOR")
	private Usuario usuarioAgendador;

	@ManyToOne
	@JoinColumn(name = "ID_MEDICO")
	private Usuario medico;
	
	@ManyToOne
	@JoinColumn(name = "ID_PACIENTE")
	private Paciente paciente;

	@Column(name = "DATA_HORA_AGENDAMENTO")
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime dataHoraAgendamento;
	
	@Column(name = "DATA_HORA_INCLUSAO")
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime dataHoraInclusao;

}
