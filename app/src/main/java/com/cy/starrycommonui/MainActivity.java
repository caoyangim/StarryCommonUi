package com.cy.starrycommonui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cy.strarryui.widget.StarryLinearLayout;

/**
 * @author Duckbb
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final StarryLinearLayout ll = findViewById(R.id.ll_common);
        ll.setChangeAlphaWhenPress(true);
        ll.setChangeAlphaWhenDisable(true);
    }
}