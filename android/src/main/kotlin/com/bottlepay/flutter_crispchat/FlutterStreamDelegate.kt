package com.bottlepay.flutter_crispchat

import android.os.Handler
import android.os.Looper
import io.flutter.plugin.common.BinaryMessenger


class FlutterStreamDelegate(binaryMessenger: BinaryMessenger) {
    private var onUpdateUnreadCountStream = FlutterStreamFactory(binaryMessenger, "onUpdateUnreadCount")
    private val uiThreadHandler: Handler = Handler(Looper.getMainLooper())

    private var unreadMessageCount = -1

    fun updateUnreadMessageCount(count: Int) {
        unreadMessageCount = count
        uiThreadHandler.post {
            onUpdateUnreadCountStream.sink?.success(count)
        }
    }
}