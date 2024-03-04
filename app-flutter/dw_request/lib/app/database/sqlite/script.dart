final createTable = '''
create table contato{
  id int primary key,
  nome varchar(200) not null,
  telefone char(16) not null,
  email varchar(150) not null,
  url-avatar varchar(300) not null
}

''';