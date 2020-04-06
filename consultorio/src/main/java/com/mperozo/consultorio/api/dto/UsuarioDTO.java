package com.mperozo.consultorio.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {
	
	String nome;
	String email;
	String senha;
}
