package com.mperozo.consultorio.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Paciente;
import com.mperozo.consultorio.model.repository.PacienteRepository;
import com.mperozo.consultorio.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;

	@Override
	public Paciente buscarPorId(Long idPaciente) {
		Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);

		if (!paciente.isPresent()) {
			throw new BusinessException("Paciente não encontrado na base de dados: ID = " + idPaciente);
		}

		return paciente.get();
	}

	@Override
	@Transactional
	public Paciente salvar(Paciente paciente) {
		
		paciente.setDataHoraInclusao(LocalDateTime.now());
		return pacienteRepository.save(paciente);
	}
}
