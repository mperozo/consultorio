package com.mperozo.consultorio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mperozo.consultorio.api.assembler.UsuarioDTOAssembler;
import com.mperozo.consultorio.api.dto.UsuarioDTO;
import com.mperozo.consultorio.exception.AuthenticationException;
import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;
import com.mperozo.consultorio.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	@Autowired
	private final UsuarioService usuarioService;
	
	@Autowired
	private final UsuarioDTOAssembler usuarioDTOAssembler;
	
	@PostMapping("/salvar")
	public ResponseEntity salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

		Usuario usuario = usuarioDTOAssembler.toEntity(usuarioDTO);
		
		try {
			Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
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
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity buscarUsuario(@PathVariable("id") Long id) {
		
		Usuario usuario = usuarioService.buscarPorId(id);
		
		if(usuario == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/buscar-por-tipo")
	public ResponseEntity buscarUsuariosPorTipo(@RequestParam(value = "tipo", required = true) TipoUsuarioEnum tipo) {
		
		List<UsuarioDTO> usuariosDTOList = usuarioDTOAssembler.toDTOList(usuarioService.buscarUsuariosPorTipo(tipo));
		
		return ResponseEntity.ok(usuariosDTOList);
	}
	
}
