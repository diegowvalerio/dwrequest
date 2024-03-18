

import 'package:dw_request/app/database/sqlite/script.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class Conexao{
  static Database? _db;

  static Future<Database?> get() async{
    if(_db == null){
      var path= join(await getDatabasesPath(),'banco.db');
      //await deleteDatabase(path);
      _db =await openDatabase(
        path,
        version: 1,
        onCreate:(Database db,int version) async {
          await db.execute(tabela_Usuario);
          await db.execute(tabela_CondPgto);
          await db.execute(tabela_FormaPag);
        },
        );
    }
    return _db;
  }
}
