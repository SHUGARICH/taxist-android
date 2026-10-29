
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

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.gravity = Gravity.CENTER
        root.setPadding(28, 28, 28, 28)
        root.setBackgroundColor(Color.rgb(18, 18, 18))

        val title = TextView(this)
        title.text = "ТАКСИСТ"
        title.textSize = 30f
        title.setTextColor(Color.rgb(255, 193, 7))
        title.gravity = Gravity.CENTER

        val info = TextView(this)
        info.text = "Помощник водителя\n\nПервый запуск успешен!"
        info.textSize = 18f
        info.setTextColor(Color.WHITE)
        info.gravity = Gravity.CENTER
        info.setPadding(0, 24, 0, 0)

        root.addView(title)
        root.addView(info)
        setContentView(root)
    }
}
