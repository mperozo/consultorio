package com.mperozo.consultorio.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAGAMENTO", schema = "CONSULTORIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "VALOR")
	private BigDecimal valor;

	@Column(name = "PAGAMENTO_EFETUADO")
	private boolean pagamentoEfetuado;
	
	@Column(name = "DATA_HORA_ALTERACAO")
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime dataHoraAlteracao;
}
