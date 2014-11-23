window.onload = function(){
    var options = {
        zoom: 8
        , center: new google.maps.LatLng(-34.917440, -56.164244)
        , mapTypeId: google.maps.MapTypeId.SATELLITE
    };
    var map = new google.maps.Map(document.getElementById('map'), options);
};

