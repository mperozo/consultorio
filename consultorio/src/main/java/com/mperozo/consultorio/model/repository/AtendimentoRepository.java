package com.mperozo.consultorio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mperozo.consultorio.model.entity.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

}
