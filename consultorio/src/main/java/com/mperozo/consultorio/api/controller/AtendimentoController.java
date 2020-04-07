package com.mperozo.consultorio.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mperozo.consultorio.api.assembler.AtendimentoDTOAssembler;
import com.mperozo.consultorio.api.dto.AtendimentoDTO;
import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;
import com.mperozo.consultorio.service.AtendimentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/atendimento")
@RequiredArgsConstructor
public class AtendimentoController {

	@Autowired
	private final AtendimentoService atendimentoService;

	@Autowired
	private final AtendimentoDTOAssembler atendimentoDTOAssembler;

	@PostMapping("/salvar")
	public ResponseEntity salvarAtendimento(@RequestBody AtendimentoDTO atendimentoDTO) {
		
		try {
			Atendimento atendimento = atendimentoDTOAssembler.toEntity(atendimentoDTO);
			Atendimento atendimentoAgendado = atendimentoService.salvarAtendimento(atendimento);
			
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
	
	@DeleteMapping("{id}")
	public ResponseEntity deletarAtendimento( @PathVariable("id") Long id) {
		
		try {
			atendimentoService.excluir(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
		} catch(BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity buscar( 
			@RequestParam(value = "idMedico", required = false) Long idMedico,
			@RequestParam(value = "idPaciente", required = false) Long idPaciente,
			@RequestParam(value = "idUsuarioAgendador", required = false) Long idUsuarioAgendador,
			@RequestParam(value = "dataHoraAtendimento", required = false) LocalDateTime dataHoraAtendimento,
			@RequestParam(value = "statusAtendimento", required = false) StatusAtendimentoEnum statusAtendimento
			) {
		
		AtendimentoDTO atendimentoDTOFilter = AtendimentoDTO.builder()
				.idMedico(idMedico)
				.idPaciente(idPaciente)
				.idUsuarioAgendador(idUsuarioAgendador)
				.dataHoraAtendimento(dataHoraAtendimento)
				.statusAtendimento(statusAtendimento).build();
		
		Atendimento atendimentoFiltro = atendimentoDTOAssembler.toEntity(atendimentoDTOFilter);
		
		List<Atendimento> atendimentos = atendimentoService.buscar(atendimentoFiltro);
		
		return ResponseEntity.ok(atendimentos);
	}
	
}
