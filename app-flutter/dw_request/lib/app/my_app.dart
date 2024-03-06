// ignore_for_file: constant_identifier_names

import 'package:dw_request/app/view/configuracao_page.dart';
import 'package:flutter/material.dart';

import 'view/login_page.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  //rotas
  static const HOME = '/';
  static const CONFIGURACAO = 'configuracao';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Dw Resquest',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      routes: {
        HOME:(context)=> const LoginPage(),
        CONFIGURACAO:(context)=> const ConfiguracaoPage(),
      },
    );
  }
}