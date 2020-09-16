package com.bottlepay.flutter_crispchat

import android.app.Application
import im.crisp.sdk.Crisp
import io.flutter.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.Result
import org.json.JSONException
import org.json.JSONObject

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
        result.success(null)
    }

    fun setUserDetails(call: MethodCall, result: Result) {
        result.success(null)
    }

    fun setCustomField(call: MethodCall, result: Result) {
        result.success(null)
    }

    fun logout(call: MethodCall, result: Result) {
        result.success(null)
    }
}