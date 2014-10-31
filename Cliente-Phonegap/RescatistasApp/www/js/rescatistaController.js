function rescatistaController($scope) {
    $scope.nombre = "Vic",
    $scope.apellido = "Doe",
    $scope.email = "a@rescatista.com",
    $scope.password = "",
    $scope.listaPlanes = "",
    $scope.fullName = function() {
        return $scope.nombre + " " + $scope.apellido}
    $scope.validarRescatista = function(){
        //hacer una llamada http
        if($scope.email == 'a@rescatista.com' && $scope.password == 'pass')
        	alert('ok')
     }
    $scope.listarPlanes = function($scope,$http) {
        $http.get("http://localhost:8080/ServicioRest/catastrofe/personas").success(function(response) {$scope.listaPlanes = response;});
    }
}