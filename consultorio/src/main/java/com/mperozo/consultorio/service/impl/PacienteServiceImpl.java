package com.mperozo.consultorio.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mperozo.consultorio.model.entity.Paciente;
import com.mperozo.consultorio.model.repository.PacienteRepository;
import com.mperozo.consultorio.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;

	@Override
	public Optional<Paciente> recuperarPorId(Long idPaciente) {
		
		return pacienteRepository.findById(idPaciente);
	}
}
