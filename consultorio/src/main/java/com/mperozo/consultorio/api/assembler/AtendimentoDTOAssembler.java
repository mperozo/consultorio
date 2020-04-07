package com.mperozo.consultorio.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mperozo.consultorio.api.dto.AtendimentoDTO;
import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.service.PacienteService;
import com.mperozo.consultorio.service.UsuarioService;

@Service
public class AtendimentoDTOAssembler {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PacienteService pacienteService;
	
	public Atendimento toEntity(AtendimentoDTO dto) {
		
		return Atendimento.builder()
				.id(dto.getId())
				.dataHoraAtendimento(dto.getDataHoraAtendimento())
				.paciente(pacienteService.buscarPorId(dto.getIdPaciente()).get())
				.medico(usuarioService.buscarPorId(dto.getIdMedico()).get())
				.usuarioAgendador(usuarioService.buscarPorId(dto.getId()).get())
				.status(dto.getStatusAtendimento())
				.build();
	}

}
