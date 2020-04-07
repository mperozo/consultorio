package com.mperozo.consultorio.service;

import com.mperozo.consultorio.model.entity.Paciente;

public interface PacienteService {

	Paciente buscarPorId(Long idPaciente);

	Paciente salvar(Paciente paciente);
}
