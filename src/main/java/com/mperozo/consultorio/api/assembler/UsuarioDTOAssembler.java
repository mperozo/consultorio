package com.mperozo.consultorio.api.assembler;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mperozo.consultorio.api.dto.UsuarioDTO;
import com.mperozo.consultorio.model.entity.Usuario;

@Service
public class UsuarioDTOAssembler {

	public Usuario toEntity(UsuarioDTO dto) {
		
		return Usuario.builder()
				.nome(dto.getNome())
				.email(dto.getEmail())
				.senha(dto.getSenha())
				.tipo(dto.getTipoUsuario()).build();
	}

	public UsuarioDTO toDTO(Usuario entity) {
		
		return UsuarioDTO.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.tipoUsuario(entity.getTipo())
				.email(entity.getEmail()).build();
	}
	
	public List<UsuarioDTO> toDTOList(List<Usuario> entityList) {
		
		List<UsuarioDTO> usuarioDTOList = new LinkedList<UsuarioDTO>();
		
		entityList.forEach(paciente -> usuarioDTOList.add(toDTO(paciente)));
		
		return usuarioDTOList;
	}

}
