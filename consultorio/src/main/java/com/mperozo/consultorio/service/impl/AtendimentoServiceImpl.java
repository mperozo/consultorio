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
	public Atendimento salvarAtendimento(Atendimento atendimento) {

		validarAtendimento(atendimento);
		atendimento.setStatus(StatusAtendimentoEnum.AGENDADO);
		atendimento.setDataHoraInclusao(LocalDateTime.now());
		//TODO obter o usuário autenticado
		//atendimento.setUsuarioAgendador(usuarioAgendador);
		return atendimentoRepository.save(atendimento);
	}

	protected void validarAtendimento(Atendimento atendimento) {
		
		if(atendimento.getPaciente() == null) {
			throw new BusinessException("Paciente é obrigatório." );
		}
		
		if(atendimento.getMedico() == null) {
			throw new BusinessException("Medico é obrigatório." );
		}
		
		if(atendimento.getUsuarioAgendador() == null) {
			throw new BusinessException("Usuário Cadastrador é obrigatório." );
		}
		
		if(atendimento.getStatus() == null) {
			throw new BusinessException("Status é obrigatório." );
		}
	}

	@Override
	@Transactional
	public Atendimento atualizarAtendimento(Atendimento atendimentoNovo) {
		
		Atendimento atendimentoAntigo = buscarPorId(atendimentoNovo.getId()).get();
		Atendimento atendimentoAtualizado = atualizarAtendimento(atendimentoNovo, atendimentoAntigo);
		
		return atendimentoRepository.saveAndFlush(atendimentoAtualizado);
	}

	private Atendimento atualizarAtendimento(Atendimento atendimentoNovo, Atendimento atendimentoAntigo) {

		validarAlteracaoDeAtendimento(atendimentoNovo, atendimentoAntigo);
		
		atendimentoAntigo.setDataHoraAtendimento(atendimentoNovo.getDataHoraAtendimento());
		atendimentoAntigo.setMedico(atendimentoNovo.getMedico());
		atendimentoAntigo.setPaciente(atendimentoNovo.getPaciente());
		atendimentoAntigo.setUsuarioAgendador(atendimentoNovo.getUsuarioAgendador());
		atendimentoAntigo.setDataHoraAlteracao(LocalDateTime.now());
		
		return atendimentoAntigo;
	}

	private void validarAlteracaoDeAtendimento(Atendimento atendimentoNovo, Atendimento atendimentoAntigo) {

		if(!atendimentoAntigo.getStatus().equals(atendimentoNovo.getStatus())) {
			throw new BusinessException("Não é possível alterar o estado.");
		}
	}

	@Override
	@Transactional
	public Atendimento atualizarStatusAtendimento(Long idAtendimento, StatusAtendimentoEnum novoStatusAtendimento) {
		
		Atendimento atendimento = buscarPorId(idAtendimento).get();
		
		if(novoStatusAtendimento == null) {
			throw new BusinessException("Novo status para atendimento inválido.");
		}
		if(atendimento.getStatus().equals(novoStatusAtendimento)) {
			throw new BusinessException("Novo estado do atendimento não pode ser igual ao estado anterior.");
		}
		
		atendimento.setStatus(novoStatusAtendimento);
		atendimento.setDataHoraAlteracao(LocalDateTime.now());
		
		return atendimentoRepository.saveAndFlush(atendimento);
	}

	@Override
	@Transactional
	public void deletarAtendimento(Atendimento atendimento) {
		Objects.requireNonNull(atendimento.getId());
		atendimentoRepository.delete(atendimento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Atendimento> buscar(Atendimento atendimentoFiltro) {
		Example<Atendimento> example = Example.of(atendimentoFiltro, 
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return atendimentoRepository.findAll(example);
	}

	@Override
	public Optional<Atendimento> buscarPorId(Long id) {
		Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
		
		if(!atendimento.isPresent()) {
			throw new BusinessException("Atendimento não encontrado na base de dados: ID = " + id );
		}
		
		return atendimento;
	}

	@Override
	public void excluir(Long id) {
		atendimentoRepository.deleteById(id);
	}

}
