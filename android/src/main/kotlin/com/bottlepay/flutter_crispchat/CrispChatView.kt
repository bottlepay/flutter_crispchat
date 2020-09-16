package com.bottlepay.flutter_crispchat

import android.content.Context
import android.widget.LinearLayout


class CrispChatView(context: Context?) : LinearLayout(context) {
    init {
        inflate(context, R.layout.crisp_window, this)
    }
}