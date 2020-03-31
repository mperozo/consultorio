package com.mperozo.consultorio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mperozo.consultorio.model.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
