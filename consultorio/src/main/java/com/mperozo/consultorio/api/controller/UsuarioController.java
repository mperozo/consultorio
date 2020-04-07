package com.mperozo.consultorio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mperozo.consultorio.api.dto.UsuarioDTO;
import com.mperozo.consultorio.exception.AuthenticationException;
import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/incluir")
	public ResponseEntity incluirUsuario(@RequestBody UsuarioDTO usuarioDTO) {

		Usuario usuario = Usuario.builder()
								.nome(usuarioDTO.getNome())
								.email(usuarioDTO.getEmail())
								.senha(usuarioDTO.getSenha()).build();
		
		try {
			Usuario usuarioSalvo = usuarioService.criarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		}catch(BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO usuarioDTO) {
		
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(usuarioDTO.getEmail(), usuarioDTO.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		}catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
