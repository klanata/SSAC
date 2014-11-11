package com.example.pruebaandroid;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PedidoAyudaActivity extends Activity{

	private EditText personaText;
	private TextView textoMuestra;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		System.out.print("entre al pedido de ayuda");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido_ayuda);
        Intent intent = getIntent();
        
        String message = intent.getStringExtra("nombrePersona");
      // muestroNick = (EditText) findViewById(R.id.editText1);
        
      textoMuestra = (TextView) findViewById(R.id.textMostrarNick);

      textoMuestra.setText(message);

     
	}

	public void onPedidoDeAyuda(View view){
		personaText = (EditText) findViewById(R.id.personaText);
		RequestParams params = new RequestParams();
		params.put("nick",personaText.getText().toString());
		
		invokeIngresarWS(params);
		
	}
	
	 public void invokeIngresarWS(RequestParams params){
     	AsyncHttpClient client = new AsyncHttpClient();
         client.get("http://10.0.2.2:8080/ServicioRest/catastrofe/personas/crearUsuario?num="+ personaText.getText().toString(),
        		 new AsyncHttpResponseHandler() {//acá hay que cambiar a nuestra url
         
         });
	 }
}
