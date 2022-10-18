package uk.me.eastmans;
import java.io.File;
import java.net.URL;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        System.out.println("Setting the port to " + webPort);
        tomcat.setPort(Integer.valueOf(webPort));

        String contextPath = "/CrudeWeb";
        String appBase = ".";
        tomcat.setHostname("localhost");
        tomcat.getHost().setAppBase(appBase);
        Context c = tomcat.addWebapp(contextPath, appBase);

        // Add the jar folder for the scanning
        final WebResourceRoot root = new StandardRoot(c);
        final URL url = findClassLocation(Main.class);
        root.createWebResourceSet(WebResourceRoot.ResourceSetType.PRE,"/WEB-INF/classes", url, "/" );
        c.setResources(root);

        // Create the connection
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static URL findClassLocation(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation();
    }
}