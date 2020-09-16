package com.bottlepay.flutter_crispchat

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ChatView : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_up, 0);
        setContentView(R.layout.activity_chat_view)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.slide_down);
    }

}