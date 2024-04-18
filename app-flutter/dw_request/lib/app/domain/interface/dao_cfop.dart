import 'package:dw_request/app/domain/entity/cfop.dart';

abstract class DaoCfop{

save(Cfop cfop);

remove(int id);

removeAll();

Future<List<Cfop>> find();

}