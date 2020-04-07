package com.mperozo.consultorio.service;

import java.util.Optional;

import com.mperozo.consultorio.model.entity.Paciente;

public interface PacienteService {

	Optional<Paciente> buscarPorId(Long idPaciente);
}
