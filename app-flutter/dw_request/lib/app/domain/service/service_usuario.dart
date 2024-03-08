import 'package:dw_request/app/domain/entity/usuario.dart';
import 'package:dw_request/app/domain/interface/dao_usuario.dart';
import 'package:get_it/get_it.dart';

class ServiceUsuario{

  final _dao = GetIt.I.get<DaoUsuario>();

  save(Usuario usuario){
    _dao.save(usuario);
  }

  remove(int id){
    _dao.remove(id);
  }

  Future<List<Usuario>> find(){
    return _dao.find();
  }

  removeAll(){
    _dao.removeAll();
  }

}