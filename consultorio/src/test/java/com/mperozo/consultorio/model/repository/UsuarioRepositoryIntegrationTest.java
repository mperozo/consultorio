package com.mperozo.consultorio.model.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.StatusUsuarioEnum;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryIntegrationTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void deveVerificarExistenciaDeUmEmail() {

		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		boolean result = usuarioRepository.existsByEmail("marcos@email.com.br");

		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {

		boolean result = usuarioRepository.existsByEmail("marcos@email.com.br");

		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		
		Usuario usuario = criarUsuario();
		
		Usuario usuarioSalvo = entityManager.persist(usuario);
		
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		
		Usuario usuario = criarUsuario();

		Usuario usuarioSalvo = entityManager.persist(usuario);
		Optional<Usuario> result = usuarioRepository.findByEmail("marcos@email.com.br");

		Assertions.assertThat(result.isPresent()).isTrue();
		Assertions.assertThat(usuarioSalvo).isEqualToComparingFieldByField(result.get());
	}
	
	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
		
		Optional<Usuario> result = usuarioRepository.findByEmail("marcos@email.com.br");
		Assertions.assertThat(result.isPresent()).isFalse();
	}
	
	/* Métodos auxiliares */
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.nome("Marcos")
				.senha("senha")
				.email("marcos@email.com.br")
				.tipo(TipoUsuarioEnum.SECRETARIA)
				.status(StatusUsuarioEnum.ATIVO)
				.dataHoraInclusao(LocalDateTime.now())
				.build();
	}
	
}
