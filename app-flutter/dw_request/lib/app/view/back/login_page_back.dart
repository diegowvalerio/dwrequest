// ignore_for_file: library_private_types_in_public_api, annotate_overrides, empty_constructor_bodies

import 'package:dw_request/app/domain/entity/usuario.dart';
import 'package:dw_request/app/my_app.dart';
import 'package:flutter/material.dart';
import 'package:mobx/mobx.dart';

part 'login_page_back.g.dart';

class LoginPageBack = _LoginPageBack with _$LoginPageBack;

abstract class _LoginPageBack with Store{
 Usuario? usuario;

  _LoginPageBack(BuildContext context){
    usuario = Usuario();
  }

  btnConfiguracao(BuildContext context){
    Navigator.of(context).pushNamed(MyApp.CONFIGURACAO);
  }
  

}