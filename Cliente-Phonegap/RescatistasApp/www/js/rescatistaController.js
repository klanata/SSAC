'use strict';

function rescatistaController($scope) {
    $scope.nick = "Vic",
    $scope.email = "a@rescatista.com",
    $scope.password = ""
    $scope.planes = new Array();
    $scope.fullName = function() {
        return $scope.nombre + " " + $scope.apellido}
    $scope.validarRescatista = function(){
        //hacer una llamada http
        /*$http.get('http://127.0.0.1:8080/ServicioRest/catastrofe/personas')
        .success(function(response) {$scope.listaPlanes = response;
        console.log("OnSuccess" + response);
         }).error(function(response) {
          console.log("OnError" + response);
        });*/

        //llamar a la funcion de login
        if($scope.email == 'a@rescatista.com' && $scope.password == 'pass')
        {
            	alert('ok');
            //document.getElementById("body").innerHTML='<object type="text/html" data="listaPendientes.html" ></object>';
            $scope.listar();
            //document.getElementById("login").style.display = 'none';
            //document.getElementById("listaPendientes").style.display = 'initial';


        }
      }
    $scope.listarPlanes = function($scope,$http) {
        //$http.get("http://localhost:8080/ServicioRest/catastrofe/personas").success(function(response) {$scope.listaPlanes = response;});
    }

   $scope.listar = function() {
    
    /*$http.get("http://localhost:8080/ServicioRest/catastrofe/catastrofes")
    .success(function(response) {$scope.tareas = response;}).
    error(function(response) {
          console.log("OnError" + response);
        });*/
          
        $.ajax({url:"http://10.0.2.2/RescatistasApp/www/planesEmergenciaDB.js",
            success:function(response) {
                //alert("Success");
                //useReturnData(response);
                console.log(response.planesAsignados);
                $scope.planes = response.planesAsignados;
                //document.getElementById("login").style.display = 'none';
               // document.getElementById("listaPendientes").style.display = 'initial';
                //scope.$apply();
               
            },
            error:function (request, status, error) {
                alert("Error" + request.responseText);
            },
            /*error:function() {
                alert("Error");
            },*/
            dataType:"json",
            type:"get"
        });

        document.getElementById("login").style.display = 'none';
        document.getElementById("listaPendientes").style.display = 'initial';

       //$scope.planes = [{"nombrePlan":"Procedimiento Incendios", "planId":"1"}, {"nombrePlan":"Procedimiento Inundación", "planId":"3"}];--Datos para probar
        /*console.log($scope.planes);
        function useReturnData(response){
            
            $scope.planesAsignados = JSON.stringify(response.planesAsignados);
            console.log(response.planesAsignados);
        };*/

    
 }

 $scope.go = function () {
    $scope.go = function () {
        alert('ok');
        document.getElementById("listaPendientes").style.display = 'none';
        document.getElementById("plan").style.display = 'initial';
    }
  }

  $scope.cancelar = function () {
    $scope.cancelar = function () {
        document.getElementById("plan").style.display = 'none';
        document.getElementById("listaPendientes").style.display = 'initial';
    }
  }

  $scope.finalizar = function () {
    $scope.finalizar = function () {
        //llama a funcion del rest que finaliza la ejecucion del plan
        document.getElementById("plan").style.display = 'none';
        document.getElementById("listaPendientes").style.display = 'initial';
    }
  }
}



  