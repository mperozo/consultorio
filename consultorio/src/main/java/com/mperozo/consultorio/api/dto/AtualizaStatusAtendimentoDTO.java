package com.mperozo.consultorio.api.dto;

import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizaStatusAtendimentoDTO {

	private StatusAtendimentoEnum statusAtendimento;
}
