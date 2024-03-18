import 'package:dw_request/app/domain/entity/condpgto.dart';

abstract class DaoCondPgto{

save(Condpgto condpgto);

remove(int id);

removeAll();

Future<List<Condpgto>> find();

}