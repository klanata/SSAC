//Funciones:



function cargar(position){

        var listaPedidosPendientes = new Array();
        $.ajax({url:"http://localhost:8080/ServicioRest/catastrofe/rescatista/verPendientes",
            success:function(response) {
                //alert("Success");
                //useReturnData(response);
                listaPedidosPendientes = response.planesPendientesRescatistaRest;
                window.localStorage.setItem("listaPedidosStorage", listaPedidosPendientes);

                var longitude = position.coords.longitude;
                var latitude = position.coords.latitude;
                var latLong = new google.maps.LatLng(latitude, longitude);

                var mapOptions = {
                    center: latLong,
                    zoom: 12,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                var map =  new google.maps.Map(document.getElementById('mapa'),mapOptions);
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
                        window.localStorage.setItem("IdPedidoAyudaActual", IdActual);
                        window.localStorage.setItem("descripcionPedidoAyudaActual", descripcionActual);
                        //alert(window.localStorage.getItem("IdPedidoAyudaActual"));
                        
                        document.getElementById("descripcionPedidoAyuda").value = descripcionActual;
                        //document.getElementById("idPedidoAyudaActual").value = IdActual;
                        //document.getElementById("listaPendientes").style.display = 'none';
                        //document.getElementById("pedidoAyudaDetalle").style.display = 'initial';
                    });
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

$(document).ready(navigator.geolocation.getCurrentPosition(cargar, onError));
     
function validarRescatista(){

	var Nick = document.getElementById("nickText").value;
	var Pass = document.getElementById("passText").value;

        $.ajax({url:"http://localhost:8080/ServicioRest/catastrofe/rescatista/login?nick="+Nick.toString()+"&pass="+Pass.toString(),//NO ANDA CON 127.0.0.1
        
            success:function(response) {
                //console.log(response);
                if(response.booleanValue === 'true')
                {
                    alert('ok');
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
	window.location.replace("http://localhost:8080/ServicioRest/catastrofe/rescatista/pdf");
	
}