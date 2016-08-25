
CREATE TABLE cor (
    codigo integer NOT NULL,
    cor character varying(255)
);


ALTER TABLE public.cor OWNER TO postgres;

--
-- Name: fabricante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fabricante (
    codigo integer NOT NULL,
    fabricante character varying(255)
);


ALTER TABLE public.fabricante OWNER TO postgres;

--
-- Name: foto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE foto (
    codigo integer NOT NULL,
    path character varying(255),
    codigo_veiculo integer
);


ALTER TABLE public.foto OWNER TO postgres;

--
-- Name: grupo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grupo (
    codigo integer NOT NULL,
    grupo character varying(255)
);


ALTER TABLE public.grupo OWNER TO postgres;




CREATE TABLE modelo (
    codigo integer NOT NULL,
    modelo character varying(255),
    codigo_fabricante integer
);


ALTER TABLE public.modelo OWNER TO postgres;

--
-- Name: ocorrencia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ocorrencia (
    codigo integer NOT NULL,
    data date,
    ocorrencia character varying(255),
    codigo_veiculo integer
);


ALTER TABLE public.ocorrencia OWNER TO postgres;

--
-- Name: pericia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pericia (
    codigo integer NOT NULL,
    pericia character varying(255)
);


ALTER TABLE public.pericia OWNER TO postgres;

--
-- Name: pessoa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pessoa (
    codigo integer NOT NULL,
    login character varying(255),
    senha character varying(255),
    codigo_grupo integer
);


ALTER TABLE public.pessoa OWNER TO postgres;

--
-- Name: seguro; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE seguro (
    codigo integer NOT NULL,
    seguro character varying(255)
);


ALTER TABLE public.seguro OWNER TO postgres;

--
-- Name: tipo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo (
    codigo integer NOT NULL,
    tipo character varying(255)
);


ALTER TABLE public.tipo OWNER TO postgres;

--
-- Name: veiculo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE veiculo (
    codigo integer NOT NULL,
    anofabricacao character varying(255),
    chassi character varying(255),
    chave character varying(255),
    comentarios character varying(255),
    condicao character varying(255),
    date4 date,
    dossie character varying(255),
    ip_processo character varying(255),
    localatual character varying(255),
    motivoapreensao character varying(255),
    motor character varying(255),
    numero_ocorrencia character varying(255),
    obs character varying(255),
    placa character varying(255),
    placaoriginal character varying(255),
    proprietario character varying(255),
    situacao character varying(255),
    codigo_cor integer,
    codigo_fabricante integer,
    codigo_modelo integer,
    codigo_pericia integer,
    codigo_seguro integer,
    codigo_tipo integer
);


ALTER TABLE public.veiculo OWNER TO postgres;


--
-- Name: cor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cor
    ADD CONSTRAINT cor_pkey PRIMARY KEY (codigo);


--
-- Name: fabricante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fabricante
    ADD CONSTRAINT fabricante_pkey PRIMARY KEY (codigo);


--
-- Name: foto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY foto
    ADD CONSTRAINT foto_pkey PRIMARY KEY (codigo);


--
-- Name: grupo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grupo
    ADD CONSTRAINT grupo_pkey PRIMARY KEY (codigo);


--
-- Name: modelo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY modelo
    ADD CONSTRAINT modelo_pkey PRIMARY KEY (codigo);


--
-- Name: ocorrencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ocorrencia
    ADD CONSTRAINT ocorrencia_pkey PRIMARY KEY (codigo);


--
-- Name: pericia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pericia
    ADD CONSTRAINT pericia_pkey PRIMARY KEY (codigo);


--
-- Name: pessoa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (codigo);


--
-- Name: seguro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY seguro
    ADD CONSTRAINT seguro_pkey PRIMARY KEY (codigo);


--
-- Name: tipo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo
    ADD CONSTRAINT tipo_pkey PRIMARY KEY (codigo);


--
-- Name: veiculo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT veiculo_pkey PRIMARY KEY (codigo);


--
-- Name: fk_5gfn754ps5dcdncq31r08h8kn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT fk_5gfn754ps5dcdncq31r08h8kn FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo);


--
-- Name: fk_64pdyy3s3gql1wwk5g78drg2h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT fk_64pdyy3s3gql1wwk5g78drg2h FOREIGN KEY (codigo_modelo) REFERENCES modelo(codigo);


--
-- Name: fk_6fgclxtlwrj2aqx2af249omh8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foto
    ADD CONSTRAINT fk_6fgclxtlwrj2aqx2af249omh8 FOREIGN KEY (codigo_veiculo) REFERENCES veiculo(codigo);


--
-- Name: fk_75ku1h66efdorsd9xc9l1bgbr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT fk_75ku1h66efdorsd9xc9l1bgbr FOREIGN KEY (codigo_cor) REFERENCES cor(codigo);


--
-- Name: fk_7b5s7lmuvj3nlrrghmxhyhvem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT fk_7b5s7lmuvj3nlrrghmxhyhvem FOREIGN KEY (codigo_tipo) REFERENCES tipo(codigo);


--
-- Name: fk_h8ww3xc42ycdigyc9hb4i62mw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY modelo
    ADD CONSTRAINT fk_h8ww3xc42ycdigyc9hb4i62mw FOREIGN KEY (codigo_fabricante) REFERENCES fabricante(codigo);


--
-- Name: fk_i9b00q9okx27q11vv5sx0s096; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT fk_i9b00q9okx27q11vv5sx0s096 FOREIGN KEY (codigo_fabricante) REFERENCES fabricante(codigo);


--
-- Name: fk_m7indq1qiiwrd0xgf9o4gn7uo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT fk_m7indq1qiiwrd0xgf9o4gn7uo FOREIGN KEY (codigo_seguro) REFERENCES seguro(codigo);


--
-- Name: fk_rojnklrx8d0c4l8fglj6oksn9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ocorrencia
    ADD CONSTRAINT fk_rojnklrx8d0c4l8fglj6oksn9 FOREIGN KEY (codigo_veiculo) REFERENCES veiculo(codigo);


--
-- Name: fk_ti4dvdfihfgpv0jpwwe45fqcs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT fk_ti4dvdfihfgpv0jpwwe45fqcs FOREIGN KEY (codigo_pericia) REFERENCES pericia(codigo);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

