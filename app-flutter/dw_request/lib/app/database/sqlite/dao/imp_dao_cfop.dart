import 'package:dw_request/app/database/sqlite/conexao.dart';
import 'package:dw_request/app/domain/entity/cfop.dart';
import 'package:dw_request/app/domain/interface/dao_cfop.dart';
import 'package:sqflite/sqflite.dart';

class ImpDaoCfop implements DaoCfop{
Database? _db;

  @override
  Future<List<Cfop>> find() async{
    _db = await Conexao.get();
    List<Map<String,dynamic>>? result = await _db?.query('usuario');
    List<Cfop> lista = List.generate(result!.length, (i){
      var linha = result[i];
      return Cfop(
        id: linha['id'],
        cfop: linha['cfop']
      );
    }
    );
    return lista;
  }

  @override
  remove(int id) async{
     _db = await Conexao.get();
     var sql ='DELETE FROM cfop WHERE id = ?';
   await  _db?.rawDelete(sql,[id]);
  }

  @override
  save(Cfop cfop) async {
    _db = await Conexao.get();
    String sql;
    sql = 'INSERT INTO cfop (id,cfop) VALUES(?)';
   await _db?.rawInsert(sql,[cfop.id,cfop.cfop]);
    
  }
  
  @override
  removeAll() async{
    _db = await Conexao.get();
     var sql ='DELETE FROM cfop';
     await _db?.rawDelete(sql);
  }

}