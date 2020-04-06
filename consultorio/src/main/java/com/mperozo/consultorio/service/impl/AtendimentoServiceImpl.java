package com.mperozo.consultorio.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

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
	public Atendimento agendarAtendimento(Atendimento atendimento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Atendimento atualizarAtendimento(Atendimento atendimento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Atendimento atualizarStatusAtendimento(Atendimento atendimento, StatusAtendimentoEnum statusAtendimentoEnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerAtendimento(Atendimento atendimento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Atendimento> consultar(Atendimento atendimentoFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

}
