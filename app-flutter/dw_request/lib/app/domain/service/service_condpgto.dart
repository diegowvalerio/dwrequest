import 'package:dw_request/app/domain/entity/condpgto.dart';
import 'package:dw_request/app/domain/interface/dao_condpgto.dart';
import 'package:get_it/get_it.dart';

class ServiceCondPgto{

  final _dao = GetIt.I.get<DaoCondPgto>();

  save(Condpgto condpgto) async {
    await _dao.save(condpgto);
  }

  remove(int id) async {
    await _dao.remove(id);
  }

  Future<List<Condpgto>> find() async {
    return await _dao.find();
  }

  removeAll() async {
    await _dao.removeAll();
  }

}