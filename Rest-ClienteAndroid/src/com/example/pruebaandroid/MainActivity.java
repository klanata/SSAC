package com.example.pruebaandroid;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


//pantalla en la que se elige la catastrofe
public class MainActivity extends Activity {

	private EditText catastrofeId;
    private TextView error;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catastrofe);
		//catastrofeId = (EditText) findViewById(R.id.catastrofeId);
		
		//llamo a rest para obtener toda la lista de catastrofes ni bien arranca
		invokeWS();
	
    }

    /*
    	public void onCatastrofeSeleccionada(View view) {
        // Obtengo el id de la persona del input.
        String personaIdParam = catastrofeId.getText().toString();
        
		// Se crea un objeto del tipo Param del Http Request y se le pasa el id.
        RequestParams params = new RequestParams();
        params.put("id", personaIdParam);
    }*/

	 public void invokeWS(){
         
         AsyncHttpClient client = new AsyncHttpClient();
         client.get("http://10.0.2.2:8080/ServicioRest/catastrofe/catastrofes",new AsyncHttpResponseHandler() {//acá hay que cambiar a nuestra url
             // When the response returned by REST has Http response code '200'
             @Override
             public void onSuccess(String response) {
                 try {
                	 
                          // JSON Object
                         JSONObject obj = new JSONObject(response);
                          
                         JSONArray arregloCat = obj.getJSONArray("catastrofe");
                         List<String> arreglo= new ArrayList<String>();
                         
                         //LISTO CATAS en logcat se ven los nombres de las catastrofes
                         for(int i=0;i< arregloCat.length();i++){
                        	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("nombreEvento").toString());
                        	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("id").toString());

                        	 //los agrego al JsonArray para desp listar de aca
                        	arreglo.add(obj.getJSONArray("catastrofe").getJSONObject(i).get("nombreEvento").toString());
                         }
                         
                         
                 }       
                 catch (JSONException e) {
                     // TODO Auto-generated catch block
                     Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]! "+ response.toString() , Toast.LENGTH_LONG).show();
                     e.printStackTrace();

                 }
             }
             // When the response returned by REST has Http response code other than '200'
             @Override
             public void onFailure(int statusCode, Throwable error,
                 String content) {
                 // Hide Progress Dialog 
                 //prgDialog.hide();
                 // When Http response code is '404'
                 if(statusCode == 404){
                     Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                 } 
                 // When Http response code is '500'
                 else if(statusCode == 500){
                     Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                 } 
                 // When Http response code other than 404, 500
                 else{
                     //Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                	 Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                 }
             }
         });
    }
	 
	//ACA tendria que haber un al seleccionar  de la lista o combobox, mandar al intent de abajo
	public void onCatastrofeSeleccionada(View view) {
	
     //paso el id de la catastrofe a la segunda pantalla
    // if(obj.getJSONArray("catastrofe").getJSONObject(0).get("id").toString() != null){
     
    	 Intent pedidoAyudaIntent = new Intent(getApplicationContext(),PedidoAyudaActivity.class);
    	 
    	// String message = obj.getJSONArray("catastrofe").getJSONObject(0).get("id").toString();
    	// pedidoAyudaIntent.putExtra("catastrofeId", message);
    	 pedidoAyudaIntent.putExtra("catastrofeId", "1");

    	 pedidoAyudaIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         startActivity(pedidoAyudaIntent);
      
     //} //Else display error message
    /* else{
         error.setText(obj.getString("error_msg"));
         Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
    
     }*/
     
     
     
   //  Spinner spinnerCat = (Spinner) findViewById(R.id.spinner1) 
     //ArrayAdapter<String> dataAdapter = ArrayAdapter.createFromResource(this, arreglo, android.R.layout.simple_spinner_item);
    //		 	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
     //    	android.R.layout.simple_spinner_item, arreglo);
    //     		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		
     
     
     
     
    // String pageName = obj.getJSONObject("catastrofes").getJSONObject("catastrofe").getString("id");
    //JSONArray arr = obj.getJSONArray("posts");
    // for (int i = 0; i < arr.length(); i++)
    // {
         //String post_id = arr.getJSONObject(i).getString("post_id");
     
    // }
     
     
  
    /* if(obj.get("nick") != null){
    	 //Preparo los datos para mandar a la siguiente pantalla
    	 Intent pedidoAyudaIntent = new Intent(getApplicationContext(),PedidoAyudaActivity.class);
    	 
    	 String message = (obj.get("nick").toString());
    	 pedidoAyudaIntent.putExtra("nombrePersona", message);

    	 pedidoAyudaIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         startActivity(pedidoAyudaIntent);
     } 
     // Else display error message
     else{
         error.setText(obj.getString("error_msg"));
         Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
     */
//     }
	}
	
	public void nuevoPedidoAyuda(View view){
        Intent pedidoAyudaIntent = new Intent(getApplicationContext(),PedidoAyudaActivity.class);
        // Clears History of Activity
        pedidoAyudaIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pedidoAyudaIntent);
    }
 
}
