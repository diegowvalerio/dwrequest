
import 'package:dw_request/app/database/sqlite/dao/imp_dao_contato.dart';
import 'package:dw_request/app/domain/interface/dao_contato.dart';
import 'package:get_it/get_it.dart';

setupInjecao(){
  GetIt getit = GetIt.I;

  getit.registerSingleton<DaoContato>(ImpDaoContato());
}