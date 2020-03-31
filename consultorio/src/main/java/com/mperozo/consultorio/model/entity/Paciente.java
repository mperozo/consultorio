package com.mperozo.consultorio.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.Data;

@Entity
@Table(name = "PACIENTE", schema = "CONSULTORIO")
@Data
public class Paciente {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "DATA_INCLUSAO")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataInclusao;

}
