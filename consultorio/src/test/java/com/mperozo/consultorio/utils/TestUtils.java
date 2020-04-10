package com.mperozo.consultorio.utils;

import java.time.LocalDateTime;

import com.mperozo.consultorio.model.entity.Atendimento;
import com.mperozo.consultorio.model.entity.Paciente;
import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.StatusAtendimentoEnum;
import com.mperozo.consultorio.model.enums.StatusUsuarioEnum;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

public class TestUtils {

	public static final String SENHA_PARA_TESTE = "SENHA1";
	public static final String EMAIL_PARA_TESTE = "emailparateste@email.com.br";
	
	private TestUtils() {}
	
	/* Métodos auxiliares */
	
	public static Atendimento criarAtendimento() {
		
		Usuario medico = criarUsuario("medico@email.com.br", "senha", TipoUsuarioEnum.MEDICO);
		Usuario secretaria = criarUsuario("secretaria@email.com.br", "senha", TipoUsuarioEnum.SECRETARIA);
		Paciente paciente = criarPaciente();
		
		return Atendimento.builder()
				.medico(medico)
				.paciente(paciente)
				.usuarioAgendador(secretaria)
				.dataHoraInclusao(LocalDateTime.now())
				.dataHoraAtendimento(LocalDateTime.now())
				.status(StatusAtendimentoEnum.AGENDADO)
				.build();
	}

	public static Usuario criarUsuario(String email, String senha, TipoUsuarioEnum tipoUsuario) {
		return Usuario.builder()
				.nome("Nome")
				.senha(senha)
				.email(email)
				.tipo(TipoUsuarioEnum.SECRETARIA)
				.status(StatusUsuarioEnum.ATIVO)
				.dataHoraInclusao(LocalDateTime.now())
				.build();
	}
	
	public static Paciente criarPaciente() {
		return Paciente.builder().nome("Nome").build();
	}
}
