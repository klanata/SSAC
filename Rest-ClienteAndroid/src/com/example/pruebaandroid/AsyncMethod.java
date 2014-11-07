package com.example.pruebaandroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncMethod extends AsyncTask<String, Void, String> {
//public class AsyncMethod extends AsyncTask<Void, Void, Void> {
	private static final String tag = "info";
	
	
	
	
	
	
	@Override
	protected String doInBackground(String... params)  {
		Log.i(tag,"entre async");
		// TODO Auto-generated method stub
		BufferedReader in = null;
		String data = null;
		String out = "";
		
		try{
		//	String uri ="http://10.0.2.2:8080/ServicioRest/catastrofe/personas/2";
		
			String uri ="http://10.0.2.2:8080/ServicioRest/catastrofe/personas/"+params[0];
			URL url = new URL(uri);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/text");
			InputStream xml = connection.getInputStream();
			
			InputStreamReader is = new InputStreamReader(xml);
			StringBuilder sb=new StringBuilder();
			BufferedReader br = new BufferedReader(is);
			String read = br.readLine();

			while(read != null) {
			    //System.out.println(read);
			    sb.append(read);
			    read =br.readLine();
			    	
			}
			//Toast.makeText(getApplicationContext(), "Sample Text", Toast.LENGTH_LONG).show();
			System.out.println(sb.toString());
			Log.i(tag,"resultado "+ sb.toString());
			
		
			return sb.toString();
			}
			catch(Exception e){
					e.printStackTrace();
					return "Hubo un error " +e.getMessage();
					
			}
	}
	
	   protected void onPostExecute(String result) {
		   //este metodo se llama automaticamente cuando termina el doinbackground
		   
		   System.out.print("El resultado es " + result );
	     }



}
