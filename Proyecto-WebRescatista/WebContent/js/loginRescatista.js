//Funciones:
var marcadores = [];
var map = "";
var Nick;
var server = "localhost";
var puerto = "8080";

function cargarMapa(position) {

	var listaPedidosPendientes = [];
	var nick = window.localStorage.getItem("usuarioNick");
	$
			.ajax({
				url : "http://"
						+ server
						+ ":"
						+ puerto
						+ "/ServicioRest/catastrofe/rescatista/verPendientes?nick="
						+ nick,
				success : function(response) {
					// alert("Success");
					// useReturnData(response);

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
					// alert("lo que hay adentro " +
					// listaPedidosPendientes[0].coordPedidoAyudaX);

					// Posicion del rescatista
					var marker = new google.maps.Marker(
							{
								position : latLong,
								map : map,
								title : "Usted esta aqui",
								icon : 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
							});

					if (response != null && !$.isEmptyObject(response)) {
						listaPedidosPendientes = response.planesPendientesRescatistaDTO;
						window.localStorage.setItem("listaPedidosStorage",
								listaPedidosPendientes);

						// cargar las ubicaciones de los pedidos de ayuda en el
						// mapa
						var marcador;
						if (Object.prototype.toString
								.call(listaPedidosPendientes) === '[object Array]') {
							for (var i = 0; i < listaPedidosPendientes.length; i++) {
								marcador = new google.maps.Marker(
										{
											position : new google.maps.LatLng(
													listaPedidosPendientes[i].coordenadaX,
													listaPedidosPendientes[i].coordenaday),
											map : map
										});

								// Para guardar datos en el marcador
								marcador
										.set(
												'IdPedidoAyudaActual',
												listaPedidosPendientes[i].idEstadoRescatista);
								marcador.set('descripcionPedidoAyudaActual',
										listaPedidosPendientes[i].descripcion);
								marcador.set('urlArchivo', listaPedidosPendientes[i].urlArchivo);

								// para agregar evento onclick a los markers
								google.maps.event
										.addListener(
												marcador,
												'click',
												function() {
													$("#errorPedidoNoSeleccionado").remove();
													var IdActual = this
															.get('IdPedidoAyudaActual');
													var descripcionActual = this
															.get('descripcionPedidoAyudaActual');
													var urlArchivo = this.get('urlArchivo');
													// alert(descripcionActual);
													window.localStorage
															.setItem(
																	"IdPedidoAyudaActual",
																	IdActual);
													window.localStorage
															.setItem(
																	"descripcionPedidoAyudaActual",
																	descripcionActual);
													// alert(window.localStorage.getItem("IdPedidoAyudaActual"));
													//alert(IdActual);
													//alert(descripcionActual);
													document
															.getElementById("descripcionPedidoAyuda").value = descripcionActual;
													document
															.getElementById("idEstadoRescatista").value = IdActual;
													document.getElementById("urlArchivo").value = urlArchivo;
													// = IdActual;
													// document.getElementById("listaPendientes").style.display
													// = 'none';
													// document.getElementById("pedidoAyudaDetalle").style.display
													// = 'initial';
												});
								marcadores.push(marcador);
							}
						} else {
							marcador = new google.maps.Marker(
									{
										position : new google.maps.LatLng(
												[ listaPedidosPendientes ][0].coordenadaX,
												[ listaPedidosPendientes ][0].coordenaday),
										map : map
									});

							// Para guardar datos en el marcador
							marcador.set('IdPedidoAyudaActual',
									[listaPedidosPendientes][0].idEstadoRescatista);
							marcador.set('descripcionPedidoAyudaActual',
									[listaPedidosPendientes][0].descripcion);
							marcador.set('urlArchivo', listaPedidosPendientes.urlArchivo);

							// para agregar evento onclick a los markers
							google.maps.event
									.addListener(
											marcador,
											'click',
											function() {
												$("#errorPedidoNoSeleccionado").remove();
												var IdActual = this
														.get('IdPedidoAyudaActual');
												var descripcionActual = this
														.get('descripcionPedidoAyudaActual');
												var urlArchivo = this.get('urlArchivo');
												window.localStorage.setItem(
														"IdPedidoAyudaActual",
														IdActual);
												window.localStorage
														.setItem(
																"descripcionPedidoAyudaActual",
																descripcionActual);
												//alert(IdActual);
												//alert(descripcionActual);

												document
														.getElementById("descripcionPedidoAyuda").value = descripcionActual;
												document
														.getElementById("idEstadoRescatista").value = IdActual;
												document.getElementById("urlArchivo").value = urlArchivo;
											});
							marcadores.push(marcador);
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

$(document)
		.ready(navigator.geolocation.getCurrentPosition(cargarMapa, onError));

function validarRescatista() {

	Nick = document.getElementById("nickText").value;
	var Pass = document.getElementById("passText").value;

	$.ajax({
		url : "http://" + server + ":" + puerto
				+ "/ServicioRest/catastrofe/rescatista/login?nick="
				+ Nick.toString() + "&pass=" + encodeURIComponent(Pass),
		

		success : function(response) {
			// console.log(response);
			if (response.booleanValue === 'true') {
				//alert('ok');
				window.localStorage.setItem("usuarioNick", Nick);
				window.location.replace("index.jsp");

			} else {
				alert("Error: los datos son incorrectos.");
			}

		},
		error : function(request, status, error) {
			alert("No hay conexion. Por favor, intente mas tarde."
					+ request.responseText);
		},
		dataType : "json",
		type : "get"
	});

}

function verPlan() {
	var urlArchivo = document.getElementById("urlArchivo").value;
	window.location.replace("http://" + server + ":" + puerto
			+ "/ServicioRest/catastrofe/rescatista/pdf?nombreArchivoPlan="+urlArchivo);
	alert("urlArchivo");
	// $("#panelPdf").empty();
	// $("#panelPdf").append('<iframe
	// src="http://docs.google.com/gview?url=http://192.168.0.105:8080/ServicioRest/catastrofe/rescatista/pdf.pdf&embedded=true"style="width:600px;
	// height:500px;" frameborder="0"></iframe>');
}

function finalizar() {
	
	
	var idEstadoRescatista = document.getElementById("idEstadoRescatista").value;
	//alert(idEstadoRescatista);

	if (idEstadoRescatista != null && !$.isEmptyObject(idEstadoRescatista)) {
	//Saca todos los marcadores del mapa
		setAllMap(null);
	// llama a funcion del rest que finaliza la ejecucion del plan
	$
			.ajax({
				url : "http://"
						+ server
						+ ":"
						+ puerto
						+ "/ServicioRest/catastrofe/rescatista/finalizarPlan?idEstadoRescatista="
						+ idEstadoRescatista,
				success : function(response) {
					alert("Plan finalizado");
					// cargar mapa de nuevo con los marcadores incluidos
					document.getElementById("descripcionPedidoAyuda").value = "";
					navigator.geolocation.getCurrentPosition(cargarMapa,
							onError);
				},
				error : function(request, status, error) {
					alert("Error al finalizar plan. Por favor, vuelva a intentar");
				},
				dataType : "json",
				type : "get"
			});
	}
	else{
		$("#menuFinalizarPlan").append('<span id="errorPedidoNoSeleccionado">No ha seleccionado un pedido.</span>').css('color','red');	
		//document.getElementById("menuFinalizarPlan").innerHTML = "<span style='color:red;'>No ha seleccionado un pedido.</span>";
	}
}

// Sets the map on all markers in the array.
function setAllMap(map) {
	for (var i = 0; i < marcadores.length; i++) {
		marcadores[i].setMap(map);
	}
}