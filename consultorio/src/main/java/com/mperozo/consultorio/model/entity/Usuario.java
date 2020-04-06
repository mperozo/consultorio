package com.mperozo.consultorio.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.mperozo.consultorio.model.enums.StatusUsuarioEnum;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIO", schema = "CONSULTORIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME")
	@NotBlank(message = "Nome é obrigatório.")
	private String nome;

	@Column(name = "TIPO")
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Tipo é obrigatório.")
	private TipoUsuarioEnum tipo;

	@Column(name = "STATUS")
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "Status é obrigatório.")
	private StatusUsuarioEnum status;

	@Column(name = "EMAIL")
	@NotBlank(message = "E-mail é obrigatório.")
	private String email;

	@Column(name = "SENHA")
	@NotBlank(message = "Senha é obrigatória.")
	private String senha;

	@Column(name = "DATA_HORA_INCLUSAO")
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	@NotNull(message = "Data de inclusão é obrigatória.")
	private LocalDateTime dataHoraInclusao;

}
