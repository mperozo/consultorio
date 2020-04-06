package com.mperozo.consultorio.service;

import java.util.List;
import java.util.Optional;

import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;

public interface AtendimentoService {

	Atendimento agendarAtendimento(Atendimento atendimento);
	
	Atendimento atualizarAtendimento(Atendimento atendimento);
	
	Atendimento atualizarStatusAtendimento(Atendimento atendimento, StatusAtendimentoEnum statusAtendimentoEnum);
	
	void removerAtendimento(Atendimento atendimento);
	
	List<Atendimento> consultar(Atendimento atendimentoFiltro);

	Optional<Atendimento> recuperarPorId(Long id);
}
