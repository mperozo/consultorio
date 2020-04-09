package com.mperozo.consultorio.api.dto;

import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private String nome;
	private String email;
	private String senha;
	private TipoUsuarioEnum tipoUsuario;
}
