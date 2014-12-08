//Función para obtener los datos pasados a través de la url (id y css)
  function processUser()
  {
    var parameters = location.search.substring(1).split('&');

    var temp = parameters[0].split("=");
    l = unescape(temp[1]);
    temp = parameters[1].split("=");
    p = unescape(temp[1]);
	document.getElementById("pedidoAyudaBean:catastrofeId").value = l;
	var cssFinal = "css/"+ p; 
	$("#cssPrincipal").attr("href",cssFinal);
	return false;
    alert("Id: " + l + "css: " + p);
  }

  processUser();