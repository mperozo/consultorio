package com.mperozo.consultorio.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mperozo.consultorio.exception.BusinessException;
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
		atendimento.setDataHoraInclusao(LocalDateTime.now());
		//TODO obter o usuário autenticado
		//atendimento.setUsuarioAgendador(usuarioAgendador);
		
		return atendimentoRepository.save(atendimento);
	}

	private void validarAgendamento(Atendimento atendimento) {
		
		//TODO
	}

	@Override
	@Transactional
	public Atendimento atualizarAtendimento(Atendimento atendimentoNovo) {
		
		Atendimento atendimentoAntigo = recuperarPorId(atendimentoNovo.getId()).get();
		Atendimento atendimentoAtualizado = atualizarAtendimento(atendimentoNovo, atendimentoAntigo);
		
		return atendimentoRepository.saveAndFlush(atendimentoAtualizado);
	}

	private Atendimento atualizarAtendimento(Atendimento atendimentoNovo, Atendimento atendimentoAntigo) {

		validarAlteracaoDeAtendimento(atendimentoNovo, atendimentoAntigo);
		
		atendimentoAntigo.setDataHoraAgendamento(atendimentoNovo.getDataHoraAgendamento());
		atendimentoAntigo.setMedico(atendimentoNovo.getMedico());
		atendimentoAntigo.setPaciente(atendimentoNovo.getPaciente());
		atendimentoAntigo.setUsuarioAgendador(atendimentoNovo.getUsuarioAgendador());
		
		return atendimentoAntigo;
	}

	// TODO criar testes unitários
	private void validarAlteracaoDeAtendimento(Atendimento atendimentoNovo, Atendimento atendimentoAntigo) {

		if(!atendimentoAntigo.getStatus().equals(atendimentoNovo.getStatus())) {
			throw new BusinessException("Não é possível alterar o estado.");
		}
		
		if(!atendimentoAntigo.getDataHoraInclusao().equals(atendimentoNovo.getDataHoraInclusao())) {
			throw new BusinessException("Não é possível alterar a data e hora de inclusão.");
		}
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

	@Override
	public Optional<Atendimento> recuperarPorId(Long id) {
		Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
		
		if(!atendimento.isPresent()) {
			throw new BusinessException("Atendimento não encontrado na base de dados: ID = " + id );
		}
		
		return atendimento;
	}

}
