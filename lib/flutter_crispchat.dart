import 'package:flutter/services.dart';
import 'package:flutter_crispchat/src/streams.dart';

class FlutterCrispChat {
  static final FlutterCrispChat _instance = FlutterCrispChat._internal();
  factory FlutterCrispChat() => _instance;
  FlutterCrispChat._internal();

  var isConfigured = false;

  /// Method channel
  MethodChannel _channel =
      const MethodChannel('com.bottlepay.flutter_crispchat');

  /// Throws if configure has not been called yet.
  void _ensureConfigured() =>
      !isConfigured ? throw ('Error: must call configure first.') : null;

  /// Will contain all of our streams for various platform events.
  final streams = StreamHandler();

  /// Configure the plugin. Can optionally cutomize the configuration options.
  /// This must be called before calling `showSupport`.
  Future<void> configure(String websiteId) async {
    await _channel.invokeMethod('configure', {
      'websiteId': websiteId,
    });

    isConfigured = true;
  }

  /// Open the chat window
  Future<void> showChat() async {
    _ensureConfigured();

    await _channel.invokeMethod('showChat');
  }

  /// Set a visitor identifier for your visitor, can be tracked by admin in remark.
  Future<void> setUserDetails({
    String? email,
    String? nickname,
    String? phone,
    String? avatarUrl,
    String? company,
  }) async {
    _ensureConfigured();

    await _channel.invokeMethod('setUserDetails', {
      'email': email,
      'nickname': nickname,
      'phone': phone,
      'avatarUrl': avatarUrl,
      'company': company,
    });
  }

  ///  Set any custom fields
  Future<void> setCustomFields(Map<String, String> fields) async {
    _ensureConfigured();

    fields.forEach((k, v) {
      _channel.invokeMethod('setCustomField', {
        'key': k,
        'value': v,
      });
    });
  }

  /// Logout the user
  Future<void> logout() async {
    await _channel.invokeMethod('logout');
  }
}
