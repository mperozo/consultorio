package com.mperozo.consultorio.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;
import com.mperozo.consultorio.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AtendimentoRepositoryIntegrationTest {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmAtendimento() {
		Atendimento atendimento = TestUtils.criarAtendimento();
		
		Atendimento result = atendimentoRepository.save(atendimento);
		
		assertThat(result).isEqualToComparingFieldByField(atendimento);
	}
	
	@Test
	public void deveDeletarUmAtendimento() {
		Atendimento atendimento = criarEPersistirUmAtendimento();
		atendimento = entityManager.find(Atendimento.class, atendimento.getId());
		
		assertThat(atendimento).isNotNull();
		
		atendimentoRepository.delete(atendimento);
		
		Atendimento atendimentoInexistente = entityManager.find(Atendimento.class, atendimento.getId());
		
		assertThat(atendimentoInexistente).isNull();
	}

	@Test
	public void deveAtualizarUmAtendimento() {
		Atendimento atendimento = criarEPersistirUmAtendimento();
		
		Usuario novoMedico = TestUtils.criarUsuario("medico2@email.com.br", "senha", TipoUsuarioEnum.MEDICO);
		
		atendimento.setMedico(novoMedico);
		
		atendimentoRepository.save(atendimento);
		
		Atendimento atendimentoAtualizado = entityManager.find(Atendimento.class, atendimento.getId());
		
		assertThat(atendimentoAtualizado.getId()).isEqualTo(atendimento.getId());
		assertThat(atendimentoAtualizado.getMedico().getEmail()).isEqualTo(novoMedico.getEmail());
	}
	
	@Test
	public void deveBuscarUmAtendimentoPorId() {
		Atendimento atendimento = criarEPersistirUmAtendimento();
		
		Optional<Atendimento> atendimentoBuscado = atendimentoRepository.findById(atendimento.getId());
		
		assertThat(atendimentoBuscado.get()).isEqualToComparingFieldByField(atendimento);
	}

	/* Métodos auxiliares */ 
	
	private Atendimento criarEPersistirUmAtendimento() {
		Atendimento atendimento = TestUtils.criarAtendimento();
		entityManager.persist(atendimento);
		return atendimento;
	}
	

}
