package com.mperozo.consultorio.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mperozo.consultorio.model.entity.Usuario;
import com.mperozo.consultorio.model.enums.TipoUsuarioEnum;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findById(Long id);
	
	boolean existsByEmail(String email);

	List<Usuario> findByTipo(TipoUsuarioEnum tipo);
}
