package com.mperozo.consultorio.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ATENDIMENTO", schema = "CONSULTORIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atendimento {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_AGENDADOR")
	@NotBlank(message = "Usuário agendador é obrigatório.")
	private Usuario usuarioAgendador;

	@ManyToOne
	@JoinColumn(name = "ID_MEDICO")
	private Usuario medico;
	
	@ManyToOne
	@JoinColumn(name = "ID_PACIENTE")
	@NotBlank(message = "Paciente é obrigatório.")
	private Paciente paciente;
	
	@Column(name = "STATUS")
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Status do atendimento é obrigatório.")
	private StatusAtendimentoEnum status;

	@Column(name = "DATA_HORA_AGENDAMENTO")
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	@NotNull(message = "A data do atendimento é obrigatória.")
	private LocalDateTime dataHoraAgendamento;
	
	@Column(name = "DATA_HORA_INCLUSAO")
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	@NotNull(message = "A data de inclusão do atendimento é obrigatória.")
	private LocalDateTime dataHoraInclusao;

}
