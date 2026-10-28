
package com.shugarich.taxist

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 28, 28, 28)
            setBackgroundColor(Color.rgb(18, 18, 18))
        }

        val title = TextView(this).apply {
            text = "ТАКСИСТ"
            textSize = 30f
            setTextColor(Color.rgb(255, 193, 7))
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "Помощник водителя"
            textSize = 18f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 32)
        }

        val status = TextView(this).apply {
            text = "Версия 1.0\\n\\nАнализ заказов — скоро"
            textSize = 16f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        root.addView(title)
        root.addView(subtitle)
        root.addView(status)
        setContentView(root)
    }
}
