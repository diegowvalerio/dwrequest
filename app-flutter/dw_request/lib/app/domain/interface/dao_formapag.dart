import 'package:dw_request/app/domain/entity/formapag.dart';

abstract class DaoFormaPag{

save(FormaPag formaPag);

remove(int id);

removeAll();

Future<List<FormaPag>> find();

}