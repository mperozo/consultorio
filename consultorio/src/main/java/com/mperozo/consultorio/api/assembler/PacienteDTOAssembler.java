package com.mperozo.consultorio.api.assembler;

import org.springframework.stereotype.Service;

import com.mperozo.consultorio.api.dto.PacienteDTO;
import com.mperozo.consultorio.model.entity.Paciente;

@Service
public class PacienteDTOAssembler {

	public Paciente toEntity(PacienteDTO dto) {
		
		return Paciente.builder()
				.id(dto.getId())
				.nome(dto.getNome()).build();
	}

}
