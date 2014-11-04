package clienteutility;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClienteUtility {

	private static Context initialContext;
	 
    private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
 
    public static Context getInitialContext() throws NamingException {
        if (initialContext == null) {
            Properties properties = new Properties();
            
            //properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
            //properties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
            //properties.put(Context.PROVIDER_URL,"jnp://localhost:4447" );
        	
            
            properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
 
            initialContext = new InitialContext(properties);
        }
        return initialContext;
    }
}
