// ignore_for_file: unused_element, unused_local_variable, no_leading_underscores_for_local_identifiers

import 'package:dw_request/app/view/back/login_page_back.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  bool? _isChecked = false;

  final TextEditingController _logincontroler = TextEditingController();
  final TextEditingController _senhacontroler = TextEditingController();

  void lembreme(bool value) async {
    _isChecked = value;
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setBool("lembre-me", value);

    setState(() {
      _isChecked = value;
      if (_isChecked == true) {
        prefs.setString('login', _logincontroler.text);
        prefs.setString('senha', _senhacontroler.text);
      } else {
        prefs.setString('login', '');
        prefs.setString('senha', '');
      }
    });
  }

  void buscarloginlembreme() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    if (prefs.containsKey('lembre-me')) {
      setState(() {
        _isChecked = prefs.getBool('lembre-me');
        if (_isChecked == true) {
          _logincontroler.text = prefs.getString('login')!;
          _senhacontroler.text = prefs.getString('senha')!;
        } else {
          _logincontroler.text = '';
          _senhacontroler.text = '';
        }
      });
    } else {
      prefs.setBool("lembre-me", false);
      prefs.setString('login', '');
      prefs.setString('senha', '');
    }
  }

  @override
  void initState() {
    super.initState();
    buscarloginlembreme();
  }

  @override
  Widget build(BuildContext context) {
    var _back = LoginPageBack();
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Dw Request',
          style: TextStyle(
            color:Color.fromARGB(255, 255, 255, 255),
          ),
        ),
        elevation: 0,
        backgroundColor: const Color.fromARGB(255, 55, 145, 230),//Color.fromARGB(255, 58, 158, 183),
        actions: [
          IconButton(
              onPressed: () {
                _back.btnConfiguracao(context,_isChecked,_logincontroler.text,_senhacontroler.text);
              },
              iconSize: 35,
              color: const Color.fromARGB(255, 210, 223, 236),
              icon: const Icon(Icons.settings))
        ],
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(27),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const FractionallySizedBox(
              widthFactor: 0.9,
            child: Image(
              image: AssetImage('assets/imagens/logo.png'),
              //width: 250,
            ),
            ),
            const Text(
              "Digite os dados de acesso nos campos abaixo.",
              style: TextStyle(
                color: Color.fromARGB(255, 17, 17, 17),
              ),
            ),
            const SizedBox(height: 30),
            CupertinoTextField(
              controller: _logincontroler,
              //onSubmitted: (value) => _back.login,
              cursorColor: Colors.pinkAccent,
              padding: const EdgeInsets.all(15),
              placeholder: "Digite o seu login",
              placeholderStyle:
                  const TextStyle(color: Colors.white70, fontSize: 14),
              style: const TextStyle(color: Color.fromARGB(255, 34, 33, 33), fontSize: 14),
              decoration: const BoxDecoration(
                  color: Colors.black12,
                  borderRadius: BorderRadius.all(
                    Radius.circular(7),
                  )),
            ),
            const SizedBox(height: 5),
            CupertinoTextField(
              controller: _senhacontroler,
              //onSubmitted: (value) => _back.senha, 
              padding: const EdgeInsets.all(15),
              cursorColor: Colors.pinkAccent,
              placeholder: "Digite sua senha",
              obscureText: true,
              placeholderStyle:
                  const TextStyle(color: Colors.white70, fontSize: 14),
              style: const TextStyle(color: Color.fromARGB(255, 34, 33, 33), fontSize: 14),
              decoration: const BoxDecoration(
                  color: Colors.black12,
                  borderRadius: BorderRadius.all(
                    Radius.circular(7),
                  )),
            ),
            Row(mainAxisAlignment: MainAxisAlignment.start, children: [
              Checkbox(
                  activeColor: const Color(0xff00C8E8),
                  value: _isChecked,
                  onChanged: (value) {
                    //setState(() {
                      //_back.lembreme(value!);
                    //});                      
                    lembreme(value!);
                  }),
              const Text(
                "Lembrar",
                style: TextStyle(
                  color: Color.fromARGB(255, 34, 33, 33),
                ),
              ),
            ]),
            const SizedBox(height: 10),
            SizedBox(
              width: double.infinity,
              child: CupertinoButton(
                padding: const EdgeInsets.all(17),
                color: const Color.fromARGB(255, 55, 145, 230),
                child: const Text(
                  "Acessar",
                  style: TextStyle(
                      color: Colors.black45,
                      fontSize: 14,
                      fontWeight: FontWeight.w600),
                ),
                onPressed: () {
                  _back.btnAcessar(
                      context,_isChecked,_logincontroler.text, _senhacontroler.text);
                },
              ),
            ),
          ],
        ),
      )
    );
  }
}
