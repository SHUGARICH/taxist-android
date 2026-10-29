package com.shugarich.taxist

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.graphics.PixelFormat
import android.graphics.Color
import android.view.Gravity
import android.view.WindowManager
import android.view.View
import android.widget.TextView
import android.widget.LinearLayout

class OrderReaderService : AccessibilityService() {

    private var windowManager: WindowManager? = null
    private var overlay: View? = null

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        val texts = mutableListOf<String>()
        fun read(node: android.view.accessibility.AccessibilityNodeInfo?) {
            if (node == null) return
            node.text?.toString()?.let { texts.add(it) }
            for (i in 0 until node.childCount) {
                read(node.getChild(i))
            }
        }
        read(rootInActiveWindow)

        val allText = texts.joinToString(" ")
        val hasOrder = allText.contains("Принять", true) ||
                allText.contains("Комфорт", true) ||
                allText.contains("Эконом", true)

        if (hasOrder) showOverlay(allText) else hideOverlay()
    }

    override fun onInterrupt() {
        hideOverlay()
    }

    override fun onServiceConnected() {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
    }

    private fun showOverlay(text: String) {
        if (overlay != null) return

        val passengerFare = Regex("""(\d{2,5})\s*₽""")
            .find(text)?.groupValues?.get(1)?.toIntOrNull()

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(22, 16, 22, 16)
            setBackgroundColor(Color.argb(190, 20, 22, 28))
        }

        val title = TextView(this).apply {
            setTextColor(Color.WHITE)
            textSize = 18f
            text = "Пассажир: ${passengerFare?.let { "≈ $it ₽" } ?: "считываю цену…"}"
        }

        val info = TextView(this).apply {
            setTextColor(Color.GREEN)
            textSize = 16f
            text = "Taxi Assistant\nОценка дохода и ₽/ч — настройка"
        }

        root.addView(title)
        root.addView(info)

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 12
            y = 150
            alpha = 0.88f
        }

        try {
            windowManager?.addView(root, params)
            overlay = root
        } catch (_: Exception) {
        }
    }

    private fun hideOverlay() {
        try {
            overlay?.let { windowManager?.removeView(it) }
        } catch (_: Exception) {
        }
        overlay = null
    }
}
