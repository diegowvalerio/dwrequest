import 'package:dw_request/app/database/sqlite/conexao.dart';
import 'package:dw_request/app/domain/entity/formapag.dart';
import 'package:dw_request/app/domain/interface/dao_formapag.dart';
import 'package:sqflite/sqflite.dart';

class ImpDaoFormaPag implements DaoFormaPag {
Database? _db;

  @override
  Future<List<FormaPag>> find() async{
    _db = await Conexao.get();
    List<Map<String,dynamic>>? result = await _db?.query('formapag');
    List<FormaPag> lista = List.generate(result!.length, (i){
      var linha = result[i];
      return FormaPag(
        id: linha['id'],
        idseven: linha['idseven'],
        nome: linha['nome'],
        descintegracao: linha['descintegracao'],
      );
    }
    );
    return lista;
  }

  @override
  remove(int id) async{
     _db = await Conexao.get();
     var sql ='DELETE FROM formapag WHERE id = ?';
     await _db?.rawDelete(sql,[id]);
  }

  @override
  save(FormaPag x) async {
    _db = await Conexao.get();
    String sql;
    sql = 'INSERT INTO formapag (idseven,nome,descintegracao) VALUES(?,?,?)';
    await _db?.rawInsert(sql,[x.idseven,x.nome,x.descintegracao]);
    
  }
  
  @override
  removeAll() async{
    _db = await Conexao.get();
     var sql ='DELETE FROM formapag';
    await _db?.rawDelete(sql);
  }

}