package com.mperozo.consultorio.exception;

public class AuthenticationException extends RuntimeException {

	//deve herdar de business exception
	public AuthenticationException(String mensagem) {
		super(mensagem);
	}
}
