package com.mperozo.consultorio.model.enums;

import lombok.Getter;

@Getter
public enum StatusAtendimentoEnum {
	
	AGENDADO ("Agendado", "AGENDADO"),
	REALIZADO ("Realizado", "REALIZADO"),
	CANCELADO ("Cancelado", "CANCELADO");
	
	private String label;
	private String value;
	
	private StatusAtendimentoEnum(String label, String valor) {
		
		this.label = label;
		this.value = valor;
	}
}
