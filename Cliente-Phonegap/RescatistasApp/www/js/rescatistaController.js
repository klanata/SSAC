'use strict';



function rescatistaController($scope) {
    $scope.nick = "",
    $scope.email = "",
    $scope.password = "",
    $scope.nombreArchivoPlan = "",
    $scope.idPlanActual = "",
    $scope.server = "192.168.0.104",
    $scope.puerto = "8080",
    $scope.rutaViewerJS = "http://10.0.2.2/RescatistasApp/www/ViewerJS/#../",
    $scope.descripcion = "",//window.localStorage.getItem("descripcionPedidoAyudaActual"),
    $scope.planes = new Array();
    
    //Funciones:
    $scope.validarRescatista = function(){
        $scope.verMapa();
        if(window.localStorage.usuarioNick){
			$scope.listarPlanes.call($scope.verMapa());
		}
		else{
		console.log("Nick: " + $scope.nick);
        $.ajax({url:"http://" + $scope.server + ":" + $scope.puerto + "/ServicioRest/catastrofe/rescatista/login?nick=" + $scope.nick + "&pass=" + encodeURIComponent($scope.password),
            success:function(response) {
                //console.log("Entro al response: " + response);
                if(response.booleanValue === 'true')
                {
                    //alert('ok');
                    window.localStorage.setItem("usuarioNick", $scope.nick);
                    $scope.listarPlanes.call($scope.verMapa());
					document.getElementById("nick").value="";
					document.getElementById("password").value="";
                    $scope.nick = "";
                    $scope.password = "";
                }
                else{
                    alert("Error: los datos son incorrectos.");
                }
               
            },
            error:function (request, status, error) {
			//console.log("Nick: " + $scope.nick);
			//console.log("pass: " + $scope.password);
			    console.log(error.message);
				console.log(request.responseText);
                alert("No hay conexión. Sus datos no pueden ser verificados. Por favor, intente más tarde.");
            },
            dataType:"json",
            type:"get"
        });
		}
       
      }
    

   $scope.listarPlanes = function() {
    
        document.getElementById("login").style.display = 'none';
        document.getElementById("listaPendientes").style.display = 'initial';//Div que contiene al mapa.
        document.getElementById("pedidoAyudaDetalle").style.display = 'none';

 }

 $scope.abrirPlan = function () {
    $scope.abrirPlan = function () {
        //$scope.nombreArchivoPlan = nombrePlan;
        //$scope.idPlanActual = plan.idEstadoRescatista;

        //window.open('http://10.0.2.2/RescatistasApp/www/ViewerJS/#../PropuestaProyecto20140831v03.pdf', '_system', 'location=yes');
        //window.open('http://10.0.2.2:8080/ServicioRest/WEB-INF/lib/ViewerJS/#../PropuestaProyecto20140831v03.pdf', '_system', 'location=yes');
        //window.open('http://192.168.0.104:8080/ServicioRest/catastrofe/rescatista/pdf', '_system', 'location=yes');
       /*  $.ajax({url:"http://10.0.2.2:8080/ServicioRest/catastrofe/rescatista/pdfPrueba",
            success:function(response) {
                console.log(response);
                window.open(response);
               
            },
            error:function (request, status, error) {
                alert("Error. No se pudo leer el archivo");
            },
            type:"get"
        });*/
        $scope.nombreArchivoPlan = document.getElementById("urlArchivo").value;
        var ref = window.open('http://'+$scope.server+':8080/ServicioRest/catastrofe/rescatista/pdf?nombreArchivoPlan='+$scope.nombreArchivoPlan, '_system', 'location=yes');
        //var ref = window.open('http://www.google.com', '_system', 'location=yes');
        //document.getElementById("pedidoAyudaDetalle").style.display = 'none';
        //document.getElementById("plan").style.display = 'initial';
    }
  }

  $scope.cancelar = function () {
    $scope.cancelar = function () {
        document.getElementById("plan").style.display = 'none';
        document.getElementById("listaPendientes").style.display = 'initial';
    }
  }

  $scope.verMapa = function () {
  $scope.verMapa = function () {
    //alert("verMapa");
    mapa.initialize();
    //mapa.cargarMarcadores();
    }
  }

  $scope.finalizar = function () {
    $scope.finalizar = function () {
        //llama a funcion del rest que finaliza la ejecucion del plan y le pasa $scope.idPlanActual
        $scope.idPlanActual = document.getElementById("idPedidoAyudaActual").value;
        //alert("Id plan actual: " + $scope.idPlanActual);
        $.ajax({url:"http://" + $scope.server + ":" + $scope.puerto + "/ServicioRest/catastrofe/rescatista/finalizarPlan?idEstadoRescatista=" + $scope.idPlanActual, //Android nativo - red local

            success:function(response) {
                alert("Plan finalizado");
                $scope.listarPlanes.call($scope.verMapa());
            },
            error:function (request, status, error) {
                alert("Error al finalizar plan. Por favor, vuelva a intentar");
            },
            dataType:"json",
            type:"get"
        });
        
       
        //document.getElementById("pedidoAyudaDetalle").style.display = 'none';
        //document.getElementById("listaPendientes").style.display = 'initial';
    }
  }

   $scope.volver = function () {
    $scope.volver = function () {
        document.getElementById("pedidoAyudaDetalle").style.display = 'initial';
        document.getElementById("plan").style.display = 'none';
    }
  }

  $scope.logout = function () {
    $scope.logout = function () {
	    window.localStorage.removeItem("usuarioNick");
        document.getElementById("listaPendientes").style.display = 'none';
        document.getElementById("pedidoAyudaDetalle").style.display = 'none';
		document.getElementById("nick").value="";
		document.getElementById("password").value="";
        document.getElementById("login").style.display = 'initial';
    }
  }

 

 /*var onSuccess = function(position){
    var longitud = position.coords.longitude;
    var latitude = position.coords.latitude;
    var latLong = new google.maps.LatLng(latitude, longitude);

    var mapOptions = {
        center: latLong,
        zoom: 16,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    var map = new google.maps.Map(document.getElementById("mapa"), mapOptions);
  }

  var onError = function(error){
    alert("code" + error.code + "\n" + "message:" + error.message + "\n");
  }*/

  // function mostrarMapa(){
  //  navigator.geolocation.getCurrentPosition(onSuccess, onError);
  //}

 /*function GoogleMap(){
 
    this.initialize = function(){
    var map = showMap();
    }
 
    var showMap = function(){
    var mapOptions = {
        zoom: 4,
        center: new google.maps.LatLng(-33, 151),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
     
     var div = document.getElementById("mapa");
     var map = plugin.google.maps.Map.getMap(div)
    //var map = new google.maps.Map(document.getElementById("mapa"), mapOptions);
     
    return map;
 }
}*/

 }




  