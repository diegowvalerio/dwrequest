import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
//import 'package:flutter_exit_app/flutter_exit_app.dart';

class PrincipalPage extends StatelessWidget {
  const PrincipalPage({super.key});

  void selectedItem(BuildContext context, int index) {
    // Navigator.of(context).pop();
    print(index);

    switch (index) {
      case 0:
        Navigator.pushReplacementNamed(context, "/home");
        break;
      case 1:
        Navigator.pushReplacementNamed(context, "/profile");
        break;
      case 2:
        Navigator.pushReplacementNamed(context, "/marketOrderHistory");
        break;
      case 3:
        Navigator.pushReplacementNamed(context, "/paymenthistory");
        break;
      case 4:
        Navigator.pushReplacementNamed(context, "/paymentdeatils");
        break;
      case 5:
        Navigator.pushReplacementNamed(context, "/settings");
        break;
      case 6:
        Navigator.pushReplacementNamed(context, "/chooseLocation");
        break;
      case 7:
        Navigator.pushReplacementNamed(context, "/feedback");
        break;
      case 8:
        Navigator.pushReplacementNamed(context, "/contactus");
        break;
    }
  }

  Future confirmarsaida(BuildContext context){
    return showDialog(
                context: context,
                builder: (context) {
                  return AlertDialog(
                    title: const Text('Confirmação'),
                    content: const Text('Você deseja realmente sair do App ?'),
                    actions: [
                      TextButton(
                      onPressed: () {
                        if (Platform.isIOS) {
                        SystemChannels.platform.invokeMethod('SystemNavigator.pop');
                        }else{
                         // FlutterExitApp.exitApp();
                        }
                      },
                       child: const Text('Sim'),
                      ),
                      TextButton(
                      onPressed: () {
                        Navigator.pop(context, false);
                      },
                       child: const Text('Não'),
                      ),
                    ],
                  );
                },
              );          
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Bem Vindo',
          style: TextStyle(
            color: Colors.white,
          ),
        ),
        backgroundColor: const Color.fromARGB(255, 55, 145, 230),
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            //------------user image section  and other deatils //
            Container(
                child: const SizedBox(
                  height: 100.0,
                  child: DrawerHeader(
                decoration: BoxDecoration(
                color: Color.fromARGB(255, 55, 145, 230),
                ),
               child: Text.rich(
                  TextSpan(
                    children: [
                      TextSpan(
                        text: 'Dw',
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 24.0,
                        ),
                      ),
                      TextSpan(
                        text: ' Request',
                        style: TextStyle(
                          color: Colors.white,
                        ),
                        ),
                    ],
                  ),
                )
              ),
            )),
            //--------menu list ------------------//
            ListTile(
              leading: Icon(Icons.home),
              title: Text(
                'side_MenuHome',
                style: TextStyle(
                  fontSize: 15.0,
                  color: Colors.black,
                  letterSpacing: 0,
                ),
              ),
              onTap: () => selectedItem(context, 0),
            ),
            ListTile(
              leading: Icon(Icons.history_rounded),
              title: Text(
                'side_MenuOrderHistory',
                style: TextStyle(
                  fontSize: 15.0,
                  color: Colors.black,
                  letterSpacing: 0,
                ),
              ),
              onTap: () => selectedItem(context, 1),
            ),
            ListTile(
              leading: Icon(Icons.payment),
              title: Text(
                'side_MenuPaymentHistory',
                style: TextStyle(
                  fontSize: 15.0,
                  color: Colors.black,
                  letterSpacing: 0,
                ),
              ),
              onTap: () => selectedItem(context, 2),
            ),
            ListTile(
              leading: Icon(
                Icons.settings,
                color: Colors.black54,
              ),
              title: Text(
                'side_MenuSettings',
                style: TextStyle(
                  fontSize: 15.0,
                  color: Colors.black,
                  letterSpacing: 0,
                ),
              ),
              onTap: () => selectedItem(context, 3),
            ),
            ListTile(
              leading: Image.asset(
                "assets/imagens/logo.png",
                width: 25,
                height: 25,
                fit: BoxFit.fill,
                color: Colors.black54,
              ),
              title: Text(
                'side_MenuChooseLocation',
                style: TextStyle(
                  fontSize: 15.0,
                  color: Colors.black,
                  letterSpacing: 0,
                ),
              ),
              onTap: () => selectedItem(context, 4),
            ),
            ListTile(
              leading: Image.asset(
                "assets/imagens/logo.png",
                width: 25,
                height: 25,
                fit: BoxFit.fill,
                color: Colors.black54,
              ),
              title: Text(
                "Support",
                style: TextStyle(
                  fontSize: 15.0,
                  color: Colors.black,
                  letterSpacing: 0,
                ),
              ),
              onTap: () => selectedItem(context, 5),
            ),
            ListTile(
              leading: Image.asset(
                "assets/imagens/logo.png",
                width: 25,
                height: 25,
                fit: BoxFit.fill,
                color: Colors.black54,
              ),
              title: Text(
                "Feedback",
                style: TextStyle(
                  fontSize: 15.0,
                  color: Colors.black,
                  letterSpacing: 0,
                ),
              ),
              onTap: () => selectedItem(context, 6),
            ),
            Divider(
              color: Colors.black38,
            ),
            //----------------------end code-------------------//
            ListTile(
              leading: Image.asset(
                "assets/imagens/logo.png",
                width: 25,
                height: 25,
                fit: BoxFit.fill,
                color: Colors.black54,
              ),
              title: const Text(
                "Sair",
                style: TextStyle(
                  fontSize: 15.0,
                  color: Colors.black,
                  letterSpacing: 0,
                ),
              ),
              onTap: () => confirmarsaida(context),
            ),
          ],
        ),
      ),
    );
  }
}
