

import 'package:dw_request/app/database/sqlite/script.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class Conexao{
  static Database? _db;

  static Future<Database?> get() async{
    if(_db == null){
      var path= join(await getDatabasesPath(),'banco');
      _db =await openDatabase(
        path,
        version: 1,
        onCreate:(db,v) {
          db.execute(tabela_Usuario);
        },
        );
    }
    return _db;
  }
}
