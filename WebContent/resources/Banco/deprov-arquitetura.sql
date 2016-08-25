


CREATE TABLE cor (
    codigo integer NOT NULL,
    cor character varying(255),
    PRIMARY KEY (codigo)
);


CREATE TABLE fabricante (
    codigo integer NOT NULL,
    fabricante character varying(255),
    PRIMARY KEY (codigo)
);


CREATE TABLE foto (
    codigo integer NOT NULL,
    path character varying(255),
    codigo_veiculo integer,
    PRIMARY KEY (codigo),
    FOREIGN KEY (codigo_veiculo) REFERENCES veiculo(codigo)
);


CREATE TABLE grupo (
    codigo integer NOT NULL,
    grupo character varying(255),
    PRIMARY KEY (codigo)
);



CREATE TABLE modelo (
    codigo integer NOT NULL,
    modelo character varying(255),
    codigo_fabricante integer,
    PRIMARY KEY (codigo),
    FOREIGN KEY (codigo_fabricante) REFERENCES fabricante(codigo)
);



CREATE TABLE ocorrencia (
    codigo integer NOT NULL,
    data date,
    ocorrencia character varying(255),
    codigo_veiculo integer,
    PRIMARY KEY (codigo),
    FOREIGN KEY (codigo_veiculo) REFERENCES veiculo(codigo)

);



CREATE TABLE pericia (
    codigo integer NOT NULL,
    pericia character varying(255),
    PRIMARY KEY (codigo)
);


CREATE TABLE pessoa (
    codigo integer NOT NULL,
    login character varying(255),
    senha character varying(255),
    codigo_grupo integer,
    PRIMARY KEY (codigo),
    FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo)

);


CREATE TABLE seguro (
    codigo integer NOT NULL,
    seguro character varying(255),
    PRIMARY KEY (codigo)
);


CREATE TABLE tipo (
    codigo integer NOT NULL,
    tipo character varying(255),
    PRIMARY KEY (codigo)
);


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
    codigo_tipo integer,
    PRIMARY KEY (codigo),
    FOREIGN KEY (codigo_cor) REFERENCES cor(codigo),
    FOREIGN KEY (codigo_fabricante) REFERENCES fabricante(codigo),
    FOREIGN KEY (codigo_modelo) REFERENCES modelo(codigo),
    FOREIGN KEY (codigo_pericia) REFERENCES pericia(codigo),
    FOREIGN KEY (codigo_seguro) REFERENCES seguro(codigo),
    FOREIGN KEY (codigo_tipo) REFERENCES tipo(codigo)
);
