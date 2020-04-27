package com.mperozo.consultorio.service;

import java.util.List;

import com.mperozo.consultorio.model.entity.Paciente;

public interface PacienteService {

	Paciente buscarPorId(Long idPaciente);

	Paciente salvar(Paciente paciente);

	List<Paciente> buscarTodosPacientes();
}
