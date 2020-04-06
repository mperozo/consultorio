package com.mperozo.consultorio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mperozo.consultorio.api.assembler.AtendimentoDTOAssembler;
import com.mperozo.consultorio.api.dto.AtendimentoDTO;
import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.service.AtendimentoService;

@RestController
@RequestMapping("/api/atendimento")
public class AtendimentoController {

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private AtendimentoDTOAssembler atendimentoDTOAssembler;

	public AtendimentoController(AtendimentoService atendimentoService) {
		this.atendimentoService = atendimentoService;
	}

	@PostMapping("/agendar")
	public ResponseEntity agendarAtendimento(@RequestBody AtendimentoDTO atendimentoDTO) {
		
		try {
			Atendimento atendimento = atendimentoDTOAssembler.toEntity(atendimentoDTO);
			Atendimento atendimentoAgendado = atendimentoService.agendarAtendimento(atendimento);
			
			return new ResponseEntity(atendimentoAgendado, HttpStatus.CREATED); 
		} catch(BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity atualizarAtendimento( @PathVariable("id") Long id, @RequestBody AtendimentoDTO atendimentoDTO ) {
		
		try {
			Atendimento atendimentoComNovosDados = atendimentoDTOAssembler.toEntity(atendimentoDTO);
			Atendimento atendimentoAutalizado = atendimentoService.atualizarAtendimento(atendimentoComNovosDados);
			
			return ResponseEntity.ok(atendimentoAutalizado); 
		} catch(BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
