package com.mperozo.consultorio.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Convert;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoDTO {

	private Long id;
	private Long idMedico;
	private Long idPaciente;
	private Long idUsuarioAgendador;
	
	private String nomeMedico;
	private String nomePaciente;
	
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime dataHoraAtendimento;
	
	private StatusAtendimentoEnum statusAtendimento;
}
