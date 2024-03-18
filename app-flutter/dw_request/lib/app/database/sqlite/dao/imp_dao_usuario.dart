import 'package:dw_request/app/database/sqlite/conexao.dart';
import 'package:dw_request/app/domain/entity/usuario.dart';
import 'package:dw_request/app/domain/interface/dao_usuario.dart';
import 'package:sqflite/sqflite.dart';

class ImpDaoUsuario implements DaoUsuario{
Database? _db;

  @override
  Future<List<Usuario>> find() async{
    _db = await Conexao.get();
    List<Map<String,dynamic>>? result = await _db?.query('usuario');
    List<Usuario> lista = List.generate(result!.length, (i){
      var linha = result[i];
      return Usuario(
        id: linha['id'],
        nome: linha['nome'],
        situacao: linha['situacao'],
        login: linha['login'],
        senha: linha['senha']
      );
    }
    );
    return lista;
  }

  @override
  remove(int id) async{
     _db = await Conexao.get();
     var sql ='DELETE FROM usuario WHERE id = ?';
   await  _db?.rawDelete(sql,[id]);
  }

  @override
  save(Usuario usuario) async {
    _db = await Conexao.get();
    String sql;
    sql = 'INSERT INTO usuario (id,nome,situacao,login,senha) VALUES(?,?,?,?,?)';
   await _db?.rawInsert(sql,[usuario.id,usuario.nome,usuario.situacao,usuario.login,usuario.senha]);
    
  }
  
  @override
  removeAll() async{
    _db = await Conexao.get();
     var sql ='DELETE FROM usuario';
     await _db?.rawDelete(sql);
  }

}