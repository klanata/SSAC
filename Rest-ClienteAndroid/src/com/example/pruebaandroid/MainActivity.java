package com.example.pruebaandroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//pantalla principal
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		
    }
    
    public void onBotonInicial(View view) {
    	Intent paso = new Intent(getApplicationContext(),HelloGoogleMaps.class);
   	 	paso.putExtra("catastrofeId", "1");
   	 	paso.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(paso);
    }
}
