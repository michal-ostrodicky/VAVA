package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Hashtable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import lang.Language;

public class Main extends Application {
	
	
	private static Logger LOG = Logger.getLogger(Main.class.getName());


    @Override
    public void start(Stage primaryStage) throws Exception {
    	LOG.setLevel(Level.INFO);
    	
    	Language.getInstance().setLanguage(Locale.forLanguageTag("sk"));
    	
        // Load GUI
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/login.fxml"));
        LOG.info("Loading FXML file");
        AnchorPane root = loader.load();

        LoginController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        // Show first scene
        primaryStage.setTitle("AATM - home");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        


    }

    private static Context createRemoteEjbContext(String host, String port) throws NamingException {
        Hashtable<Object, Object> props = new Hashtable<>();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        props.put("jboss.naming.client.ejb.context", false);
        props.put("org.jboss.ejb.client.scoped.context", true);

        props.put("endpoint.name", "client-endpoint");
        props.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
        props.put("remote.connections", "default");
        props.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);

        props.put(Context.PROVIDER_URL, "http-remoting://" + host + ":" + port);
        props.put("remote.connection.default.host", host);
        props.put("remote.connection.default.port", port);

        return new InitialContext(props);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
