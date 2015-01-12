package com.example.pruebaandroid;

import java.util.Date;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//pantalla en la que se escribe la descripcion y se manda el pedido de ayuda
public class PedidoAyudaActivity extends Activity{

	
	private EditText descripcionText;
	private EditText idCatText;
	int idCat = 0;
	String coordenadaX = "1";
	String coordenadaY = "2";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		System.out.print("entre al pedido de ayuda");
		Log.i("entre segunda pantalla","oncreate del pedido");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido_ayuda);
        
        
        // 	muestroNick = (EditText) findViewById(R.id.textMostrarCatastrofe);  
        //  textoMuestra = (TextView) findViewById(R.id.textMostrarCatastrofe);
        //  textoMuestra.setText(message1);

	}

	public void onPedidoDeAyuda(View view){
		Log.i("2","inpedidodeayuda");
		descripcionText = (EditText) findViewById(R.id.descripcionText);
//		idCatText = (EditText) findViewById(R.id.idCatText);
//		params.put("idCatPDA",idCatText.getText().toString());
		RequestParams params = new RequestParams();
		params.put("descripcionPDA",descripcionText.getText().toString());
		
		
		invokeIngresarWS(params);
		TextView cambiaBoton = (TextView)findViewById(R.id.button1);
	 	cambiaBoton.setText("Solicitud Enviada");
	 	cambiaBoton.setEnabled(false);
		
	}
	
	//crearPedido(new Long(catastrofeId), descripcion, coordenadasX, coordenadasY, fechaPublicacion);
	//mando el pedido de ayuda con los parametros al rest para que llegue a la base
	 public void invokeIngresarWS(RequestParams params){
		 Log.i("entre invoke","invoke entre");
		//obtengo coordX, coordY y id de la catastrofe
	        Intent intent = getIntent();
	        
	        String coordenadaX = intent.getStringExtra("coordX");
	        String coordenadaY = intent.getStringExtra("coordY");
	        String idCat = intent.getStringExtra("catastrofeId"); 
	        
	        //Date hoy = new Date();
	       // hoy.getTime();
	        Log.i("x",intent.getStringExtra("coordX"));
	        Log.i("y",intent.getStringExtra("coordY"));
	        Log.i("idcat",intent.getStringExtra("catastrofeId"));
	        Log.i("descripcion",descripcionText.getText().toString());
     	AsyncHttpClient client = new AsyncHttpClient();
         //client.get("http://10.0.2.2:8080/ServicioRest/catastrofe/ayuda/pedirAyuda?catId="+
     		client.get("http://192.168.1.44:8080/ServicioRest/catastrofe/ayuda/pedirAyuda?catId="+ 
         idCat +"&des="+ descripcionText.getText().toString()+ "&coordX="+ coordenadaX
        		 + "&coordY=" + coordenadaY, new AsyncHttpResponseHandler() {
        	 	
         
         });
	 
	 }
}
