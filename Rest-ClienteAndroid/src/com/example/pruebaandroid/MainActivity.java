package com.example.pruebaandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView lblGotoRegister;
    private Button btnLogin;
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView loginErrorMsg;
	private static final String tag = "info";
	TextView httpStuff;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpex);
	
	
	
		httpStuff = (TextView) findViewById(R.id.tvHttp);
		Log.i(tag,"entre MAIN ACTIVITY");
		String returned;
	            
		try {
			//Async
			AsyncTask<Void, Void, String> s = new AsyncMethod().execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}
	        
}
	
	        

