import model.User;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import util.UserJsonHandler;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private final static int PORT = 8081;

    private final static String CONTEXT_PATH = "/";

    /* Package to scan for classes containing endpoints */
    private final static String API_PACKAGE = "api";

    /* Base path for all servlet/endpoint requests */
    private final static String API_PATH = "/api/*";

    public static void main(final String[] args) throws Exception {
        UserJsonHandler userJsonHandler = new UserJsonHandler();
        final org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(PORT);
        final ServletContextHandler servletContextHandler = new ServletContextHandler(server, CONTEXT_PATH);

        final ResourceConfig config = new ResourceConfig();
        config.packages(API_PACKAGE);

        final ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));
        servletContextHandler.addServlet(servletHolder, API_PATH);

        //String users = userJsonHandler.getUsers();
        userJsonHandler.getUsersWithReversedNames();



        try {
            server.start();
            server.join();
        } finally {
            server.stop();
            server.destroy();
        }
    }

}
