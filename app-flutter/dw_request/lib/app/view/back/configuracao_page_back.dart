// ignore_for_file: prefer_interpolation_to_compose_strings

import 'dart:convert';

import 'package:dw_request/app/domain/entity/usuario.dart';
import 'package:dw_request/app/domain/service/service_usuario.dart';
import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:mobx/mobx.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;

part 'configuracao_page_back.g.dart';

class ConfiguracaoPageBack = _ConfiguracaoPageBack with _$ConfiguracaoPageBack;

abstract class _ConfiguracaoPageBack with Store{
  final _servico = GetIt.I.get<ServiceUsuario>();
  TextEditingController urlControler = TextEditingController();
  
  _ConfiguracaoPageBack(){
    urlControler.text = buscaurl().toString();
  }
  
  Future<TextEditingController> buscaurl() async{
    SharedPreferences prefs =  await SharedPreferences.getInstance();
    String? uri ='';
    if(prefs.containsKey('url')){
        uri = prefs.getString('url');
    }
    urlControler.text = uri!;
    return urlControler;
  }

  //grava a url na memoria
  btnGravar(BuildContext context) async{
    SharedPreferences prefs =  await SharedPreferences.getInstance();
    if(urlControler.text.isNotEmpty){
      prefs.setString('url', urlControler.text);
    }else{
      prefs.remove('url');
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("Informe um Endereço Válido!")));
    }
  }

  btnBaixarDados(BuildContext context) async {
    if(urlControler.text.isNotEmpty){
      SharedPreferences prefs =  await SharedPreferences.getInstance();
      String login = prefs.getString('login')!;
      String senha = prefs.getString('senha')!;
      String urlC = urlControler.text+'/dwrequest/reset/usuario/login?login='+login+'&senha='+senha;

      var listausuario =<Usuario>[];
      var uri = Uri.parse(urlC);
      try{
        var response = await http.get(uri);
        var statusCode = response.statusCode;
      if(statusCode == 200){
        Iterable list= json.decode(response.body);
        for(Map<String,dynamic>item in list){
          var usuario = Usuario(
              id: item['idusuario'],
              login: item['login'],
              nome: item['nome'],
              senha: item['senha'],
              situacao: item['situacao'].toString()              
              );
          listausuario.add(usuario);
          ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Login:"+item['nome']+" Ok!")));
        }
        await _servico.removeAll();
        await salvaUsuario(context, listausuario.first);
      }else{
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Erro:{$statusCode} Não foi possivel Baixar Dados!")));
      }
      }catch(e){
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Erro:"+e.toString())));
      }    
      
    }else{
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("Informe um Endereço Válido!")));
    }
  }

  salvaUsuario(BuildContext context,Usuario usuario) async{
    try{
      await _servico.save(usuario);
    }catch(e){
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Não foi possivel salvar Usuário, Erro: "+e.toString())));
    }
  }


}