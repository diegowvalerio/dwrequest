// ignore_for_file: prefer_interpolation_to_compose_strings

import 'dart:convert';

import 'package:dw_request/app/domain/entity/condpgto.dart';
import 'package:dw_request/app/domain/entity/formapag.dart';
import 'package:dw_request/app/domain/entity/usuario.dart';
import 'package:dw_request/app/domain/service/service_condpgto.dart';
import 'package:dw_request/app/domain/service/service_formapag.dart';
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
  final _servicoCondpgto = GetIt.I.get<ServiceCondPgto>();
  final _servicoFormaPag = GetIt.I.get<ServiceFormaPag>();

  String? erro = "1";
  @observable
  bool isLoading = false;

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
      if (!context.mounted) return; 
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("Informe um Endereço Válido!")));
    }
  }


  btnBaixarDados(BuildContext context) async {
    if(urlControler.text.isNotEmpty){
      /* SharedPreferences prefs =  await SharedPreferences.getInstance();
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
        }
        await _servico.removeAll();
        await salvaUsuario(context, listausuario.first);
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Login Ok!")));

      }else{
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Erro:{$statusCode} Não foi possivel Baixar Dados!")));
      }
      }catch(e){
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Erro:"+e.toString())));
      } 
      */
      await wsusuario();
       if (!context.mounted) return; 
      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text(erro!),duration: const Duration(seconds:10 )));
      exibeprogesso();
    }else{
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text("Informe um Endereço Válido!"),));
    }
  }

  @action
  exibeprogesso(){
    if(isLoading){
      isLoading = false;
    }else{
      isLoading = true;
    }
  }

  wsusuario() async{

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
        }
        await _servico.removeAll();
        await salvaUsuario(listausuario.first);
        await salvaCondPgto(urlControler.text, login);
        await salvaFormaPag(urlControler.text);

      }else{
        erro = "Erro:{$statusCode} Não foi possivel Baixar Dados!";
      }
      }catch(e){
        erro = "Erro:"+e.toString();
      }  
  }
  

  salvaUsuario(Usuario usuario) async{
    try{
      await _servico.save(usuario);
      erro = "Login Ok!";
    }catch(e){
      erro = "Não foi possivel salvar Usuário, Erro: "+e.toString();
    }
  }

  salvaCondPgto(String urlC, String login) async{

    urlC = urlC+"/dwrequest/reset/condpgto/login?login="+login;

    var lista =<Condpgto>[];
    var uri = Uri.parse(urlC);
      try{
        var response = await http.get(uri);
        var statusCode = response.statusCode;
      if(statusCode == 200){
        Iterable list= json.decode(response.body);
        for(Map<String,dynamic>item in list){
          var condpgto= Condpgto(
              idseven : item['idcondpgto'],
              nome: item['nome'],
              descintegracao: item['desc_integracao'],
              tabelaprecoid: item['tabelaprecoid']              
              );
          lista.add(condpgto);
        }
        await _servicoCondpgto.removeAll();
        try{
          for (int i = 0; i < lista.length; i++){
            Condpgto e = lista[i];
            await _servicoCondpgto.save(e);
          }
          erro = "Condição de Pagamento Concluido";
        }catch(e){
          erro = "Não foi possivel salvar Condição de Pagamento, Erro: "+e.toString();
        }
      }else{
        erro = "Erro:{$statusCode} Não foi possivel Baixar Condição de Pagamento!";
      }
      }catch(e){
        erro = "Erro:"+e.toString();
      } 
  }
  
  salvaFormaPag(String urlC) async {
    urlC = urlC+"/dwrequest/reset/formapag";

    var lista =<FormaPag>[];
    var uri = Uri.parse(urlC);
      try{
        var response = await http.get(uri);
        var statusCode = response.statusCode;
      if(statusCode == 200){
        Iterable list= json.decode(response.body);
        for(Map<String,dynamic>item in list){
          var x= FormaPag(
              idseven : item['idformapag'],
              nome: item['nome'],
              descintegracao: item['desc_integracao']           
              );
          lista.add(x);
        }
        await _servicoFormaPag.removeAll();
        try{
          for (int i = 0; i < lista.length; i++){
            FormaPag e = lista[i];
            await _servicoFormaPag.save(e);
          }
          erro = "Forma de pagamento Concluido";
        }catch(e){
          erro = "Não foi possivel salvar Forma de pagamento, Erro: "+e.toString();
        }
      }else{
        erro = "Erro:{$statusCode} Não foi possivel Baixar Forma de pagamento!";
      }
      }catch(e){
        erro = "Erro:"+e.toString();
      } 
  }


}