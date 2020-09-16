package com.bottlepay.flutter_crispchat

import io.flutter.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel

class FlutterStreamFactory(binaryMessenger: BinaryMessenger, streamName: String) : EventChannel.StreamHandler {
    init {
        Log.d(tag,"FlutterStreamFactory | com.bottlepay.flutter_crispchat/streams/$streamName")
        EventChannel(binaryMessenger, "com.bottlepay.flutter_crispchat/streams/$streamName").setStreamHandler(this)
    }

    var sink: EventChannel.EventSink? = null

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        sink = events
    }

    override fun onCancel(arguments: Any?) {
        sink = null
    }
}