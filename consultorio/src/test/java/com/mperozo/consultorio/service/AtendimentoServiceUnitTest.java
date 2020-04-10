package com.mperozo.consultorio.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.repository.AtendimentoRepository;
import com.mperozo.consultorio.service.impl.AtendimentoServiceImpl;
import com.mperozo.consultorio.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class AtendimentoServiceUnitTest {

	@MockBean
	private AtendimentoRepository atendimentoRepositoryMock;

	@SpyBean
	private AtendimentoServiceImpl atendimentoService;

	@Test
	public void deveSalvarUmAtendimento() {

		Atendimento atendimentoASalvar = TestUtils.criarAtendimento();
		
		Atendimento expected = TestUtils.criarAtendimento();
		
		Mockito.when(atendimentoRepositoryMock.save(atendimentoASalvar)).thenReturn(atendimentoASalvar);
		
		Atendimento result = atendimentoService.salvarAtendimento(atendimentoASalvar);
		
		Assertions.assertThat(result.getId()).isEqualTo(expected.getId());
		Assertions.assertThat(result.getStatus()).isEqualTo(expected.getStatus());
	}

}
