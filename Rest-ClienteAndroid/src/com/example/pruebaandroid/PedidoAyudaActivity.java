package com.example.pruebaandroid;

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
	private TextView textoMuestra;
	private EditText idCatText;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		System.out.print("entre al pedido de ayuda");
		Log.i("entre segunda pantalla","oncreate del pedido");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido_ayuda);
        
        Intent intent = getIntent();
      //	int message1 = Integer.parseInt(intent.getStringExtra("catastrofeId"));
      // muestroNick = (EditText) findViewById(R.id.textMostrarCatastrofe);
        
        //muestro la catastrofe seleccionada
    //  textoMuestra = (TextView) findViewById(R.id.textMostrarCatastrofe);
    //  textoMuestra.setText(message1);

	}

	public void onPedidoDeAyuda(View view){
		descripcionText = (EditText) findViewById(R.id.descripcionText);
		idCatText = (EditText) findViewById(R.id.idCatText);
		RequestParams params = new RequestParams();
		
		params.put("idCatPDA",idCatText.getText().toString());
		params.put("descripcionPDA",descripcionText.getText().toString());
		
		invokeIngresarWS(params);
		TextView cambiaBoton = (TextView)findViewById(R.id.button1);
	 	cambiaBoton.setText("Enviada");
		
	}
	
	//mando el pedido de ayuda con los parametros al rest
	 public void invokeIngresarWS(RequestParams params){
     	AsyncHttpClient client = new AsyncHttpClient();
         client.get("http://10.0.2.2:8080/ServicioRest/catastrofe/ayuda/pedirAyuda?catId="+
        		 idCatText.getText().toString() +"&des="+ descripcionText.getText().toString(),
        		 new AsyncHttpResponseHandler() {
        	 	
         
         });
	 
	 }
}
