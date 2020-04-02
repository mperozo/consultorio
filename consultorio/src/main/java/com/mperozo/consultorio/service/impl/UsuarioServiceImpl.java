package com.mperozo.consultorio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.repository.UsuarioRepository;
import com.mperozo.consultorio.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
	// super();
	// this.usuarioRepository = usuarioRepository;
	// }

	@Override
	public Usuario autenticar(String email, String senha) {
		return null;
	}

	@Override
	public Usuario incluirUsuario(Usuario usuario) {
		verificarSeEmailJaEstaCadastrado(usuario.getEmail());
		return usuarioRepository.save(usuario);
	}

	@Override
	public void verificarSeEmailJaEstaCadastrado(String email) {
		if(usuarioRepository.existsByEmail(email)) {
			throw new BusinessException("E-mail já cadastrado.");
		}
	}

}
