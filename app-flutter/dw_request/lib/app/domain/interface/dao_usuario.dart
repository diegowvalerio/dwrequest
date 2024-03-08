import 'package:dw_request/app/domain/entity/usuario.dart';

abstract class DaoUsuario{

save(Usuario usuario);

remove(int id);

removeAll();

Future<List<Usuario>> find();

}