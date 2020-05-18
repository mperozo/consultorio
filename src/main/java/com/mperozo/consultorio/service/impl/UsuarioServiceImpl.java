package com.mperozo.consultorio.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mperozo.consultorio.exception.AuthenticationException;
import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.StatusUsuarioEnum;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;
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
	public Usuario salvarUsuario(Usuario usuario) {
		verificarSeEmailJaEstaCadastrado(usuario.getEmail());
		validarUsuarioParaSalvar(usuario);
		usuario.setDataHoraInclusao(LocalDateTime.now());
		usuario.setStatus(StatusUsuarioEnum.ATIVO);
		return usuarioRepository.save(usuario);
	}

	private void validarUsuarioParaSalvar(Usuario usuario) {

		if(usuario.getTipo() == null) {
			throw new BusinessException("Tipo do usuário é obrigatório.");
		}
	}

	@Override
	public void verificarSeEmailJaEstaCadastrado(String email) {
		if (usuarioRepository.existsByEmail(email)) {
			throw new BusinessException("E-mail já cadastrado.");
		}
	}

	@Override
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(!usuario.isPresent()) {
			throw new BusinessException("Usuário não encontrado na base de dados: ID = " + id );
		}
		
		return usuario.get();
	}

	@Override
	public List<Usuario> buscarUsuariosPorTipo(TipoUsuarioEnum tipo) {
		
		List<Usuario> usuarios = usuarioRepository.findByTipo(tipo);
		
		return usuarios;
	}

	public Usuario obterUsuarioAutenticado() {

		//TODO obter o usuario autenticado
		return usuarioRepository.findById(1L).get();
	}

}
