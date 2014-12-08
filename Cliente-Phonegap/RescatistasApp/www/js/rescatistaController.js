'use strict';

function rescatistaController($scope) {
    $scope.nick = "",
    $scope.email = "",
    $scope.password = "",
    $scope.nombreArchivoPlan = "",
    $scope.idPlanActual = "",
    $scope.rutaViewerJS = "http://10.0.2.2/RescatistasApp/www/ViewerJS/#../",
    $scope.descripcion = "",//window.localStorage.getItem("descripcionPedidoAyudaActual"),
    $scope.planes = new Array();
    
    //Funciones:
    $scope.validarRescatista = function(){
        $scope.verMapa();
        //Llamar a la funcion de login con POST
        /*
        $http({
            method : 'POST',
            //url : 'http://localhost:8080/ServicioRest/catastrofe/personas/login', //Web
            url : 'http://10.0.2.2:8080/ServicioRest/catastrofe/personas/login', //Android
            data : $.param({
                'id' : $scope.email,
                'password' : $scope.password
            }),
            crossDomain:true,
            //dataType: 'json',
            headers : {
                'Content-Type' : 'application/x-www-form-urlencoded'
            }
        }).success(function (data) {
            console.log(' data ');
            if(data)
            {
                alert('ok');
                $scope.listar();
            }
        }).
        error(function(){
             alert('Error al invocar al ServicioRest');
        });
        */
        
        //$.ajax({url:"http://10.0.2.2:8080/ServicioRest/catastrofe/rescatista/login?nick="+$scope.nick+"&pass="+$scope.password,//Android emulador
        //$.ajax({url:"http://192.168.0.105:8080/ServicioRest/catastrofe/rescatista/login?nick="+$scope.nick+"&pass="+$scope.password,//Android nativo - red local Victoria 
        //$.ajax({url:"http://192.168.43.183:8080/ServicioRest/catastrofe/rescatista/login?nick="+$scope.nick+"&pass="+$scope.password,   //Fing 
        //$.ajax({url:"http://172.16.102.91:8080/ServicioRest/catastrofe/rescatista/login?nick="+$scope.nick+"&pass="+$scope.password,   //Fing 
        $.ajax({url:"http://192.168.0.105:8080/ServicioRest/catastrofe/rescatista/login?nick="+$scope.nick+"&pass="+$scope.password,   //Utu 
        //$.ajax({url:"http://localhost:8080/ServicioRest/catastrofe/rescatista/login?nick="+$scope.nick+"&pass="+$scope.password,//Web - desde WAMP no se puede por CORS
            success:function(response) {
                //console.log(response);
                if(response.booleanValue === 'true')
                {
                    alert('ok');
                    window.localStorage.setItem("usuarioNick", $scope.nick);
                    $scope.listarPlanes.call($scope.verMapa());
                    $scope.nick = "";
                    $scope.password = "";
                }
                else{
                    alert("Error: los datos son incorrectos.");
                }
               
            },
            error:function (request, status, error) {
                alert("No hay conexión. Por favor, intente más tarde." + request.responseText);
            },
            dataType:"json",
            type:"get"
        });
       
      }
    

   $scope.listarPlanes = function() {
    
        //var map = new GoogleMap();
        //map.initialize();
        //console.log(map);
        //mostrarMapa();
        document.getElementById("login").style.display = 'none';
        document.getElementById("listaPendientes").style.display = 'initial';
        document.getElementById("pedidoAyudaDetalle").style.display = 'none';

       //$scope.planes = [{"nombrePlan":"Procedimiento Incendios", "planId":"1"}, {"nombrePlan":"Procedimiento Inundación", "planId":"3"}];--Datos para probar
        /*console.log($scope.planes);
        function useReturnData(response){
            
            $scope.planesAsignados = JSON.stringify(response.planesAsignados);
            console.log(response.planesAsignados);
        };*/

    
 }

 $scope.abrirPlan = function () {
    $scope.abrirPlan = function () {
        //$scope.nombreArchivoPlan = nombrePlan;
        $scope.nombreArchivoPlan = "PropuestaProyecto20140831v03.pdf";//Esto es de prueba. Borrar despues que este implementado el metodo.
        //$scope.idPlanActual = plan.idEstadoRescatista;
        //console.log($scope.rutaViewerJS + $scope.nombreArchivoPlan);
        //alert('ok');
        //alert(window.location.protocol + window.location.host + window.location.pathname);
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
        
        document.getElementById("pedidoAyudaDetalle").style.display = 'none';
        document.getElementById("plan").style.display = 'initial';
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
    mapa.initialize();
    //mapa.cargarMarcadores();
    }
  }

  $scope.finalizar = function () {
    $scope.finalizar = function () {
        //llama a funcion del rest que finaliza la ejecucion del plan y le pasa $scope.idPlanActual
        $scope.idPlanActual = document.getElementById("idPedidoAyudaActual").value;
        alert("Id plan actual: " + $scope.idPlanActual);
        $.ajax({url:"http://192.168.0.105:8080/ServicioRest/catastrofe/rescatista/finalizarPlan?idEstadoRescatista="+$scope.idPlanActual, //Android nativo - red local
        //$.ajax({url:"http://172.16.102.89:8080/ServicioRest/catastrofe/rescatista/finalizarPlan?"+$scope.idPlanActual, //Utu
        //$.ajax({url:"http://192.168.43.183:8080/ServicioRest/catastrofe/rescatista/finalizarPlan?"+$scope.idPlanActual, //Utu
        //$.ajax({url:"http://10.0.2.2:8080/ServicioRest/catastrofe/rescatista/finalizarPlan?"+$scope.idPlanActual, //Emulador Android - llamada al rest
        //$.ajax({url:"http://localhost:8080/ServicioRest/catastrofe/rescatista/finalizarPlan?"+$scope.idPlanActual, //Emulador Android - llamada al rest
        //$.ajax({url:"http://10.0.2.2/RescatistasApp/www/planesEmergenciaDB.js",//Emulador Android - llamada de prueba
        //$.ajax({url:"http://172.16.103.203/RescatistasApp/www/planesEmergenciaDB.js",//Android nativo, red local - llamada de prueba
        //$.ajax({url:"http://localhost/RescatistasApp/www/planesEmergenciaDB.js",//Web - llamada de prueba
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
        document.getElementById("listaPendientes").style.display = 'none';
        document.getElementById("pedidoAyudaDetalle").style.display = 'none';
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




  