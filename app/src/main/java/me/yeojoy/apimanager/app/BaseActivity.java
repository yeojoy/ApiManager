package me.yeojoy.apimanager.app;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {
    protected TextView textViewResult = null;

    public void logResult(String message) {
        if (textViewResult != null) {
            textViewResult.append("\n");
            textViewResult.append("message");
            textViewResult.append(" >>> ");
            textViewResult.append(message);
        }
    }
}
