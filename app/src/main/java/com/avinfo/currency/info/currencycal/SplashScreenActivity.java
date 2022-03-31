package com.avinfo.currency.info.currencycal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

public class SplashScreenActivity extends  AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
//        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(1024,1024);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                GoNext();
            }
        }, 4000);

    }

    public void GoNext() {

        Intent i = new Intent(SplashScreenActivity.this, CurrencyConMainActivity.class);
        startActivity(i);
        finish();

    }

}
