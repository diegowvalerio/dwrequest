// ignore_for_file: constant_identifier_names

const tabela_Usuario = '''
create table usuario{
  id int primary key,
  nome varchar(250) not null,
  situacao varchar(5) not null,
  login varchar(10) not null,
  senha varchar(100) not null
}
''';

const drop_tabela_usuario = ''' drop table usuario ''';