package com.ridvankabak.flickrgallery.ui.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ridvankabak.flickrgallery.ui.MainActivity.MainActivity;
import com.ridvankabak.flickrgallery.R;

public class SplashActivity extends AppCompatActivity implements SplashActivityContract.View {
    private SplashActivityPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPresenter = new SplashActivityPresenter(this);
        mPresenter.showSplash();

    }

    public void timer() {
        new Handler().postDelayed(() -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();

        },3000);
    }
}
