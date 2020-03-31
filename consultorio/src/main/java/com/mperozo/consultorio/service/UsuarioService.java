package com.mperozo.consultorio.service;

import com.mperozo.consultorio.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario incluirUsuario(Usuario usuario);
	
	void validarEmail(String email);
}
