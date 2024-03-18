// ignore_for_file: unused_element

import 'package:dw_request/app/view/back/configuracao_page_back.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';

class ConfiguracaoPage extends StatelessWidget {
  const ConfiguracaoPage({super.key});

  @override
  Widget build(BuildContext context) {
    var _back = ConfiguracaoPageBack();

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
        title: const Text('Configurações',
                  style: TextStyle(
                    color: Colors.white,
                  ),
          ),
        elevation: 0,
        backgroundColor: const Color.fromARGB(255, 55, 145, 230),
      ),
      body: Container(
        width: MediaQuery.of(context).size.width,
        padding: const EdgeInsets.all(27),
        //color: const Color.fromARGB(255, 179, 226, 238),
        /*decoration: const BoxDecoration(
          gradient: LinearGradient(
            colors: [
              Color.fromARGB(255, 58, 158, 183),
              Color.fromARGB(255, 71, 114, 233),
            ],
          ),
        ),*/
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            const SizedBox(height: 70),
            const Text(
              "Digite o endereço de acesso externo.",
              style: TextStyle(
                color: Color.fromARGB(255, 24, 23, 23),
              ),
            ),
            const SizedBox(height: 20),
            CupertinoTextField(  
              controller: _back.urlControler,            
              cursorColor: Colors.pinkAccent,
              padding: EdgeInsets.all(15),
              placeholder: "http://site.com.br:8080",
              placeholderStyle: TextStyle(color: Colors.white70, fontSize: 14),
              style: TextStyle(color: const Color.fromARGB(255, 43, 42, 42), fontSize: 14),
              decoration: const BoxDecoration(
                  color: Colors.black12,
                  borderRadius: BorderRadius.all(
                    Radius.circular(7),
                  )),
            ),
            const SizedBox(height: 10),
            SizedBox(
              width: double.infinity,
              child: CupertinoButton(
                padding: const EdgeInsets.all(17),
                color: Color.fromARGB(255, 55, 145, 230),
                child: const Text(
                  "Gravar",
                  style: TextStyle(
                      color: Colors.black45,
                      fontSize: 14,
                      fontWeight: FontWeight.w600),
                ),
                onPressed: () {
                  _back.btnGravar(context);
                },
              ),
            ),
            const SizedBox(height: 30),
            Observer(builder: (context) {
            return 
             _back.isLoading ? const CircularProgressIndicator()
             : SizedBox(
              width: double.infinity,
              child: CupertinoButton(
                padding: const EdgeInsets.all(17),
                color: Color.fromARGB(255, 55, 145, 230),
                child: const Text(
                  "Baixar Dados",
                  style: TextStyle(
                      color: Colors.black45,
                      fontSize: 14,
                      fontWeight: FontWeight.w600),
                ),
                onPressed: () {
                   _back.isLoading = true;
                   _back.btnBaixarDados(context);
                },
              ),
            );
            }),
          ]
        ),
      ),
    );
  }
}