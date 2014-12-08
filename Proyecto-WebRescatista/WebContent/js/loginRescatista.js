//Funciones:
var marcadores= [];
var map="";
var Nick;

function cargarMapa(position){

        var listaPedidosPendientes = [];
        var nick = window.localStorage.getItem("usuarioNick");
        $.ajax({url:"http://localhost:8080/ServicioRest/catastrofe/rescatista/verPendientes?nick=" + nick,
            success:function(response) {
                // alert("Success");
                // useReturnData(response);
                listaPedidosPendientes = response.planesPendientesRescatistaDTO;
                window.localStorage.setItem("listaPedidosStorage", listaPedidosPendientes);

                var longitude = position.coords.longitude;
                var latitude = position.coords.latitude;
                var latLong = new google.maps.LatLng(latitude, longitude);

                var mapOptions = {
                    center: latLong,
                    zoom: 12,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                map =  new google.maps.Map(document.getElementById('mapa'),mapOptions);
                // alert("lo que hay adentro " +
				// listaPedidosPendientes[0].coordPedidoAyudaX);

                // Posicion del rescatista
                var marker = new google.maps.Marker({
                position: latLong,
                map: map,
                title:"Usted esta aqui",
                icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
                });
                
                // cargar las ubicaciones de los pedidos de ayuda en el mapa
                var marcador;
                if(Object.prototype.toString.call(listaPedidosPendientes) === '[object Array]'){
                for (var i = 0; i < listaPedidosPendientes.length; i++) {  
                    marcador = new google.maps.Marker({
                        position: new google.maps.LatLng(listaPedidosPendientes[i].coordenadaX, listaPedidosPendientes[i].coordenaday),
                        map: map
                    });
                    
                    // Para guardar datos en el marcador
                    marcador.set('IdPedidoAyudaActual', listaPedidosPendientes[i].idEstadoRescatista);
                    marcador.set('descripcionPedidoAyudaActual', listaPedidosPendientes[i].descripcion);
                    
                    
                    
                    // para agregar evento onclick a los markers
                    google.maps.event.addListener(marcador, 'click', function() {
                        var IdActual = this.get('IdPedidoAyudaActual');
                        var descripcionActual = this.get('descripcionPedidoAyudaActual');
                        // alert(descripcionActual);
                        window.localStorage.setItem("IdPedidoAyudaActual", IdActual);
                        window.localStorage.setItem("descripcionPedidoAyudaActual", descripcionActual);
                        // alert(window.localStorage.getItem("IdPedidoAyudaActual"));
                        
                        document.getElementById("descripcionPedidoAyuda").value = descripcionActual;
                        document.getElementById("idEstadoRescatista").value = IdActual;
						// = IdActual;
                        // document.getElementById("listaPendientes").style.display
						// = 'none';
                        // document.getElementById("pedidoAyudaDetalle").style.display
						// = 'initial';
                    });
                    marcadores.push(marcador);
                 }
                }
                else{
                	 marcador = new google.maps.Marker({
                         position: new google.maps.LatLng([listaPedidosPendientes][0].coordenadaX, [listaPedidosPendientes][0].coordenaday),
                         map: map
                         });

                          // Para guardar datos en el marcador
                         marcador.set('IdPedidoAyudaActual', listaPedidosPendientes.idEstadoRescatista);
                         marcador.set('descripcionPedidoAyudaActual', listaPedidosPendientes.descripcion);

                         // para agregar evento onclick a los markers
                         google.maps.event.addListener(marcador, 'click', function() {
                         var IdActual = this.get('IdPedidoAyudaActual');
                         var descripcionActual = this.get('descripcionPedidoAyudaActual');
                         window.localStorage.setItem("IdPedidoAyudaActual", IdActual);
                         window.localStorage.setItem("descripcionPedidoAyudaActual", descripcionActual);
                         // alert(window.localStorage.getItem("IdPedidoAyudaActual"));
                         
                         document.getElementById("descripcionPedidoAyuda").value = descripcionActual;
                         document.getElementById("idEstadoRescatista").value = IdActual;
                         });
                         marcadores.push(marcador);
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

$(document).ready(navigator.geolocation.getCurrentPosition(cargarMapa, onError));
     
function validarRescatista(){

	Nick = document.getElementById("nickText").value;
	var Pass = document.getElementById("passText").value;

        $.ajax({url:"http://localhost:8080/ServicioRest/catastrofe/rescatista/login?nick="+Nick.toString()+"&pass="+Pass.toString(),// NO
																																	// ANDA
																																	// CON
																																	// 127.0.0.1
        
            success:function(response) {
                // console.log(response);
                if(response.booleanValue === 'true')
                {
                    alert('ok');
                    window.localStorage.setItem("usuarioNick", Nick);
                    window.location.replace("index.jsp");
                    
                }
                else{
                    alert("Error: los datos son incorrectos.");
                }
               
            },
            error:function (request, status, error) {
                alert("No hay conexion. Por favor, intente mas tarde." + request.responseText);
            },
            dataType:"json",
            type:"get"
        });
       
      }

function verPlan(){
	//window.location.replace("http://localhost:8080/ServicioRest/catastrofe/rescatista/pdf");
	$("#panelPdf").empty();
	$("#panelPdf").append('<iframe src="http://docs.google.com/gview?url=http://localhost:8080/ServicioRest/catastrofe/rescatista/pdf.pdf&embedded=true"style="width:600px; height:500px;" frameborder="0"></iframe>');
}

function finalizar(){
	//agarar el id hidenn y se lo pasa a la llamada ajax
	
	//llamara al servicio para traer marcadores nuevos.
	//vas a volcar los marcadores al mapa de nuevo
	//limpiar div que muetren datos del marcador borrado.
	
	//sacar todos los marcadores del mapa
	setAllMap(null);
	var idEstadoRescatista = document.getElementById("idEstadoRescatista").value;
	alert(idEstadoRescatista);
	
	//llama a funcion del rest que finaliza la ejecucion del plan
    $.ajax({url:"http://localhost:8080/ServicioRest/catastrofe/rescatista/finalizarPlan?idEstadoRescatista=" + idEstadoRescatista,
        success:function(response) {
            alert("Plan finalizado");
            //cargar mapa de nuevo con los marcadores incluidos
            document.getElementById("descripcionPedidoAyuda").value = "";
            navigator.geolocation.getCurrentPosition(cargarMapa, onError);
        },
        error:function (request, status, error) {
            alert("Error al finalizar plan. Por favor, vuelva a intentar");
        },
        dataType:"json",
        type:"get"
    });
}

//Sets the map on all markers in the array.
function setAllMap(map) {
	  for (var i = 0; i < marcadores.length; i++) {
	    marcadores[i].setMap(map);
	  }
	}