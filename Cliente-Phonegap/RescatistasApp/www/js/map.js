
'use strict';
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


var mapa = {

    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        //app.receivedEvent('deviceready');
        navigator.geolocation.getCurrentPosition(mapa.onSuccess, mapa.onError);
    },

    onSuccess: function(position){

        var listaPedidosPendientes = new Array();
        //$.ajax({url:"http://192.168.0.100:8080/ServicioRest/catastrofe/rescatista/verPendientes", //Emulador Android - llamada al rest
        $.ajax({url:"http://172.16.102.89:8080/ServicioRest/catastrofe/rescatista/verPendientes", //Utu
        //$.ajax({url:"http://10.0.2.2:8080/ServicioRest/catastrofe/rescatista/verPendientes", //Emulador Android - llamada al rest
        //$.ajax({url:"http://10.0.2.2/RescatistasApp/www/planesEmergenciaDB.js",//Emulador Android - llamada de prueba
        //$.ajax({url:"http://192.168.0.104/RescatistasApp/www/planesEmergenciaDB.js",//Android nativo, red local - llamada de prueba
        //$.ajax({url:"http://localhost/RescatistasApp/www/planesEmergenciaDB.js",//Web - llamada de prueba
            success:function(response) {
                //alert("Success");
                //useReturnData(response);
                listaPedidosPendientes = response.planesPendientesRescatistaRest;
                window.localStorage.setItem("listaPedidosStorage", listaPedidosPendientes);
                //alert(window.localStorage.getItem("listaPedidosStorage"));
                //listaPedidosPendientes = response.planesPendientesRescatistaRests;
                //console.log(listaPedidosPendientes);
                //document.getElementById("login").style.display = 'none';
               // document.getElementById("listaPendientes").style.display = 'initial';
                //scope.$apply();

                var longitude = position.coords.longitude;
                var latitude = position.coords.latitude;
                var latLong = new google.maps.LatLng(latitude, longitude);

                var mapOptions = {
                    center: latLong,
                    zoom: 12,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                var map =  new google.maps.Map(document.getElementById("geolocation"),mapOptions);
                //alert("lo que hay adentro " + listaPedidosPendientes[0].coordPedidoAyudaX);

                //Posicion del rescatista
                var marker = new google.maps.Marker({
                position: latLong,
                map: map,
                title:"Usted esta aqui",
                icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
                });
                
                //cargar las ubicaciones de los pedidos de ayuda en el mapa
                var marcador;
                for (var i = 0; i < listaPedidosPendientes.length; i++) {  
                    marcador = new google.maps.Marker({
                        position: new google.maps.LatLng(listaPedidosPendientes[i].coordPedidoAyudaX, listaPedidosPendientes[i].coordPedidoAyudaY),
                        map: map
                    });

                    //Para guardar datos en el marcador
                    marcador.set('IdPedidoAyudaActual', listaPedidosPendientes[i].idEstadoRescatista);
                    marcador.set('descripcionPedidoAyudaActual', listaPedidosPendientes[i].descripcion);

                    //para agregar evento onclick a los markers
                    google.maps.event.addListener(marcador, 'click', function() {
                        var IdActual = this.get('IdPedidoAyudaActual');
                        var descripcionActual = this.get('descripcionPedidoAyudaActual');
                        //alert(descripcionActual);
                        //window.localStorage.setItem("IdPedidoAyudaActual", IdActual);
                        //window.localStorage.setItem("descripcionPedidoAyudaActual", descripcionActual);
                        //alert(window.localStorage.getItem("IdPedidoAyudaActual"));
                        
                        document.getElementById("descripcion").value = descripcionActual;
                        document.getElementById("idPedidoAyudaActual").value = IdActual;
                        document.getElementById("listaPendientes").style.display = 'none';
                        document.getElementById("pedidoAyudaDetalle").style.display = 'initial';
                    });
                }
               
            },
            error:function (request, status, error) {
                alert("Error" + request.responseText);
            },
            dataType:"json",
            type:"get"
        });

        /*var longitude = position.coords.longitude;
        var latitude = position.coords.latitude;
        var latLong = new google.maps.LatLng(latitude, longitude);

        var mapOptions = {
            center: latLong,
            zoom: 16,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        var map =  new google.maps.Map(document.getElementById("geolocation"),mapOptions);
        alert(listaPedidosPendientes[0]);
        //Posicion del rescatista
        var marker = new google.maps.Marker({
        position: latLong,
        map: map,
        title:"Usted esta aqui"
        });
        
        for (i = 0; i < listaPedidosPendientes.length; i++) {  
            marker = new google.maps.Marker({
            position: new google.maps.LatLng(JSON.parse(listaPedidosPendientes[i]).coordPedidoAyudaX, JSON.parse(listaPedidosPendientes[i]).coordPedidoAyudaY),
            map: map
            });
        }*/
     /*
        google.maps.event.addListener(marker, 'click', function() {
        map.setZoom(8);
        map.setCenter(marker.getPosition());
  });*/

       
    },

    onError: function(error) {
        alert('code: ' + error.code + '\n' + 'message: ' + error.message + '\n');
    },

    cargarMarcadores: function(){
        alert("cargue marcadores");
        var soly = new google.maps.LatLng(-34.8275215,-55.9765871);
        //map.gmap('addMarker', { 'position': soly } );

        //map = document.getElementById("geolocation");

        var marker = new google.maps.Marker({
        position: soly,
        title: 'Solymar',
        draggable: true,
        map: map
        });
    }
    // Update DOM on a Received Event
    /*receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }*/
};

//app.initialize();
/*function onMapLoad() {
	
		// load the google api
		var fileref=document.createElement('script');
		fileref.setAttribute("type","text/javascript");
		fileref.setAttribute("src",
		"http://maps.googleapis.com/maps/api/js?sensor=true&callback=" +
		"getGeolocation");
		document.getElementsByTagName("head")[0].
		appendChild(fileref);
		console.log("llame al mapa");
		
}

function getGeolocation() {
		// get the user's gps coordinates and display map
		var options = {
			maximumAge: 3000,
			timeout: 5000,
			enableHighAccuracy: true
		};
		navigator.geolocation.getCurrentPosition(loadMap,
		geoError, options);
}

function loadMap(position) {
	var latlng = new google.maps.LatLng(
	position.coords.latitude, position.coords.longitude);
	var myOptions = {
		zoom: 8,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	var mapObj = document.getElementById("mapa");
	var map = new google.maps.Map(mapObj, myOptions);
	var marker = new google.maps.Marker({
		position: latlng,
		map: map,
		title:"You"
	});
}

function geoError(error) {
	alert('code: ' + error.code + '\n' +
	'message: ' + error.message + '\n');
}*/
