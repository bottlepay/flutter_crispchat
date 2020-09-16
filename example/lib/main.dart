import 'package:flutter/material.dart';
import 'package:flutter_crispchat/flutter_crispchat.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();

    FlutterCrispChat().streams.onUpdateUnreadCount.listen((event) {
      print('onUpdateUnreadCount: $event');
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('flutter_acquireio example app'),
        ),
        body: Center(
          child: Column(
            children: [
              RaisedButton(
                child: Text('Configure'),
                onPressed: () async {
                  await FlutterCrispChat().configure('f193b');
                },
              ),
              RaisedButton(
                child: Text('Show chat'),
                onPressed: () async {
                  await FlutterCrispChat().showChat();
                },
              ),
              RaisedButton(
                child: Text('Set user details'),
                onPressed: () async {
                  await FlutterCrispChat().setUserDetails(
                    email: 'andrew.coutts@bottlepay.com',
                    nickname: 'Andrew Coutts',
                    phone: '+14444444444',
                    avatarUrl:
                        'https://www.iconfinder.com/data/icons/flatfaces-everyday-people-square/128/beard_male_man_face_avatar-512.png',
                  );
                },
              ),
              RaisedButton(
                child: Text('Set custom fields'),
                onPressed: () async {
                  await FlutterCrispChat().setCustomFields({
                    'user_id': '1111111111111111111111111111',
                    'app_version': '1.0.2-2334055',
                  });
                },
              ),
              RaisedButton(
                child: Text('Logout'),
                onPressed: () async {
                  await FlutterCrispChat().logout();
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
