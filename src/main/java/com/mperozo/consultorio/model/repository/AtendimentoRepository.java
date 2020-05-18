package com.mperozo.consultorio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mperozo.consultorio.model.entity.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
	
	/*
	
	@Query(value = 
			" select from Atendimento a join a.usuario u "
		+	" where u.tipo = :tipo group by a")
	LinkedList<Atendimento> buscarAtendimentosPorTipoUsuario(@Param("tipo") TipoUsuarioEnum tipo);

	 */
}
