
package com.shugarich.taxist;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("TAXI ASSISTANT\n\nПриложение запущено!");
        text.setTextSize(24);
        text.setTextColor(Color.rgb(255, 193, 7));
        text.setGravity(Gravity.CENTER);
        text.setBackgroundColor(Color.rgb(18, 18, 18));

        setContentView(text);
    }
}
