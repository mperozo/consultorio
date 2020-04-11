package com.mperozo.consultorio.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mperozo.consultorio.exception.BusinessException;
import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;
import com.mperozo.consultorio.model.repository.AtendimentoRepository;
import com.mperozo.consultorio.model.repository.PacienteRepository;
import com.mperozo.consultorio.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class AtendimentoServiceUnitTest {

	@MockBean
	private AtendimentoRepository atendimentoRepositoryMock;

	@MockBean
	private PacienteRepository PacienteRepositoryMock;
	
	@SpyBean
	private AtendimentoServiceImpl atendimentoService;

	@Test
	public void deveSalvarUmAtendimento() {

		Atendimento atendimentoASalvar = TestUtils.criarAtendimento();
		Atendimento expected = TestUtils.criarAtendimento();
		
		doReturn(atendimentoASalvar).when(atendimentoRepositoryMock).save(atendimentoASalvar);
		
		Atendimento result = atendimentoService.salvarAtendimento(atendimentoASalvar);
		
		assertThat(result.getId()).isEqualTo(expected.getId());
		assertThat(result.getStatus()).isEqualTo(expected.getStatus());
	}
	
	@Test
	public void naoDeveSalvarUmAtendimentoQuandoHouverErroDeValidacao() {
		
		Atendimento atendimentoASalvar = TestUtils.criarAtendimento();
		
		doThrow(BusinessException.class).when(atendimentoService).validarAtendimento(atendimentoASalvar);
		
		catchThrowableOfType( () -> atendimentoService.salvarAtendimento(atendimentoASalvar), BusinessException.class);
		
		verify(atendimentoRepositoryMock, never()).save(atendimentoASalvar);
	}
	
	@Test
	public void deveAtualizarUmAtendimento() {

		Usuario medicoAntigo = TestUtils.criarUsuario("MedicoAntigo@email.com.br", "teste", TipoUsuarioEnum.MEDICO);
		Atendimento atendimentoAntigo = TestUtils.criarAtendimento();
		atendimentoAntigo.setMedico(medicoAntigo);

		Usuario medicoNovo = TestUtils.criarUsuario("MedicoNovo@email.com.br", "teste", TipoUsuarioEnum.MEDICO);
		Atendimento atendimentoAtualizado = TestUtils.criarAtendimento();
		atendimentoAtualizado.setMedico(medicoNovo);

		doReturn(Optional.of(atendimentoAntigo)).when(atendimentoRepositoryMock).findById(atendimentoAntigo.getId());
		doReturn(atendimentoAtualizado).when(atendimentoRepositoryMock).saveAndFlush(Mockito.any(Atendimento.class));
		
		atendimentoService.salvarAtendimento(atendimentoAntigo);
		Atendimento result = atendimentoService.atualizarAtendimento(atendimentoAtualizado);
		
		assertThat(result.getId()).isEqualTo(atendimentoAtualizado.getId());
		assertThat(result.getMedico().getEmail()).isEqualTo(atendimentoAtualizado.getMedico().getEmail());
	}
	
	@Test
	public void deveLancarExceptionQuandoTentarAlterarOStatusDeUmAtendimentoPeloMetodoAtualizarAtendimento() {
		
		Atendimento atendimentoAntigo = TestUtils.criarAtendimento();
		doReturn(Optional.of(atendimentoAntigo)).when(atendimentoRepositoryMock).findById(atendimentoAntigo.getId());
		
		Atendimento atendimentoAtualizado = TestUtils.criarAtendimento();
		atendimentoAtualizado.setStatus(StatusAtendimentoEnum.REALIZADO);
		
		Exception exception = assertThrows(BusinessException.class, () -> {
			atendimentoService.atualizarAtendimento(atendimentoAtualizado);
		});

		assertThat(exception)
					.isInstanceOf(BusinessException.class)
					.hasMessage("Não é possível alterar o estado.");
	}
	
	@Test
	public void deveDeletarAtendimento() {
		
		Atendimento atendimento = TestUtils.criarAtendimento();
		atendimento.setId(1L);
		doReturn(Optional.of(atendimento)).when(atendimentoRepositoryMock).findById(atendimento.getId());
		
		atendimentoService.deletarAtendimento(atendimento);
		
		verify(atendimentoRepositoryMock, Mockito.times(1)).delete(atendimento);
	}
	
	@Test
	public void deveLancarExceptionAoTentarExcluirUmAtendimentoSemId() {
		
		Atendimento atendimento = TestUtils.criarAtendimento();
		doReturn(Optional.of(atendimento)).when(atendimentoRepositoryMock).findById(atendimento.getId());
		
		Exception exception = assertThrows(NullPointerException.class, () -> {
			atendimentoService.deletarAtendimento(atendimento);
		});

		assertThat(exception).isInstanceOf(NullPointerException.class);
		
		verify(atendimentoRepositoryMock, Mockito.never()).delete(atendimento);
	}
	
	@Test
	public void deveBuscarAtendimentosPorNomeDoPaciente() {

		Atendimento atendimento = TestUtils.criarAtendimento();
		atendimento.setId(1L);
		
		List<Atendimento> atendimentos = Arrays.asList(atendimento);
		when(atendimentoRepositoryMock.findAll(Mockito.any(Example.class))).thenReturn(atendimentos);
		
		List<Atendimento> result = atendimentoService.buscar(atendimento);

		assertThat(result)
			.isNotEmpty()
			.hasSize(1)
			.contains(atendimento);
	}
	
	@Test
	public void deveAtualizarStatusDeUmLancamento() {
		
		Atendimento atendimentoAntigo = TestUtils.criarAtendimento();
		doReturn(Optional.of(atendimentoAntigo)).when(atendimentoRepositoryMock).findById(atendimentoAntigo.getId());
		
		atendimentoService.atualizarStatusAtendimento(atendimentoAntigo.getId(), StatusAtendimentoEnum.REALIZADO);
		
		verify(atendimentoRepositoryMock, Mockito.times(1)).saveAndFlush(Mockito.any(Atendimento.class));
	}
	
	@Test
	public void deveLancarExceptionAoTentarAtualizarStatusDeUmLancamentoComOStatusIgualAoAntigo() {
		
		Atendimento atendimentoAntigo = TestUtils.criarAtendimento();
		doReturn(Optional.of(atendimentoAntigo)).when(atendimentoRepositoryMock).findById(atendimentoAntigo.getId());
		
		Exception exception = assertThrows(BusinessException.class, () -> {
			atendimentoService.atualizarStatusAtendimento(atendimentoAntigo.getId(), StatusAtendimentoEnum.AGENDADO);
		});

		assertThat(exception)
			.isInstanceOf(BusinessException.class)
			.hasMessage("Novo estado do atendimento não pode ser igual ao estado anterior.");
		
		verify(atendimentoRepositoryMock, Mockito.never()).saveAndFlush(Mockito.any(Atendimento.class));
	}
		

}
