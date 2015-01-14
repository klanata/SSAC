//Marker sacado de http://jsfiddle.net/tcfwH/
var map;

function cargar(position) {
	// alert("Entre al cargar");
	var listaCatastrofes = new Array();
	$
			.ajax({
				url : "http://192.168.0.105:8080/ServicioRest/catastrofe/catastrofes",
				// $.ajax({url:"http://172.16.102.89:8080/ServicioRest/catastrofe/catastrofes",
				success : function(response) {

					var longitude = position.coords.longitude;
					var latitude = position.coords.latitude;
					var latLong = new google.maps.LatLng(latitude, longitude);

					var mapOptions = {
						center : latLong,
						zoom : 12,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					};

					map = new google.maps.Map(document.getElementById('mapa'),
							mapOptions);

					if (response != null || !$.isEmptyObject(response)) {
						listaCatastrofes = response.catastrofe;
						// alert("Esta es la lista cargada: "+listaCatastrofes);
						window.localStorage.setItem("listaCatastrofesStorage",
								listaCatastrofes);

						if (Object.prototype.toString.call(listaCatastrofes) === '[object Array]') {
							// cargar las ubicaciones de los pedidos de ayuda en
							// el mapa
							var marcador;
							for (var i = 0; i < listaCatastrofes.length; i++) {

								var str = listaCatastrofes[i].poligonoObjeto;
								// Transformacion del string de base a json.
								var json = str.slice(1, str.length - 1);
								var json2 = '{"vertices":[' + json + ']}';
								var json3 = JSON.parse(json2);

								var zonaCoords = [];
								for (var j = 0; j < json3.vertices.length; j++) {
									zonaCoords.push(new google.maps.LatLng(
											json3.vertices[j].k,
											json3.vertices[j].D));
								}
								makePolygon(zonaCoords, listaCatastrofes[i]);
							}

						} else {
							var str = [listaCatastrofes][0].poligonoObjeto;
							// Transformacion del string de base a json.
							var json = str.slice(1, str.length - 1);
							var json2 = '{"vertices":[' + json + ']}';
							var json3 = JSON.parse(json2);
							
							var zonaCoords = [];
							
							for (var j = 0; j < json3.vertices.length; j++) {
								zonaCoords.push(new google.maps.LatLng(
										json3.vertices[j].k,
										json3.vertices[j].D));
							}

							makePolygon(zonaCoords, [listaCatastrofes][0]);
						}
					}
				},
				error : function(request, status, error) {
					alert("Error" + request.responseText);
				},
				dataType : "json",
				type : "get"
			});
};

function onError() {
	alert("Error al cargar el mapa");
}

function makePolygon(polyCoords, catastrofe) {
	var marker = new MarkerWithLabel({
		position : new google.maps.LatLng(0, 0),
		draggable : false,
		raiseOnDrag : false,
		map : map,
		labelContent : 'Catastrofe: ' + catastrofe.nombreEvento,
		labelAnchor : new google.maps.Point(30, 20),
		labelClass : "labels",
		labelStyle : {
			opacity : 1.0
		},
		icon : "http://placehold.it/1x1",
		visible : false
	});

	var poly = new google.maps.Polygon({
		paths : polyCoords,
		strokeColor : "#FF0000",
		strokeOpacity : 0.8,
		strokeWeight : 3,
		fillColor : "#FF0000",
		fillOpacity : 0.35,
		map : map
	});

	marker.set('IdCatastrofe', catastrofe.id);
	marker.set('urlCSS', catastrofe.urlCSS);
	marker.set('nombreEvento', catastrofe.nombreEvento);
	
	google.maps.event.addListener(marker, 'click', irASitioCatastrofe);

	function irASitioCatastrofe(event) {
		var IdActual = this.get('IdCatastrofe');
		var pathCSS = this.get('urlCSS');
		var nombreEvento = this.get('nombreEvento');
		document.getElementById("idCatastrofe").value = IdActual;
		window.location.replace(nombreEvento + ".xhtml");

	}

	google.maps.event.addListener(poly, "mousemove", function(event) {
		marker.setPosition(event.latLng);
		marker.setVisible(true);
	});
	google.maps.event.addListener(poly, "mouseout", function(event) {
		marker.setVisible(false);
	});
}


$(document).ready(navigator.geolocation.getCurrentPosition(cargar, onError));
// google.maps.event.addDomListener(window, 'load', initialize);

