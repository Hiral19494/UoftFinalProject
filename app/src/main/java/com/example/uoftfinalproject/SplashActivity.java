package com.example.uoftfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d("SplashActivity","SplashActivity run");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create a.n Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                Log.d("new activity start","next page Login");
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
