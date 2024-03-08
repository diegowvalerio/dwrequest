// ignore_for_file: library_private_types_in_public_api, annotate_overrides, empty_constructor_bodies

import 'package:dw_request/app/domain/entity/usuario.dart';
import 'package:dw_request/app/domain/service/service_usuario.dart';
import 'package:dw_request/app/my_app.dart';
import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:mobx/mobx.dart';
import 'package:shared_preferences/shared_preferences.dart';

part 'login_page_back.g.dart';

class LoginPageBack = _LoginPageBack with _$LoginPageBack;

abstract class _LoginPageBack with Store{
 Usuario? usuario;
 final _servico = GetIt.I.get<ServiceUsuario>();

_LoginPageBack(){
  usuario = Usuario();
}

btnConfiguracao(BuildContext context,bool? check,String login,String senha){
  if(login.isNotEmpty && senha.isNotEmpty){
    salvaloginsenha(check, login, senha);
    Navigator.of(context).pushNamed(MyApp.CONFIGURACAO);
  }else{
    ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("Informe Login e Senha")));
  }
    
}
  
btnAcessar(BuildContext context,bool? check, String login, String senha) async {
  salvaloginsenha(check,login,senha);

  List<Usuario> list = await _servico.find();
  if(list.isNotEmpty){
    usuario = list.first;
    if(usuario?.login == login && usuario!.senha == senha){
      Navigator.of(context).popAndPushNamed(MyApp.PRINCIPAL);
    }else{
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("Login ou Senha inválido !")));
    }
  }else{
    ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("Nescessário Baixar os Dados !")));
  }
  
}

salvaloginsenha(bool? check,String login,String senha) async {
  if(check==true){
      SharedPreferences prefs = await SharedPreferences.getInstance();
      if (prefs.containsKey('lembre-me')) {
        check = prefs.getBool('lembre-me');
        login = prefs.getString('login')!;
        senha = prefs.getString('senha')!;
      } 
  }
}

}