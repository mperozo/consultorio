package com.mperozo.consultorio.api.assembler;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mperozo.consultorio.api.dto.AtendimentoDTO;
import com.mperozo.consultorio.api.dto.UsuarioDTO;
import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.entity.Usuario;
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
				.paciente(dto.getIdPaciente() != null ? pacienteService.buscarPorId(dto.getIdPaciente()) : null)
				.medico(dto.getIdMedico() != null ? usuarioService.buscarPorId(dto.getIdMedico()) : null)
				.usuarioAgendador(dto.getIdUsuarioAgendador() != null ? usuarioService.buscarPorId(dto.getIdUsuarioAgendador()) : null)
				.status(dto.getStatusAtendimento())
				.build();
	}
	
	public AtendimentoDTO toDTO(Atendimento entity) {
		
		return AtendimentoDTO.builder()
				.id(entity.getId())
				.nomePaciente(entity.getPaciente().getNome())
				.nomeMedico(entity.getMedico().getNome())
				.statusAtendimento(entity.getStatus()).build();
	}

	public List<AtendimentoDTO> toDTOList(List<Atendimento> entityList) {
		
		List<AtendimentoDTO> atendimentoDTOList = new LinkedList<AtendimentoDTO>();
		
		entityList.forEach(atendimento -> atendimentoDTOList.add(toDTO(atendimento)));
		
		return atendimentoDTOList;
	}

}
