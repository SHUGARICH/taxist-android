
package com.shugarich.taxist

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = TextView(this)
        text.text = "TAXI ASSISTANT\n\nПриложение запущено!"
        text.textSize = 24f
        setContentView(text)
    }
}
