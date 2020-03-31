package com.mperozo.consultorio.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mperozo.consultorio.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> recuperarUsuarioPorEmail(String email);

}
