package com.example.pruebaandroid;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.google.android.gms.maps.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class HelloGoogleMaps extends Activity {
	
	private static final long LOCATION_REFRESH_TIME = 0;
	private final LatLng LOCATION_SURRREY = new LatLng(49.27645, -122.917587);
	private final LatLng LOCATION_BURNABY = new LatLng(49.27645, -122.917587);
	private GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
		map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		LocationManager locationManagers = (LocationManager) getSystemService(LOCATION_SERVICE);
		map.setMyLocationEnabled(true);
		
		Criteria cri = new Criteria();
		String provider = locationManagers.getBestProvider(cri, true);
		Location posGps = locationManagers.getLastKnownLocation(provider);
		
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
	        // redraw the marker when get location update.
				drawMarker(location, "Se encuentra aqu�!");
			}
		};
	
		if(posGps!=null){
			//drawMarker(posGps);	
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(posGps.getLatitude(),posGps.getLongitude()), 9);
			map.animateCamera(update);
		}
		
		map.addMarker(new MarkerOptions().position(LOCATION_SURRREY).title("Centro de Soporte"));
		
		//map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		invokeWS();
		
	  	map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker arg0) {
				// TODO Auto-generated method stub
				
				//va a la siguiente pantalla y le pasa el idCatastrofe y las coord gps
				 Intent pedidoAyudaIntent = new Intent(getApplicationContext(),PedidoAyudaActivity.class);
				 pedidoAyudaIntent.putExtra("catastrofeId", "9");
				 pedidoAyudaIntent.putExtra("coordX", "-34.909654");
				 pedidoAyudaIntent.putExtra("coordY", "-56.202349");
				 /*//chancho
				 pedidoAyudaIntent.putExtra("catastrofeId", "9");
				 pedidoAyudaIntent.putExtra("coordX", "-34.909654");
				 pedidoAyudaIntent.putExtra("coordY", "-56.202349");*/
				 pedidoAyudaIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			     startActivity(pedidoAyudaIntent);
			}
	    });
		
		}//del oncreate
	
	
	
	
	public void drawMarker(Location location, String titulo) {
		if(location!=null){
		map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).title(titulo));
		
		}
	}
	
	//marca las catastrofes
	public void drawMarkerLatLong(String latitudX, String longitudY, String titulo) {
		if (longitudY!= null && latitudX!=null){
			if (longitudY.compareTo("")!=0 && latitudX.compareTo("")!=0){
				map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
				try {
				map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latitudX),Double.parseDouble(longitudY))).title(titulo));
				
				}
				catch(Exception e)
				{
					Log.e("Error Double", "Error al convertir a double");
				}
			}
		}
	}
	
	//***************************************************************************//
	public void invokeWS(){
		Log.i("Entro al invoke","Entro al invoke");
        AsyncHttpClient client = new AsyncHttpClient();
       //client.get("http://10.0.2.2:8080/ServicioRest/catastrofe/catastrofes",new AsyncHttpResponseHandler() {//ac� hay que cambiar a nuestra url
        client.get("http://192.168.1.43:8080/ServicioRest/catastrofe/catastrofes",new AsyncHttpResponseHandler() {//ac� hay que cambiar a nuestra url
           
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
                         
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("nombreEvento").toString());
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("id").toString());
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("coordenadasX").toString());
                       	 Log.i("hola", obj.getJSONArray("catastrofe").getJSONObject(i).get("coordenadasY").toString());
                       	 
                       	 HelloGoogleMaps.this.drawMarkerLatLong(latitudX,longitudY,titulo);
                       	 ////
                     

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