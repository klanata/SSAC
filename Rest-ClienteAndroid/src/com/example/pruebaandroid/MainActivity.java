package com.example.pruebaandroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

//pantalla principal
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.
            	Intent paso = new Intent(getApplicationContext(),HelloGoogleMaps.class);
            	paso.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	startActivity(paso);
                finish();
            }
        }, 1800);
    }
    
   
}
