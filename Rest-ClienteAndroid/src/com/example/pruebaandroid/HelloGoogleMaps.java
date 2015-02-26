package com.example.pruebaandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.MapFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.google.android.gms.maps.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class HelloGoogleMaps extends Activity {
	
	private static final long LOCATION_REFRESH_TIME = 0;
	private GoogleMap map;
	public HashMap<String,String> hashCatastrofes = new HashMap() ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
		map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		LocationManager locationManagers = (LocationManager) getSystemService(LOCATION_SERVICE);
		map.setMyLocationEnabled(true);
		Location myLocation = map.getMyLocation();
		LatLng aqui = new LatLng(-34.917826, -56.167063);
		Criteria cri = new Criteria();
		String provider = locationManagers.getBestProvider(cri, true);
		Location posGps = locationManagers.getLastKnownLocation(provider);
		
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
	        // redraw the marker when get location update.
				drawMarker(location, "Se encuentra aquí!");
			}
		};
		
		
		//if(posGps!=null){
			//drawMarker(posGps);	
			
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(aqui, 10);
			map.animateCamera(update);
		
		//}
		
		
		
		//map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		invokeWS();
		map.setOnMarkerClickListener(new OnMarkerClickListener(){

			@Override
			public boolean onMarkerClick(Marker arg0) {
				
				// TODO Auto-generated method stub
				
				 String idSnippet = arg0.getSnippet();
				 if (arg0.getSnippet().compareTo("")!=0)
				 {
					 hashCatastrofes.put(arg0.getId(),idSnippet);
					 arg0.setSnippet("");
				 }
				 arg0.showInfoWindow();
				
				return true;
			}
			
			
		});
		
	  	map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker arg0) {
				// TODO Auto-generated method stub
				 String idCatastrofe = hashCatastrofes.get(arg0.getId());
				//va a la siguiente pantalla y le pasa el idCatastrofe y las coord gps
				 Intent pedidoAyudaIntent = new Intent(getApplicationContext(),PedidoAyudaActivity.class);
				 pedidoAyudaIntent.putExtra("catastrofeId", idCatastrofe);
				 Location location = map.getMyLocation();
				 if (location!=null)
				 {
				 pedidoAyudaIntent.putExtra("coordX", String.valueOf(location.getLatitude()));
				 pedidoAyudaIntent.putExtra("coordY", String.valueOf(location.getLongitude()));
				 }
				 else
				 {
					 pedidoAyudaIntent.putExtra("coordX", "0.001");
					 pedidoAyudaIntent.putExtra("coordY", "0.001");
				 }
				
				 pedidoAyudaIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			     startActivity(pedidoAyudaIntent);
			}
	    });
		
		}//del oncreate
	
	//probando poligono
	public void dibujoPoligono(List<LatLng> list){
		 PolygonOptions opcionesPoligono = new PolygonOptions();
		 //para cada latlng de la lista de latlngs del poligono
			for(LatLng coord:list)
			{
			  
				opcionesPoligono.add(coord);
//			     .add(new LatLng(-34.899422, -56.146639))
//			     .add(new LatLng(-34.899105, -56.144654))
//			     .add(new LatLng(-34.902536, -56.143571))
//			     .add(new LatLng(-34.902827, -56.146103))
//			     .add(new LatLng(-34.899422, -56.146639));
			   
			  // opcionesPoligono.
			   //mMap.clear();			   
			
			}
			   Polygon poligono = map.addPolygon(opcionesPoligono);  
			   //poligono.hashCode();
			   
			   poligono.setFillColor(0x69FF0000); // Relleno del polígono rojo medio transparente 0x7FFF0000
			   
			   poligono.setStrokeColor(Color.WHITE); // Bordes del polígono
			   Log.d("muestro", "Poligono cargado en el mapa.");
			
		  
		
		   
	}
		
	
	
	
	
	
	public void drawMarker(Location location, String titulo) {
		if(location!=null){
		map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).title(titulo));
	    
		}
	}
	
	//marca las catastrofes
	//recibo arreglo de poligono
	public void drawMarkerLatLong(String latitudX, String longitudY, String titulo, String idCatastrofe) {
		if (longitudY!= null && latitudX!=null){
			if (longitudY.compareTo("")!=0 && latitudX.compareTo("")!=0){
				map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
				try {
			    MarkerOptions markerOption = new MarkerOptions().position(new LatLng(Double.parseDouble(latitudX),Double.parseDouble(longitudY))).title(titulo).snippet(idCatastrofe);
				 
			    map.addMarker(markerOption);
			    //llamo a la funcion del poligono pasandole arreglo
				
				}
				catch(Exception e)
				{
					Log.e("Error Double", "Error al convertir a double");
				}
			}
		}
	}
	
	
	/////////////////nuevo para que refresque
	public void onActualizar(View view){
		Log.i("3","entre a actualizar mapa");
		
		invokeWS();
		TextView cambiaBoton = (TextView)findViewById(R.id.button1);
	 	cambiaBoton.setText("Actualizado");
	 
		
	}
	
	
	
	
	
	
	//***************************************************************************//
	public void invokeWS(){
		Log.i("Entro al invoke","Entro al invoke");
        AsyncHttpClient client = new AsyncHttpClient();
       //client.get("http://10.0.2.2:8080/ServicioRest/catastrofe/catastrofes",new AsyncHttpResponseHandler() {//acá hay que cambiar a nuestra url
        client.get("http://192.168.43.91:8080/ServicioRest/catastrofe/catastrofes",new AsyncHttpResponseHandler() {//acá hay que cambiar a nuestra url
           
        	// When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                try {
                	Log.i("SuccesResponse","Pudo llamar al ws");
               	 
                         // JSON Object
                        JSONObject obj = new JSONObject(response);
                         
                        JSONArray arregloCat = obj.getJSONArray("catastrofe");
                        List<String> arreglo= new ArrayList<String>();
                     	
                        //LISTO CATAS en logcat se ven los nombres de las catastrofes
                        for(int i=0;i< arregloCat.length();i++){
                        	
                         String latitudX = obj.getJSONArray("catastrofe").getJSONObject(i).get("coordenadasX").toString();
                         String longitudY =  obj.getJSONArray("catastrofe").getJSONObject(i).get("coordenadasY").toString();
                         String  titulo =obj.getJSONArray("catastrofe").getJSONObject(i).get("nombreEvento").toString();
                         String  idCatastrofe =obj.getJSONArray("catastrofe").getJSONObject(i).get("id").toString();
                         //String poligono = obj.getJSONObject("catastrofe").getJSONObject(i).get("").toString();
                         
                         Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).toString());
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("nombreEvento").toString());
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("id").toString());
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("coordenadasX").toString());
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("coordenadasY").toString());
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("poligono").toString());
          
                     
                       	 
                       	JSONArray puntos = (JSONArray) obj.getJSONArray("catastrofe").getJSONObject(i).get("poligono");
                       	
                       	
                       	 List<LatLng> list = new ArrayList<LatLng>();
                       	 for (int j = 0; j< puntos.length();j++){
                       		 String lat = puntos.get(j).toString();
                       		 j++;
                       		 String lon = puntos.get(j).toString();
                           	 Log.i("coords", lat + " y " + lon);
                       		 LatLng latLngObjeto = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));
                       		 list.add(latLngObjeto);
                       	 }
                       	 HelloGoogleMaps.this.dibujoPoligono(list);
                       	 HelloGoogleMaps.this.drawMarkerLatLong(latitudX,longitudY,titulo,idCatastrofe);
                       	 ////aca le paso el json del poligono
                     

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
            	
            	Log.i("Fallo "+statusCode,"Contenido: " +content + "Error: " + error.getMessage());
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
}

	
	