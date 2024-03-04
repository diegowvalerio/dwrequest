import 'package:dw_request/app/database/sqlite/conexao.dart';
import 'package:dw_request/app/domain/entity/contato.dart';
import 'package:dw_request/app/domain/interface/dao_contato.dart';
import 'package:sqflite/sqflite.dart';

class ImpDaoContato implements DaoContato{
Database? _db;

  @override
  Future<List<Contato>> find() {
    // TODO: implement find
    throw UnimplementedError();
  }

  @override
  remove(int id) async{
     _db = await Conexao.get();
     var sql ='DELETE FROM contato HWERE id = ?';
     _db?.rawDelete(sql,[id]);
  }

  @override
  save(Contato contato) async {
    _db = await Conexao.get();
    var sql;
    if(contato.id == null){
      sql = 'INSERT INTO contato (nome,telefone,email,urlavatar) VALUES(?,?,?,?)';
      _db?.rawInsert(sql,[contato.nome,contato.telefone,contato.email,contato.urlavatar]);
    }else{
      sql = 'UPDATE contato SET nome=?,telefone=?,email=?,urlavatar=? WHERE id = ?';
      _db?.rawUpdate(sql,[contato.nome,contato.telefone,contato.email,contato.urlavatar]);
    }
  }

}