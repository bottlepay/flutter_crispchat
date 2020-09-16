package com.bottlepay.flutter_crispchat

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ChatView : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_view)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_down_dialog,R.anim.slide_down_dialog);
    }

}