package com.mperozo.consultorio.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mperozo.consultorio.exception.AuthenticationException;
import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.repository.UsuarioRepository;
import com.mperozo.consultorio.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	// TODO criar arquivo de properties
	private static final String USUARIO_OU_SENHA_INVALIDOS = "Usuario ou senha inválidos.";

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {

		// TODO MVP de autenticação. Evoluir futuramente.
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (!usuario.isPresent()) {
			logger.info("Tentativa de autenticação: e-mail " + email + " inexistente.");
			throw new AuthenticationException(USUARIO_OU_SENHA_INVALIDOS);
		}

		if (!usuario.get().getSenha().equals(senha)) {
			logger.info("Tentativa de autenticação: e-mail: " + email + ". Senha digitada inválida.");
			throw new AuthenticationException(USUARIO_OU_SENHA_INVALIDOS);
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario incluirUsuario(Usuario usuario) {
		verificarSeEmailJaEstaCadastrado(usuario.getEmail());
		usuario.setDataHoraInclusao(LocalDateTime.now());
		return usuarioRepository.save(usuario);
	}

	@Override
	public void verificarSeEmailJaEstaCadastrado(String email) {
		if (usuarioRepository.existsByEmail(email)) {
			throw new BusinessException("E-mail já cadastrado.");
		}
	}

	@Override
	public Optional<Usuario> recuperarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(!usuario.isPresent()) {
			throw new BusinessException("Usuário não encontrado na base de dados: ID = " + id );
		}
		
		return usuario;
	}

}
