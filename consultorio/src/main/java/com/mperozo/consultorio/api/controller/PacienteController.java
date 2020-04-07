package com.mperozo.consultorio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mperozo.consultorio.api.assembler.PacienteDTOAssembler;
import com.mperozo.consultorio.api.dto.PacienteDTO;
import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Paciente;
import com.mperozo.consultorio.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/paciente")
@RequiredArgsConstructor
public class PacienteController {

	@Autowired
	private final PacienteService pacienteService;
	
	@Autowired
	private final PacienteDTOAssembler pacienteDTOAssembler;
	
	@PostMapping("/salvar")
	public ResponseEntity salvarPaciente(@RequestBody PacienteDTO pacienteDTO) {
		
		try {
			Paciente paciente = pacienteDTOAssembler.toEntity(pacienteDTO);
			Paciente pacienteSalvo = pacienteService.salvar(paciente);
			
			return new ResponseEntity(pacienteSalvo, HttpStatus.CREATED);
		}catch(BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
