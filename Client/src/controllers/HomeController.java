package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;

import remotes.MovieManagerRemote;
import context.ContextLoader;
import lang.Language;
import model.Movie;
import model.User;


public class HomeController {
	
	public static Logger LOG = Logger.getLogger(HomeController.class.getName());
	
	public static final String TEXTS_PATH = "language/texts";
	
	public static final String APP_NAME = "applicationName";
	public static final String NEW_MOVIES = "newFilms";
	public static final String FAV_LIST = "favouriteList";
	public static final String SCAN_QR = "scanQR";
	
	public static final String MOVIE_MANAGER = "ejb:AATM/Logic/MovieManager!remotes.MovieManagerRemote";
	

    @FXML
    private FlowPane containerFlowPane;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Label newFilmsLabel;

    @FXML
    private Button favouriteListButton;

    @FXML
    private Button scanQRButton;
    
    
 

    // Switching Scenes
    private Stage mainStage;
    
    private User user;
    
    private Scene scene;


    @FXML
    void initialize() {
    	
    	
    	// Set texts from properties
    	Locale lang = Language.getInstance().getLanguage();
    	ResourceBundle bundle = ResourceBundle.getBundle(TEXTS_PATH, lang);

    	// Intialize text fields
    	appTitleLabel.setText(bundle.getString(APP_NAME));
    	newFilmsLabel.setText(bundle.getString(NEW_MOVIES));
    	favouriteListButton.setText(bundle.getString(FAV_LIST));
    	scanQRButton.setText(bundle.getString(SCAN_QR));
    }

    void initValues() throws IOException {
    	
    	userNameLabel.setText(user.getLogin());

    	MovieManagerRemote movieManager = null;
    	try {
    		Context ctx = ContextLoader.createRemoteEjbContext();
			movieManager = (MovieManagerRemote) ctx.lookup(MOVIE_MANAGER);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List<Movie> newMovies = movieManager.getNewMovies();


        for (Movie movie : newMovies) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("fxml/movie_thumbnail.fxml"));
            AnchorPane moviePane = loader.load();
            MovieThumbnailController mtc = loader.getController();
            mtc.setMovie(movie);
            mtc.setPrimaryStage(mainStage);
            mtc.setMainScene(scene);
            mtc.setUser(user);
            mtc.initValues();
            
            containerFlowPane.getChildren().add(moviePane);
        }
    }


    @FXML
    void showFavouriteList(ActionEvent event) {
    	
    }

    @FXML
    void scanQRcode(ActionEvent event) {
    	
    }
    
    @FXML
    void setEnglish(){
    	
    }
    
    @FXML
    void setSlovak(){
    	
    }
    
    
    
    
    
    
    
    
    
    public void setPrimaryStage(Stage stage){
    	this.mainStage = stage;    	
    }
    
    public void setScene(Scene scene){
    	this.scene = scene;
    	
    }
    
    public void setUser(User user){
    	this.user = user;
    	
    }

}
