package com.mperozo.consultorio.api.dto;

import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private TipoUsuarioEnum tipoUsuario;
}
