import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Movie;
import model.User;

import com.github.javafaker.Faker;
import com.sun.org.apache.xpath.internal.operations.Mod;

import exceptions.NotReadableImageException;
import remotes.MovieManagerRemote;
import remotes.UserManagerRemote;


public class Test {

	public static void main(String[] args) throws NamingException {
	
		Context ctx = createRemoteEjbContext("localhost", "10100");
		
		  
		
		
		MovieManagerRemote movieManager = (MovieManagerRemote) ctx.lookup("ejb:AATM/Logic/MovieManager!remotes.MovieManagerRemote");
	
//		UserManagerRemote userManager = (UserManagerRemote) ctx.lookup("ejb:AATM/Logic/UserManager!remotes.UserManagerRemote");
		
	
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("resources/qr38.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", baos);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] imageBytes = baos.toByteArray();
		
		
		try {
			Movie movie = movieManager.getMovie(imageBytes);
			System.out.println(movie.getTitle());
			
		} catch (NotReadableImageException e) {
			System.out.println("Nepodarilo sa precitat obrazok !!");
		}
		
		
		
			
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
	

}
