--CREATE SCHEMA consultorio;

CREATE TABLE consultorio.usuario
(
	id bigserial NOT NULL PRIMARY KEY,
	nome character varying(150) NOT NULL,
	email character varying(100) NOT NULL,
	senha character varying(20) NOT NULL,
	status character varying(10) NOT NULL,
	tipo character varying(10) NOT NULL,
	data_hora_inclusao TIMESTAMP DEFAULT now() NOT NULL,
	
	CONSTRAINT usuario_status_check CHECK (status::text = ANY (ARRAY['ATIVO'::character varying, 'INATIVO'::character varying]::text[])),
	CONSTRAINT usuario_tipo_check CHECK (tipo::text = ANY (ARRAY['SECRETARIA'::character varying, 'MEDICO'::character varying]::text[]))
);

CREATE TABLE consultorio.paciente
(
	id bigserial NOT NULL PRIMARY KEY,
	nome character varying(150) NOT NULL,
	data_hora_inclusao TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE consultorio.atendimento
(
	id bigserial NOT NULL PRIMARY KEY,
	id_usuario_agendador bigint references consultorio.usuario(id) NOT NULL,
	id_usuario_medico bigint references consultorio.usuario(id),
	id_paciente bigint references consultorio.paciente(id) NOT NULL,
	status character varying(10) NOT NULL,
	data_hora_inclusao TIMESTAMP NOT NULL DEFAULT now(),
	data_hora_atendimento TIMESTAMP NOT NULL
);