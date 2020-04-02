package com.mperozo.consultorio.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceIntegrationTest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void deveValidarQueEmailAindaNaoFoiCadastrado() {
		
		usuarioRepository.deleteAll();
		usuarioService.verificarSeEmailJaEstaCadastrado("emailqueaindanaofoicadastrado@email.com.br");
	}
	
	@Test
	public void deveValidarQueEmailJaFoiCadastradoELancarException() {
		
		Usuario usuario = Usuario.builder().email("emailjacadastrado@email.com.br").build();
		usuarioRepository.save(usuario);
		
		Exception exception = assertThrows(BusinessException.class, () -> {
			usuarioService.verificarSeEmailJaEstaCadastrado("emailjacadastrado@email.com.br");
	    });
	 
	    String expectedMessage = "E-mail já cadastrado.";
	    String actualMessage = exception.getMessage();
	 
	    Assertions.assertThat(actualMessage).isEqualTo(expectedMessage);
	}

}
