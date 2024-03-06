import 'package:dw_request/app/database/sqlite/conexao.dart';
import 'package:dw_request/app/domain/entity/usuario.dart';
import 'package:dw_request/app/domain/interface/dao_usuario.dart';
import 'package:sqflite/sqflite.dart';

class ImpDaoUsuario implements DaoUsuario{
Database? _db;

  @override
  Future<List<Usuario>> find() {
    
    throw UnimplementedError();
  }

  @override
  remove(int id) async{
     _db = await Conexao.get();
     var sql ='DELETE FROM contato HWERE id = ?';
     _db?.rawDelete(sql,[id]);
  }

  @override
  save(Usuario usuario) async {
    _db = await Conexao.get();
    String sql;
    sql = 'INSERT INTO contato (id,nome,situacao,login,senha) VALUES(?,?,?,?)';
    _db?.rawInsert(sql,[usuario.id,usuario.nome,usuario.situacao,usuario.login,usuario.senha]);
    
  }

}