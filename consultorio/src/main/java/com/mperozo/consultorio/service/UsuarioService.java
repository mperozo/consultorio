package com.mperozo.consultorio.service;

import java.util.Optional;

import com.mperozo.consultorio.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario incluirUsuario(Usuario usuario);
	
	Optional<Usuario> recuperarPorId(Long id);
	
	/**
	 * Verifica se já existe o e-mail na base de dados
	 * 
	 * @param email
	 */
	void verificarSeEmailJaEstaCadastrado(String email);
}
