package com.example.memorygame.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memorygame.R;
import com.example.memorygame.ui.game.GameActivity;

import butterknife.OnClick;

import static butterknife.ButterKnife.bind;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind(this);
    }


    @OnClick(R.id.btn_start)
    public void startGame() {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }
}
