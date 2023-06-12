CREATE TABLE IF NOT EXISTS usuarios
(
    id bigserial,
    login character varying(40) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(90) COLLATE pg_catalog."default" NOT NULL,
    ativo character varying(1) DEFAULT 'S' COLLATE pg_catalog."default",
    data_atualizacao timestamp(6) without time zone DEFAULT current_timestamp,
    data_cadastro timestamp(6) without time zone DEFAULT current_timestamp,
    data_exclusao timestamp(6) without time zone DEFAULT current_timestamp,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS usuario_setores
(
    usuario_id bigint NOT NULL,
    setor character varying(255) COLLATE pg_catalog."default"
);

ALTER TABLE IF EXISTS usuario_setores
    ADD CONSTRAINT fk_usuario_setor FOREIGN KEY (usuario_id)
    REFERENCES usuarios (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;


INSERT INTO public.usuarios(id,login, senha) VALUES (1,'administrador', '$2a$10$Ke56PYFpdqEXK6ooRlcas.dF9GVS56rNYRFkw6KR0ECFzh6H7uFKm');
INSERT INTO public.usuario_setores(usuario_id, setor) VALUES (1,'ADMINISTRACAO');