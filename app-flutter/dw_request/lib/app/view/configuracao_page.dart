// ignore_for_file: unused_element

import 'package:flutter/material.dart';

class ConfiguracaoPage extends StatelessWidget {
  const ConfiguracaoPage({super.key});

  @override
  Widget build(BuildContext context) {

  Widget textconfiguracao(){
    return TextFormField(
      decoration: const InputDecoration(
        labelText: 'Endereço:',
        hintText: 'http://endereco.com:8080'
      ),
    );
  }
    return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        title: const Text('Configurações'),
        elevation: 0,
        backgroundColor: Colors.transparent,
      ),
    );
  }
}