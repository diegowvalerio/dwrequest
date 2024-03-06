import 'package:dw_request/app/domain/entity/usuario.dart';

abstract class DaoUsuario{

save(Usuario usuario);

remove(int id);

Future<List<Usuario>> find();

}