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
	<f:view>
	<h:form>				
			<p><label for="nick">Nick:</label>				
			<h:inputText id="nick" value="#{registroUsuario.nick}" required="true" ></h:inputText>
			<h:message errorStyle="color:red;" for="nick"/> 
			</p>
			<p><label for="nombre">Nombre:</label>
			<h:inputText id="nombre" value="#{registroUsuario.nombre}"></h:inputText> 			
			</p>
			<p><label for="apellido">Apellido:</label>	
			<h:inputText id="apellido" value="#{registroUsuario.apellido}"></h:inputText>		
			</p>
			<p><label for="password">Password:</label>
			<h:inputSecret id="password" value="#{registroUsuario.password}" required="true" ></h:inputSecret>
			</p>
			<p><label for="mail">Mail:</label>	
			<h:inputText id="mail" value="#{registroUsuario.mail}"></h:inputText>
   			</p>
		<p>		
		<h:commandButton id="comandoRegistrar" type="submit" style="margin-left: 150px;" styleClass="formbutton" value="Registrar" action="#{registroUsuario.registrar()}">
		</h:commandButton>
		</p>
	</h:form>
	</f:view>
</body>
</html>