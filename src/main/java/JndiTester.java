import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class JndiTester {
    public static void main(String args[]) throws NamingException {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

        Context context = new InitialContext();

        context.createSubcontext("java:");
        context.createSubcontext("java:comp");
        context.createSubcontext("java:comp/config");

        context.bind("java:comp/config/applicationName", "JndiTester");

        String appName = (String) context.lookup("java:comp/config/applicationName");
        System.out.println("The application name is, " + appName);
    }

    public void LdapDirContext() throws NamingException {
        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");

        DirContext ctx = new InitialDirContext(env);
        System.out.println("connected");
        System.out.println(ctx.getEnvironment());
    }
}
