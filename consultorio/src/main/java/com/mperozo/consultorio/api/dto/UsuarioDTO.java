package com.mperozo.consultorio.api.dto;

import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {
	
	private String nome;
	private String email;
	private String senha;
	private TipoUsuarioEnum tipoUsuario;
}
