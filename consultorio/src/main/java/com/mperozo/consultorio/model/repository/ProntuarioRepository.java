package com.mperozo.consultorio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mperozo.consultorio.model.entity.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
	
}
