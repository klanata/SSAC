package com.example.pruebaandroid;


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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

	private EditText personaId;
    private TextView error;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catastrofe);
		personaId = (EditText) findViewById(R.id.personaId);
    }

    // Cuando el usuario hace click en el botón, se llama al método AsyncTask.
    // Primero se recomienda verificar que haya conexión a internet.
    public void onCatastrofeSeleccionada(View view) {
        // Obtengo el id de la persona del input.
        String personaIdParam = personaId.getText().toString();
		// Se crea un objeto del tipo Param del Http Request y se le pasa el id.
        RequestParams params = new RequestParams();
        params.put("id", personaIdParam);
		//Para verificar que haya conexión a internet
        try{
        ConnectivityManager connMgr = (ConnectivityManager) 
            getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        }
        catch(Exception e){
        	System.out.println("error en el conectivity manager");
        }
       // if (networkInfo != null && networkInfo.isConnected()) {
            invokeWS(params);//Éste es el método que llama al servicio rest.
       // } else {
            //error.setText("No hay conexión");
        //}
    }


    
	 public void invokeWS(RequestParams params){
         // Se usa el objeto AsyncHttpClient para llamar al servicio.
         AsyncHttpClient client = new AsyncHttpClient();
         client.get("http://10.0.2.2:8080/ServicioRest/catastrofe/personas/"+ personaId.getText().toString(),new AsyncHttpResponseHandler() {//acá hay que cambiar a nuestra url
             // When the response returned by REST has Http response code '200'
             @Override
             public void onSuccess(String response) {
                 try {
                          // JSON Object
                         JSONObject obj = new JSONObject(response);
                         System.out.println((obj.get("nick").toString()));
                         
                         // When the JSON response has status boolean value assigned with true
                         if(obj.get("nick") != null){
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
                         }
                 } catch (JSONException e) {
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
	
	public void nuevoPedidoAyuda(View view){
        Intent pedidoAyudaIntent = new Intent(getApplicationContext(),PedidoAyudaActivity.class);
        // Clears History of Activity
        pedidoAyudaIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pedidoAyudaIntent);
    }
 
}
