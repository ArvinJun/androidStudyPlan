package com.arviv.skinTest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatViewInflater;
import androidx.core.app.ActivityCompat;
import androidx.core.view.LayoutInflaterCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.arviv.skinTest.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SkinLayoutInflaterFactory mSkinLayoutInflaterFactory;

    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSkinLayoutInflaterFactory = new SkinLayoutInflaterFactory();
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), (LayoutInflater.Factory2) mSkinLayoutInflaterFactory);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        mButton = (Button) findViewById(R.id.btn_change_skin);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSkinAndChangeTheme(mSkinLayoutInflaterFactory);
            }
        });

    }

    public  void loadSkinAndChangeTheme(SkinLayoutInflaterFactory layoutInflaterFactory) {
        boolean result =   SkinManager.getInstance(getApplicationContext()).loadSkin(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Ringtones/" + "one.skin");
        if (result) {
            layoutInflaterFactory.applySkinAll();
        }
    }
}