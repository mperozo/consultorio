package com.mperozo.consultorio.service;

import java.util.List;

import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	Usuario buscarPorId(Long id);
	
	/**
	 * Verifica se já existe o e-mail na base de dados
	 * 
	 * @param email
	 */
	void verificarSeEmailJaEstaCadastrado(String email);

	List<Usuario> buscarUsuariosPorTipo(TipoUsuarioEnum tipoUsuario);
	
	Usuario obterUsuarioAutenticado();
}
