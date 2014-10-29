<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cecilia2</title>
</head>
<body>
	<h3>Registro de Usuario</h3>
	
	<form>				
			<p><label for="nick">Nick:</label>				
			<h:inputText id="nick" value="#{userBean.txtNick}" required="true" ></h:inputText>
			<h:message errorStyle="color:red;" for="nick"/> 
			</p>
			<p><label for="nombre">Nombre:</label>
			<h:inputText id="nombre" value="#{userBean.txtNombre}"></h:inputText> 			
			
			<p><label for="password">Password:</label>
			<h:inputSecret id="password" value="#{userBean.txtPassword}" required="true" ></h:inputSecret>
			</p>
			<p><label for="mail">Mail:</label>	
			<h:inputText id="mail" value="#{userBean.txtEmail}"></h:inputText>
   			</p>
		<p>		
		<h:commandButton id="comandoRegistrar" type="submit" style="margin-left: 150px;" styleClass="formbutton" value="Registrar" action="#{userBean.ingresarUsu_ON_CLICK()}">
		</h:commandButton>
		</p>
	</form>
	
</body>
</html>