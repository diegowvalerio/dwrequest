// ignore_for_file: unused_element, unused_local_variable, no_leading_underscores_for_local_identifiers

import 'package:dw_request/app/view/back/login_page_back.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginPage extends StatefulWidget{
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  bool? _isChecked = false;

  TextEditingController _logincontroler = TextEditingController();
  TextEditingController _senhacontroler = TextEditingController();

  void lembreme(bool value) async {
    _isChecked = value;
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setBool("lembre-me", value);
    
    setState(() {
      _isChecked = value;
      if(_isChecked==true){
        prefs.setString('login', _logincontroler.text);
        prefs.setString('senha', _senhacontroler.text);
      }else{
        prefs.setString('login', '');
        prefs.setString('senha', '');
      }
    });
  }

  void buscarloginlembreme() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    if(prefs.containsKey('lembre-me')){
      setState(() {
      _isChecked = prefs.getBool('lembre-me');
      if(_isChecked==true){
        _logincontroler.text = prefs.getString('login')!;
        _senhacontroler.text = prefs.getString('senha')!;
      }else{
        _logincontroler.text = '';
        _senhacontroler.text = '';
      }
    });
    }else{
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
    var _back = LoginPageBack(context);

  return Scaffold(
      extendBodyBehindAppBar: true,
      appBar: AppBar(
        title: const Text('Dw Request'),
        elevation: 0,
        backgroundColor: Colors.transparent,
        actions: [
          IconButton(
            onPressed: (){
              _back.btnConfiguracao(context);
          }, 
          iconSize: 35,
          color: Colors.greenAccent,
          icon: const Icon(
            Icons.settings)
          )
        ],
      ),
      body: Container(
        width: MediaQuery.of(context).size.width,
        padding: const EdgeInsets.all(27),
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            colors: [
              Color.fromARGB(255, 58, 158, 183),
              Color.fromARGB(255, 71, 114, 233),
            ],
          ),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Image(
              image: AssetImage('assets/imagens/logo.png'),
              width: 250,
            ),
            const SizedBox(height: 30),
            const Text(
              "Digite os dados de acesso nos campos abaixo.",
              style: TextStyle(
                color: Colors.white,
              ),
            ),
            const SizedBox(height: 30),
            CupertinoTextField(  
              controller: _logincontroler,            
              cursorColor: Colors.pinkAccent,
              padding: EdgeInsets.all(15),
              placeholder: "Digite o seu login",
              placeholderStyle: TextStyle(color: Colors.white70, fontSize: 14),
              style: TextStyle(color: Colors.white, fontSize: 14),
              decoration: const BoxDecoration(
                  color: Colors.black12,
                  borderRadius: BorderRadius.all(
                    Radius.circular(7),
                  )),
            ),
            const SizedBox(height: 5),
            CupertinoTextField(
              controller: _senhacontroler,
              padding: EdgeInsets.all(15),
              cursorColor: Colors.pinkAccent,
              placeholder: "Digite sua senha",
              obscureText: true,
              placeholderStyle: TextStyle(color: Colors.white70, fontSize: 14),
              style: TextStyle(color: Colors.white, fontSize: 14),
              decoration: const BoxDecoration(
                  color: Colors.black12,
                  borderRadius: BorderRadius.all(
                    Radius.circular(7),
                  )),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Checkbox(
                  activeColor: const Color(0xff00C8E8),
                  value: _isChecked,
                  onChanged: (value){
                    lembreme(value!);  
                    }                       
                ),
                const Text("Lembrar",
                  style: TextStyle(
                  color: Color(0xff646464),
                  fontSize: 12,
                  fontFamily: 'Rubic'
                    )
                ),
              ]
            ),
            const SizedBox(height: 10),
            SizedBox(
              width: double.infinity,
              child: CupertinoButton(
                padding: const EdgeInsets.all(17),
                color: Colors.greenAccent,
                child: const Text(
                  "Acessar",
                  style: TextStyle(
                      color: Colors.black45,
                      fontSize: 14,
                      fontWeight: FontWeight.w600),
                ),
                onPressed: () {},
              ),
            ),
          ],
        ),
      ),
    );

    
  }
}