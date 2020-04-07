package com.mperozo.consultorio.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Convert;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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
	
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime dataHoraAtendimento;
	
	private StatusAtendimentoEnum statusAtendimento;
}
