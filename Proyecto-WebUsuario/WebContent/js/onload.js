
  function processUser()
  {
    var parameters = location.search.substring(1).split('&');

    var temp = parameters[0].split("=");
    l = unescape(temp[1]);
    //temp = parameters[1].split("=");
    //p = unescape(temp[1]);
    //var id = window.localStorage.getItem("IdCatastrofe");
	  //alert(window.localStorage.getItem("urlCSS"));
	  document.getElementById("j_idt5:catastrofeId").value = l;
    alert(l);
  }

  processUser();