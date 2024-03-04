import 'package:dw_request/app/domain/entity/contato.dart';

abstract class DaoContato{

save(Contato contato);

remove(int id);

Future<List<Contato>> find();

}