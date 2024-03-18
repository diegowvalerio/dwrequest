import 'package:dw_request/app/database/sqlite/conexao.dart';
import 'package:dw_request/app/domain/entity/condpgto.dart';
import 'package:dw_request/app/domain/interface/dao_condpgto.dart';
import 'package:sqflite/sqflite.dart';

class ImpDaoCondPgto implements DaoCondPgto{
Database? _db;

  @override
  Future<List<Condpgto>> find() async{
    _db = await Conexao.get();
    List<Map<String,dynamic>>? result = await _db?.query('condpgto');
    List<Condpgto> lista = List.generate(result!.length, (i){
      var linha = result[i];
      return Condpgto(
        id: linha['id'],
        idseven: linha['idseven'],
        nome: linha['nome'],
        descintegracao: linha['descintegracao'],
        tabelaprecoid: linha['tabelaprecoid']
      );
    }
    );
    return lista;
  }

  @override
  remove(int id) async{
     _db = await Conexao.get();
     var sql ='DELETE FROM condpgto WHERE id = ?';
     await _db?.rawDelete(sql,[id]);
  }

  @override
  save(Condpgto x) async {
    _db = await Conexao.get();
    String sql;
    sql = 'INSERT INTO condpgto (idseven,nome,descintegracao,tabelaprecoid) VALUES(?,?,?,?)';
    await _db?.rawInsert(sql,[x.idseven,x.nome,x.descintegracao,x.tabelaprecoid]);
    
  }
  
  @override
  removeAll() async{
    _db = await Conexao.get();
     var sql ='DELETE FROM condpgto';
    await _db?.rawDelete(sql);
  }

}