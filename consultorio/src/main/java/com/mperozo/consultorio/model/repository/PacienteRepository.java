package com.mperozo.consultorio.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mperozo.consultorio.model.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	Optional<Paciente> findById(Long id);
}
