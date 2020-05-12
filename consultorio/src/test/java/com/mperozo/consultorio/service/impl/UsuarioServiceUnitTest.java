package com.mperozo.consultorio.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mperozo.consultorio.exception.AuthenticationException;
import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;
import com.mperozo.consultorio.model.repository.UsuarioRepository;
import com.mperozo.consultorio.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceUnitTest {

	@MockBean
	private UsuarioRepository usuarioRepositoryMock;

	@SpyBean
	private UsuarioServiceImpl usuarioService;

	@Test
	public void deveValidarQueEmailAindaNaoFoiCadastrado() {

		lenient().when(usuarioRepositoryMock.existsByEmail(anyString())).thenReturn(false);
		usuarioService.verificarSeEmailJaEstaCadastrado(TestUtils.EMAIL_PARA_TESTE);
	}

	@Test
	public void deveValidarQueEmailJaFoiCadastradoELancarException() {

		lenient().when(usuarioRepositoryMock.existsByEmail(TestUtils.EMAIL_PARA_TESTE)).thenReturn(true);

		Exception exception = assertThrows(BusinessException.class, () -> {
			usuarioService.verificarSeEmailJaEstaCadastrado(TestUtils.EMAIL_PARA_TESTE);
		});

		assertThat(exception)
					.isInstanceOf(BusinessException.class)
					.hasMessage("E-mail já cadastrado.");
		
	}

	@Test
	public void deveAutenticarUmUsuarioComSucesso() {

		Usuario usuario = criarUsuario("Marcos", TestUtils.EMAIL_PARA_TESTE, TestUtils.SENHA_PARA_TESTE);
		lenient().when(usuarioRepositoryMock.findByEmail(TestUtils.EMAIL_PARA_TESTE)).thenReturn(Optional.of(usuario));

		Usuario usuarioAutenticado = usuarioService.autenticar(TestUtils.EMAIL_PARA_TESTE, TestUtils.SENHA_PARA_TESTE);

		assertThat(usuarioAutenticado).isEqualToComparingFieldByField(usuarioAutenticado);
	}
	
	@Test
	public void deveLancarExceptionAoNaoEncontrarUsuarioPeloEmail() {

		lenient().when(usuarioRepositoryMock.findByEmail(anyString())).thenReturn(Optional.empty());

		Exception exception = assertThrows(AuthenticationException.class, () -> {
			usuarioService.autenticar(TestUtils.EMAIL_PARA_TESTE, TestUtils.SENHA_PARA_TESTE);
		});

		assertThat(exception)
					.isInstanceOf(AuthenticationException.class)
					.hasMessage("Usuario ou senha inválidos.");
	}
	
	@Test
	public void deveLancarExceptionAoVerificarQueASenhaNaoEstaCorreta() {

		Usuario usuario = criarUsuario("Marcos", TestUtils.EMAIL_PARA_TESTE, TestUtils.SENHA_PARA_TESTE);
		lenient().when(usuarioRepositoryMock.findByEmail(TestUtils.EMAIL_PARA_TESTE)).thenReturn(Optional.of(usuario));

		Exception exception = assertThrows(AuthenticationException.class, () -> {
			usuarioService.autenticar(TestUtils.EMAIL_PARA_TESTE, "SENHA2");
		});

		assertThat(exception)
					.isInstanceOf(AuthenticationException.class)
					.hasMessage("Usuario ou senha inválidos.");
	}
	
	@Test
	public void deveIncluirUsuarioComSucesso() {
		
		Usuario usuario = TestUtils.criarUsuario(TestUtils.EMAIL_PARA_TESTE, TestUtils.SENHA_PARA_TESTE, TipoUsuarioEnum.MEDICO);
		lenient().doNothing().when(usuarioService).verificarSeEmailJaEstaCadastrado(TestUtils.EMAIL_PARA_TESTE);
		lenient().when(usuarioRepositoryMock.save(usuario)).thenReturn(usuario);
		
		Usuario result = usuarioService.salvarUsuario(usuario);
		
		assertThat(result).isEqualToComparingFieldByField(usuario);
	}
	
	@Test
	public void deveLancarExceptionAoTentarSalvarUsuarioComEmailJaCadastrado() {
		
		Usuario usuario = criarUsuario("Marcos", TestUtils.EMAIL_PARA_TESTE, TestUtils.SENHA_PARA_TESTE);
		lenient().doThrow(BusinessException.class).when(usuarioService).verificarSeEmailJaEstaCadastrado(TestUtils.EMAIL_PARA_TESTE);
		
		assertThrows(BusinessException.class, () -> {
			usuarioService.salvarUsuario(usuario);
		});
		
		verify(usuarioRepositoryMock, never()).save(usuario);
	}

	/* Métodos auxiliares */

	public static Usuario criarUsuario(String nome, String email, String senha) {
		return Usuario.builder()
				.nome(nome)
				.senha(senha)
				.email(email)
				.build();
	}

}
