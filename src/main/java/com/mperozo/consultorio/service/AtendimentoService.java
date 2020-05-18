package com.mperozo.consultorio.service;

import java.util.List;
import java.util.Optional;

import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;

public interface AtendimentoService {

	Atendimento salvarAtendimento(Atendimento atendimento);
	
	Atendimento atualizarAtendimento(Atendimento atendimento);
	
	Atendimento atualizarStatusAtendimento(Long idAtendimento, StatusAtendimentoEnum statusAtendimentoEnum);
	
	void deletarAtendimento(Atendimento atendimento);
	
	List<Atendimento> buscar(Atendimento atendimentoFiltro);

	Optional<Atendimento> buscarPorId(Long id);

	void excluir(Long id);
}
