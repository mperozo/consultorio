package com.mperozo.consultorio.api.dto;

import java.time.LocalDateTime;

import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AtendimentoDTO {

	private Long id;
	private Long idMedico;
	private Long idPaciente;
	private Long idUsuarioAgendador;
	private LocalDateTime dataHoraAtendimento;
	private StatusAtendimentoEnum statusAtendimento;
}
