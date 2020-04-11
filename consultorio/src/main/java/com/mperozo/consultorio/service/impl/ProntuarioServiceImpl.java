package com.mperozo.consultorio.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mperozo.consultorio.model.entity.Paciente;
import com.mperozo.consultorio.model.entity.Prontuario;
import com.mperozo.consultorio.model.repository.ProntuarioRepository;
import com.mperozo.consultorio.service.ProntuarioService;

@Service
public class ProntuarioServiceImpl implements ProntuarioService {

	@Autowired
	ProntuarioRepository prontuarioRepository;

	@Override
	@Transactional
	public Prontuario criarProntuario() {
		
		Prontuario prontuario = Prontuario.builder()
				.dataHoraInclusao(LocalDateTime.now())
				.numeroProntuario(geradorNumeroProntuario())
				.build();
		
		return prontuarioRepository.save(prontuario);
	}

	private Integer geradorNumeroProntuario() {
		// TODO gerar
		return (int) Math.random();
	}
}
