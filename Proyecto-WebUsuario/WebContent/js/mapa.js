//Marker sacado de http://jsfiddle.net/tcfwH/
var map;

function cargar(position){
	   //alert("Entre al cargar");
        var listaCatastrofes = new Array();
        $.ajax({url:"http://172.16.102.150:8080/ServicioRest/catastrofe/catastrofes",
        //$.ajax({url:"http://172.16.102.89:8080/ServicioRest/catastrofe/catastrofes",
            success:function(response) {
            	

                var longitude = position.coords.longitude;
                var latitude = position.coords.latitude;
                var latLong = new google.maps.LatLng(latitude, longitude);

                var mapOptions = {
                    center: latLong,
                    zoom: 12,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                map =  new google.maps.Map(document.getElementById('mapa'),mapOptions);

                if (response != null || !$.isEmptyObject(response)) {
                listaCatastrofes = response.catastrofe;
                //alert("Esta es la lista cargada: "+listaCatastrofes);
                window.localStorage.setItem("listaCatastrofesStorage", listaCatastrofes);
                

                if(Object.prototype.toString.call(listaCatastrofes) === '[object Array]'){
                	console.log("pase por true");
                //cargar las ubicaciones de los pedidos de ayuda en el mapa
                var marcador;
                for (var i = 0; i < listaCatastrofes.length; i++) {  
                       
                	/*/*marcador = new google.maps.Marker({
                        position: new google.maps.LatLng(listaCatastrofes[i].coordenadasX, listaCatastrofes[i].coordenadasY),
                        map: map
                    });
                        
                    //Para guardar datos en el marcador
                    marcador.set('IdCatastrofe', listaCatastrofes[i].id);
                    marcador.set('urlCSS', listaCatastrofes[i].urlCSS);

                    //para agregar evento onclick a los markers
                    google.maps.event.addListener(marcador, 'click', function() {
                        var IdActual = this.get('IdCatastrofe');
                        var pathCSS = this.get('urlCSS');
                        window.localStorage.setItem("IdCatastrofe", IdActual);
                        window.localStorage.setItem("urlCSS", pathCSS);
                        document.getElementById("idCatastrofe").value = IdActual;
                        window.location.replace("Index.xhtml?id="+ IdActual+"&c="+ pathCSS);
                        
                    });*/
                    
                    var str = listaCatastrofes[i].poligono; 
                    //Transformacion del string de base a json.
                    var json = str.slice(1,str.length-1);
                    var json2 = '{"vertices":['+ json + ']}';
                    var json3 = JSON.parse(json2);
                    
                    var zonaCoords = [];
                    for(var j = 0;j < json3.vertices.length;j++)
                    {
                    	zonaCoords.push(new google.maps.LatLng(json3.vertices[j].k, json3.vertices[j].D));
                    }
                    
                    makePolygon(zonaCoords,listaCatastrofes[i]);
                    
                    //Poligono
                    /*zona = new google.maps.Polygon({
                        paths: zonaCoords,
                        strokeColor: '#FF0000',
                        strokeOpacity: 0.8,
                        strokeWeight: 2,
                        fillColor: '#FF0000',
                        fillOpacity: 0.35,
                    	  draggable: true,
                    	  geodesic: true,
                        editable:false
                      });
                    
                    zona.set('IdCatastrofe', listaCatastrofes[i].id);
                    zona.set('urlCSS', listaCatastrofes[i].urlCSS);
                    zona.setMap(map);
                    
                    
                    infoWindow = new google.maps.InfoWindow();
                    
                    
                    google.maps.event.addListener(zona, 'click', irASitioCatastrofe);
                    //google.maps.event.addListener(zona, 'mouseover', mostrarNombre(listaCatastrofes[i],map,infoWindow));
                    /*google.maps.event.addListener(zona, 'mouseover', function(){
                    	var contentString = 'Catastrofe: ' + listaCatastrofes[i].nombreEvento;
                    	infoWindow.setContent(contentString);
                        infoWindow.setPosition(new google.maps.LatLng(listaCatastrofes[i].coordenadasX, listaCatastrofes[i].coordenadasY));
                    	infoWindow.open(map,zona);
                    });*/
                    /*google.maps.event.addListener(zona,'mouseout',function(infoWindow){
                    	infoWindow.close();
                    });
                    
                    function mostrarNombre(listaCatastrofes,map,infoWin){
                        
                        var contentString = 'Catastrofe: ' + listaCatastrofes.nombreEvento;
                        //console.log(contentString);
                    	  // Replace the info window's content and position.
                        infoWin.setContent(contentString);
                        infoWin.setPosition(new google.maps.LatLng(listaCatastrofes.coordenadasX, listaCatastrofes.coordenadasY));

                        infoWin.open(map);
                    	console.log("Por aca no pasa");
                      }
                    
                    function irASitioCatastrofe(event) {
                  	  var IdActual = this.get('IdCatastrofe');
                  	  var pathCSS = this.get('urlCSS');
                  	  document.getElementById("idCatastrofe").value = IdActual;
                      window.location.replace("Index.xhtml?id="+ IdActual+"&c="+ pathCSS);
                  	  
                    }*/
                    
                 
                }

              }
              /*else{
            	  console.log("pase por el else");
            	  /*Definicion del marcador cuando la catastrofe tenia solo una coordenada para la ubicacion
                   var marcador;
                
                        marcador = new google.maps.Marker({
                        position: new google.maps.LatLng([listaCatastrofes][0].coordenadasX, [listaCatastrofes][0].coordenadasY),
                        map: map
                    });*/
                    /*Fin de la definicion del marcador.
                        
                    var str = [listaCatastrofes][0].poligono; 
                    //Transformacion del string de base a json.
                    var json = str.slice(1,str.length-1);
                    var json2 = '{"vertices":['+ json + ']}';
                    var json3 = JSON.parse(json2);
                    
                    //Para guardar datos en el marcador (ahora es en el poligono)
                    //marcador.set('IdCatastrofe', [listaCatastrofes][0].id);
                    //marcador.set('urlCSS', [listaCatastrofes][0].urlCSS);
                    
                    // Define las coordenadas LatLng para el path del poligono.
                    var zonaCoords = [];
                    //alert("Vertices lenght = " + json3.vertices.length);
                    for(var i = 0;i < json3.vertices.length;i++)
                    {
                    	zonaCoords.push(new google.maps.LatLng(json3.vertices[i].k, json3.vertices[i].D));
                    }
                    
                    //alert("ZonaCoords = " + zonaCoords);
                    // Construct the polygon.
                    zona = new google.maps.Polygon({
                      paths: zonaCoords,
                      strokeColor: '#FF0000',
                      strokeOpacity: 0.8,
                      strokeWeight: 2,
                      fillColor: '#FF0000',
                      fillOpacity: 0.35,
                  	  draggable: true,
                  	  geodesic: true,
                      editable:false
                    });
                    
                    zona.set('IdCatastrofe', [listaCatastrofes][0].id);
                    zona.set('urlCSS', [listaCatastrofes][0].urlCSS);
                    zona.setMap(map);

                    google.maps.event.addListener(zona, 'click', irASitioCatastrofe);
                    google.maps.event.addListener(zona, 'mouseover', mostrarNombre(listaCatastrofes));
                    google.maps.event.addListener(zona,'mouseout',function(){
                    	infoWindow.close();
                    });
                    infoWindow = new google.maps.InfoWindow();
                    
                    function irASitioCatastrofe(event) {
                    	  var IdActual = this.get('IdCatastrofe');
                    	  var pathCSS = this.get('urlCSS');
                    	  //var contentString = '<a href="Index.xhtml?id='+ IdActual+'&c='+ pathCSS + '">Catastrofe: ' + [listaCatastrofes][0].nombreEvento + '</a>';
                    	  //var contentString = '<a href="#" onclick="redirect()";>Catastrofe: ' + [listaCatastrofes][0].nombreEvento + '</a>';
                    	  // Replace the info window's content and position.
                    	  //infoWindow.setContent(contentString);
                    	  //infoWindow.setPosition(event.latLng);

                    	  //infoWindow.open(map);
                    	  document.getElementById("idCatastrofe").value = IdActual;
                          window.location.replace("Index.xhtml?id="+ IdActual+"&c="+ pathCSS);
                    	  
                    }
                    
                    function mostrarNombre(listaCatastrofes){
                      infoWindow = new google.maps.InfoWindow();
                      var contentString = 'Catastrofe: ' + [listaCatastrofes][0].nombreEvento;
                      console.log(contentString);
                  	  // Replace the info window's content and position.
                  	  infoWindow.setContent(contentString);
                  	  infoWindow.setPosition(new google.maps.LatLng([listaCatastrofes][0].coordenadasX, [listaCatastrofes][0].coordenadasY));

                  	  infoWindow.open(map);
                  	console.log("Por aca no pasa");
                    }
                    //para agregar evento onclick a los markers
                    /*google.maps.event.addListener(marcador, 'click', function() {
                        var IdActual = this.get('IdCatastrofe');
                        var pathCSS = this.get('urlCSS');
                        //alert(descripcionActual);
                        window.localStorage.setItem("IdCatastrofe", IdActual);
                        window.localStorage.setItem("urlCSS", pathCSS);
                        //window.localStorage.setItem("descripcionPedidoAyudaActual", descripcionActual);
                        //alert(window.localStorage.getItem("IdPedidoAyudaActual"));
                        //alert("Me hiciste click!");
                        document.getElementById("idCatastrofe").value = IdActual;
                        window.location.replace("Index.xhtml?id="+ IdActual+"&c="+ pathCSS);
                        
                        //document.getElementById("idPedidoAyudaActual").value = IdActual;
                        //document.getElementById("listaPendientes").style.display = 'none';
                        //document.getElementById("pedidoAyudaDetalle").style.display = 'initial';
                    })
                

              }*/
            }
               
            },
            error:function (request, status, error) {
                alert("Error" + request.responseText);
            },
            dataType:"json",
            type:"get"
        });
};

function onError(){
	alert("Error al cargar el mapa");
}

function makePolygon(polyCoords, catastrofe) {
    var marker = new MarkerWithLabel({
     position: new google.maps.LatLng(0,0),
     draggable: false,
     raiseOnDrag: false,
     map: map,
     labelContent: 'Catastrofe: ' + catastrofe.nombreEvento,
     labelAnchor: new google.maps.Point(30, 20),
     labelClass: "labels",
     labelStyle: {opacity: 1.0},
     icon: "http://placehold.it/1x1",
     visible: false
    });

   var poly = new google.maps.Polygon({
       paths: polyCoords,
       strokeColor: "#FF0000",
       strokeOpacity: 0.8,
       strokeWeight: 3,
       fillColor: "#FF0000",
       fillOpacity: 0.35,
       map: map
   });
   
   marker.set('IdCatastrofe', catastrofe.id);
   marker.set('urlCSS', catastrofe.urlCSS);
   
   
   google.maps.event.addListener(marker, 'click', irASitioCatastrofe);

   function irASitioCatastrofe(event) {
   	  var IdActual = this.get('IdCatastrofe');
   	  var pathCSS = this.get('urlCSS');
   	  document.getElementById("idCatastrofe").value = IdActual;
       window.location.replace("Index.xhtml?id="+ IdActual+"&c="+ pathCSS);
   	  
     }

     google.maps.event.addListener(poly, "mousemove", function(event) {
       marker.setPosition(event.latLng);
       marker.setVisible(true);
     });
     google.maps.event.addListener(poly, "mouseout", function(event) {
       marker.setVisible(false);
     });
   }

/*function mostrarNombre(catastrofe){
	/*infoWindow = new google.maps.InfoWindow();
	  console.log(catastrofe);
    var contentString = 'Catastrofe: ' + catastrofe.nombreEvento;
    
	  // Replace the info window's content and position.
    infoWindow.setContent(contentString);
    infoWindow.setPosition(new google.maps.LatLng(catastrofe.coordenadasX, catastrofe.coordenadasY));

    infoWindow.open(map);
	console.log("La mierda no anda");
  }*/



//$(document).ready(navigator.geolocation.getCurrentPosition(cargar, onError));
$(document).ready(navigator.geolocation.getCurrentPosition(cargar, onError));
//google.maps.event.addDomListener(window, 'load', initialize);
     
