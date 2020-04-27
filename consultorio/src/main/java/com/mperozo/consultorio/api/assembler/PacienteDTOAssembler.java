package com.mperozo.consultorio.api.assembler;

import java.util.LinkedList;
import java.util.List;

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
	
	public PacienteDTO toDTO(Paciente entity) {
		
		return PacienteDTO.builder()
				.id(entity.getId())
				.nome(entity.getNome()).build();
	}
	
	public List<PacienteDTO> toDTOList(List<Paciente> entityList) {
		
		List<PacienteDTO> pacienteDTOList = new LinkedList<PacienteDTO>();
		
		entityList.forEach(paciente -> pacienteDTOList.add(toDTO(paciente)));
		
		return pacienteDTOList;
	}

}
