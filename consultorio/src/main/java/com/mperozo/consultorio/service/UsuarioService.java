package com.mperozo.consultorio.service;

import com.mperozo.consultorio.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario incluirUsuario(Usuario usuario);
	
	/**
	 * Verifica se já existe o e-mail na base de dados
	 * 
	 * @param email
	 */
	void verificarSeEmailJaEstaCadastrado(String email);
}
