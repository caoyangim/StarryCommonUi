package com.cy.starrycommonui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.cy.starrycommonui.R;
import com.cy.starrycommonui.fragment.StarryLayoutFragment;

/**
 * @author Duckbb
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, new StarryLayoutFragment());
        transaction.commit();
    }
}