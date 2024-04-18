import 'package:dw_request/app/domain/entity/cfop.dart';
import 'package:dw_request/app/domain/interface/dao_cfop.dart';
import 'package:get_it/get_it.dart';

class ServiceCfop{

  final _dao = GetIt.I.get<DaoCfop>();

  save(Cfop cfop) async {
    await _dao.save(cfop);
  }

  remove(int id) async {
   await _dao.remove(id);
  }

  Future<List<Cfop>> find() async {
    return await _dao.find();
  }

  removeAll() async {
    await _dao.removeAll();
  }

}