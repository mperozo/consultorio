package com.mperozo.consultorio.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mperozo.consultorio.model.entity.Usuario;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryIntegrationTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	public void verificarExistenciaDeUmEmail() {

		Usuario usuario = Usuario.builder().nome("Marcos").email("marcos@email.com.br").build();
		usuarioRepository.save(usuario);

		boolean result = usuarioRepository.existsByEmail("marcos@email.com.br");

		Assertions.assertThat(result).isTrue();
	}
}
