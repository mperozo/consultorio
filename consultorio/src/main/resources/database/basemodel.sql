CREATE SCHEMA consultorio;

CREATE TABLE consultorio.usuario
(
	id bigserial NOT NULL PRIMARY KEY,
	nome character varying(150),
	email character varying(100),
	senha character varying(20),
	status character varying(10),
	tipo character varying(10),
	data_inclusao date DEFAULT now(),
	
	CONSTRAINT usuario_status_check CHECK (status::text = ANY (ARRAY['ATIVO'::character varying, 'INATIVO'::character varying]::text[])),
	CONSTRAINT usuario_tipo_check CHECK (tipo::text = ANY (ARRAY['SECRETARIA'::character varying, 'MEDICO'::character varying]::text[]))
);

CREATE TABLE consultorio.paciente
(
	id bigserial NOT NULL PRIMARY KEY,
	nome character varying(150),
	data_inclusao date DEFAULT now()
);

CREATE TABLE consultorio.atendimento
(
	id bigserial NOT NULL PRIMARY KEY,
	id_usuario_agendador bigint references consultorio.usuario(id) NOT NULL,
	id_usuario_medico bigint references consultorio.usuario(id) NOT NULL,
	id_usuario_paciente bigint references consultorio.paciente(id) NOT NULL,
	data_criacao date DEFAULT now()
);