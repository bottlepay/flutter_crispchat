package com.bottlepay.flutter_crispchat

import android.app.Application
import androidx.annotation.NonNull
import io.flutter.Log

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry

const val tag = "flutter_crispchat"

class FlutterCrispChatPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
  private var channel: MethodChannel? = null
  private var application: Application? = null
  private var flutterAcquireIoPluginImpl: FlutterCrispChatPluginImpl? = null

  // Used in v2 initialization.
  constructor() {
    Log.d(tag,"constructor v2")
  }

  // Used in v1 binding registration
  constructor(_channel: MethodChannel, _application: Application, _binaryMessenger: BinaryMessenger) : this() {
    Log.d(tag,"constructor v1")

    channel = _channel
    application = _application
    flutterAcquireIoPluginImpl = FlutterCrispChatPluginImpl(_binaryMessenger)
  }

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    Log.d(tag,"onAttachedToEngine | $flutterAcquireIoPluginImpl")
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "com.bottlepay.flutter_crispchat")
    channel?.setMethodCallHandler(this);
    flutterAcquireIoPluginImpl = FlutterCrispChatPluginImpl(flutterPluginBinding.binaryMessenger)
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  companion object {
    @JvmStatic
    fun registerWith(registrar: PluginRegistry.Registrar) {
      Log.d(tag,"registerWith")

      val channel = MethodChannel(registrar.messenger(), "com.bottlepay.flutter_crispchat")
      channel.setMethodCallHandler(FlutterCrispChatPlugin(channel, registrar.activity().application, registrar.messenger()))
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    Log.d(tag,"onDetachedFromEngine")

    channel?.setMethodCallHandler(null)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    Log.d(tag,"handle: $flutterAcquireIoPluginImpl")

    when ((call.method)) {
      "configure" -> flutterAcquireIoPluginImpl?.configure(call, result)
      "showChat" -> flutterAcquireIoPluginImpl?.showChat(call, result)
      "setUserDetails" -> flutterAcquireIoPluginImpl?.setUserDetails(call, result)
      "setCustomField" -> flutterAcquireIoPluginImpl?.setCustomField(call, result)
      "logout" -> flutterAcquireIoPluginImpl?.logout(call, result)
      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromActivity() {
    Log.d(tag,"onDetachedFromActivity")

    application = null
    flutterAcquireIoPluginImpl?.setApplicationRef(null)
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    Log.d(tag,"onReattachedToActivityForConfigChanges")

    application = binding.activity.application
    flutterAcquireIoPluginImpl?.setApplicationRef(application)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    Log.d(tag,"onAttachedToActivity")

    application = binding.activity.application
    flutterAcquireIoPluginImpl?.setApplicationRef(application)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    Log.d(tag,"onDetachedFromActivityForConfigChanges")

    application = null
    flutterAcquireIoPluginImpl?.setApplicationRef(null)
  }
}
