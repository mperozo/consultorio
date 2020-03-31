package com.mperozo.consultorio.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.mperozo.consultorio.model.enums.StatusUsuarioEnum;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "USUARIO", schema = "CONSULTORIO")
@Data
@Builder
public class Usuario {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "TIPO")
	@Enumerated(value = EnumType.STRING)
	private TipoUsuarioEnum tipo;

	@Column(name = "STATUS")
	@Enumerated(value = EnumType.STRING)
	private StatusUsuarioEnum status;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "SENHA")
	private String senha;

	@Column(name = "DATA_INCLUSAO")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataInclusao;

}
