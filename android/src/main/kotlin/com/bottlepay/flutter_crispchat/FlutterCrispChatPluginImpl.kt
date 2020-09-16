package com.bottlepay.flutter_crispchat

import android.app.Application
import android.content.Intent
import im.crisp.sdk.Crisp
import io.flutter.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.Result

class FlutterCrispChatPluginImpl(_binaryMessenger: BinaryMessenger) {
    private var application: Application? = null
    private var binaryMessenger: BinaryMessenger = _binaryMessenger

    // AcquireIO delegate implementation
    private val streamDelegate = FlutterStreamDelegate(binaryMessenger)

    init {
        Log.d(tag, "FlutterCrispChatPluginImpl | $application | $binaryMessenger")
    }

    fun setApplicationRef(_application: Application?) {
        application = _application
    }

    fun configure(call: MethodCall, result: Result) {
        val websiteId = call.argument<String>("websiteId")

        Crisp.initialize(application?.applicationContext)
        Crisp.getInstance().websiteId = websiteId
        result.success(null)
    }

    fun showChat(call: MethodCall, result: Result) {
        if (application != null) {
            var intent = Intent(application!!.applicationContext, ChatView::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            // Return back to Flutter to unlock the UI
            result.success(null)

            // Launch the chat UI
            application!!.applicationContext.applicationContext.startActivity(intent)
        }

    }

    fun setUserDetails(call: MethodCall, result: Result) {
        val email = call.argument<String>("email")
        val nickname = call.argument<String>("nickname")
        val phone = call.argument<String>("phone")
        val avatarUrl = call.argument<String>("avatarUrl")

        Crisp.User.setEmail(email)
        Crisp.User.setNickname(nickname)
        Crisp.User.setPhone(phone)
        Crisp.User.setAvatar(avatarUrl)
        result.success(null)
    }

    fun setCustomField(call: MethodCall, result: Result) {
        val key = call.argument<String>("key")
        val value = call.argument<String>("value")

        Crisp.Session.setData(key, value)
        result.success(null)
    }

    fun logout(call: MethodCall, result: Result) {
        Crisp.Session.reset()
        result.success(null)
    }
}