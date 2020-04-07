package com.mperozo.consultorio.api.assembler;

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

}
