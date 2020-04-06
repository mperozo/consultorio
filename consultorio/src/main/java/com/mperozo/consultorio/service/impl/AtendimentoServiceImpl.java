package com.mperozo.consultorio.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;
import com.mperozo.consultorio.model.repository.AtendimentoRepository;
import com.mperozo.consultorio.service.AtendimentoService;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

	private AtendimentoRepository atendimentoRepository;
	
	public AtendimentoServiceImpl(AtendimentoRepository atendimentoRepository) {
		this.atendimentoRepository = atendimentoRepository;
	}
	
	@Override
	@Transactional
	public Atendimento agendarAtendimento(Atendimento atendimento) {

		validarAgendamento(atendimento);
		atendimento.setStatus(StatusAtendimentoEnum.AGENDADO);
		
		return atendimentoRepository.save(atendimento);
	}

	private void validarAgendamento(Atendimento atendimento) {
		
		//TODO
	}

	@Override
	@Transactional
	public Atendimento atualizarAtendimento(Atendimento atendimento) {
		Objects.requireNonNull(atendimento.getId());
		//Optional<Atendimento> atendimentoPersistido = atendimentoRepository.findById(atendimento.getId());
		return atendimentoRepository.save(atendimento);
	}

	@Override
	public Atendimento atualizarStatusAtendimento(Atendimento atendimento, StatusAtendimentoEnum statusAtendimentoEnum) {
		atendimento.setStatus(statusAtendimentoEnum);
		return atualizarAtendimento(atendimento);
	}

	@Override
	@Transactional
	public void removerAtendimento(Atendimento atendimento) {
		Objects.requireNonNull(atendimento.getId());
		atendimentoRepository.delete(atendimento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Atendimento> consultar(Atendimento atendimentoFiltro) {
		Example<Atendimento> example = Example.of(atendimentoFiltro, 
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return atendimentoRepository.findAll(example);
	}

}
