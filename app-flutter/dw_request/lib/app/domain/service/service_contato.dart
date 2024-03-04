import 'package:dw_request/app/domain/entity/contato.dart';
import 'package:dw_request/app/domain/interface/dao_contato.dart';
import 'package:get_it/get_it.dart';

class ServiceContato{

  final _dao = GetIt.I.get<DaoContato>();

  save(Contato contato){
    _dao.save(contato);
  }

  remove(int id){
    _dao.remove(id);
  }

  Future<List<Contato>> find(){
    return _dao.find();
  }

}