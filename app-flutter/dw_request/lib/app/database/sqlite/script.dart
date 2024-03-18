// ignore_for_file: constant_identifier_names

const tabela_Usuario = """
CREATE TABLE IF NOT EXISTS usuario (
  id int primary key,
  nome varchar(250) not null,
  situacao varchar(5) not null,
  login varchar(10) not null,
  senha varchar(100) not null
);""";

const drop_tabela_usuario = """ drop table usuario """;

const tabela_CondPgto = """
CREATE TABLE IF NOT EXISTS condpgto (
  id int primary key,
  idseven int not null,
  nome varchar(250) not null,
  descintegracao varchar(250) not null,
  tabelaprecoid int not null
);""";

const drop_tabela_CondPgto = """ drop table condpgto """;

const tabela_FormaPag = """
CREATE TABLE IF NOT EXISTS formapag (
  id int primary key,
  idseven int not null,
  nome varchar(250) not null,
  descintegracao varchar(250) not null
);""";

const drop_tabela_FormaPag = """ drop table formapag """;