
import 'package:dw_request/app/database/sqlite/dao/imp_dao_condpgto.dart';
import 'package:dw_request/app/database/sqlite/dao/imp_dao_contato.dart';
import 'package:dw_request/app/database/sqlite/dao/imp_dao_formapag.dart';
import 'package:dw_request/app/database/sqlite/dao/imp_dao_usuario.dart';
import 'package:dw_request/app/domain/interface/dao_condpgto.dart';
import 'package:dw_request/app/domain/interface/dao_contato.dart';
import 'package:dw_request/app/domain/interface/dao_formapag.dart';
import 'package:dw_request/app/domain/interface/dao_usuario.dart';
import 'package:dw_request/app/domain/service/service_condpgto.dart';
import 'package:dw_request/app/domain/service/service_formapag.dart';
import 'package:dw_request/app/domain/service/service_usuario.dart';
import 'package:get_it/get_it.dart';

setupInjecao(){
  GetIt getit = GetIt.I;

  getit.registerSingleton<DaoContato>(ImpDaoContato());
  
  getit.registerSingleton<DaoUsuario>(ImpDaoUsuario());
  getit.registerSingleton<ServiceUsuario>(ServiceUsuario());

  getit.registerSingleton<DaoCondPgto>(ImpDaoCondPgto());
  getit.registerSingleton<ServiceCondPgto>(ServiceCondPgto());

  getit.registerSingleton<DaoFormaPag>(ImpDaoFormaPag());
  getit.registerSingleton<ServiceFormaPag>(ServiceFormaPag());

}