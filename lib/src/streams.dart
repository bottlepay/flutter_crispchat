import 'dart:async';

import 'package:flutter/services.dart';

/// Contains all of the streams to receive events from the platform plugin. It will
/// type the enums to the dart types and pass all events to the dart event streams.
class StreamHandler {
  StreamHandler() {
    _onUpdateUnreadCount
        .receiveBroadcastStream()
        .listen((event) => _onUpdateUnreadCountController.sink.add(event));
  }

  final _onUpdateUnreadCount = const EventChannel(
      'com.bottlepay.flutter_crispchat/streams/onUpdateUnreadCount');
  final _onUpdateUnreadCountController = StreamController<int>();
  Stream<int> get onUpdateUnreadCount =>
      _onUpdateUnreadCountController.stream.asBroadcastStream();

  /// Call this to clean up streams
  void cleanup() {
    _onUpdateUnreadCountController.close();
  }
}
