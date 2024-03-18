import 'package:dw_request/app/domain/entity/formapag.dart';
import 'package:dw_request/app/domain/interface/dao_formapag.dart';
import 'package:get_it/get_it.dart';

class ServiceFormaPag{

  final _dao = GetIt.I.get<DaoFormaPag>();

  save(FormaPag formaPag) async {
    await _dao.save(formaPag);
  }

  remove(int id) async {
    await _dao.remove(id);
  }

  Future<List<FormaPag>> find() async {
    return await _dao.find();
  }

  removeAll() async {
    await _dao.removeAll();
  }

}