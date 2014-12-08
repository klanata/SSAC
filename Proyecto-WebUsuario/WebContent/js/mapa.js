
function cargar(position){
	   //alert("Entre al cargar");
        var listaCatastrofes = new Array();
        $.ajax({url:"http://192.168.0.105:8080/ServicioRest/catastrofe/catastrofes",
        //$.ajax({url:"http://172.16.102.89:8080/ServicioRest/catastrofe/catastrofes",
            success:function(response) {
            	
                //alert(response);
                //useReturnData(response);
                listaCatastrofes = response.catastrofe;
                //alert("Esta es la lista cargada: "+listaCatastrofes);
                window.localStorage.setItem("listaCatastrofesStorage", listaCatastrofes);

                var longitude = position.coords.longitude;
                var latitude = position.coords.latitude;
                var latLong = new google.maps.LatLng(latitude, longitude);
                //alert("lo que hay adentro " + listaCatastrofes[0].coordenadasX);

                var mapOptions = {
                    center: latLong,
                    zoom: 12,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                var map =  new google.maps.Map(document.getElementById('mapa'),mapOptions);
                //alert("lo que hay adentro " + listaCatastrofes[0].coordenadasX);

                
                //cargar las ubicaciones de los pedidos de ayuda en el mapa
                var marcador;
                for (var i = 0; i < listaCatastrofes.length; i++) {  
                        marcador = new google.maps.Marker({
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

//$(document).ready(navigator.geolocation.getCurrentPosition(cargar, onError));
$(document).ready(navigator.geolocation.getCurrentPosition(cargar, onError));
     
