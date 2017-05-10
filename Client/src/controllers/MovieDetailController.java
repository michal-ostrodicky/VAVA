package controllers;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import remotes.MovieManagerRemote;
import remotes.UserManagerRemote;
import lang.Language;
import model.Movie;
import model.User;

import com.jfoenix.controls.JFXButton;

import context.ContextLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MovieDetailController {
	
	public static final String TEXTS_PATH = "language/texts";
	
    public static final String MOVIE_DETAIL = "movieDetail";
    public static final String GENRES = "genres";
    public static final String YEAR = "year";
    public static final String PREMIERE = "premiere";
    public static final String DESCRIPTION = "description";
    public static final String MINUTES_LONG = "movieLength";
    public static final String RATING = "rating";
    public static final String BACK = "back";
    public static final String ADD_TO_FAVOURITE = "addToFavourite";
    
    
    public static final String USER_MANAGER = "ejb:AATM/Logic/UserManager!remotes.UserManagerRemote";
	
	
	
    @FXML
    private Label ratingLabel;

    @FXML
    private Label movieDetailLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private JFXButton addToFavouriteButton;

    @FXML
    private Label minutesLabel;
    
    @FXML
    private Label movieMinutesLabel;

    @FXML
    private Label premiereLabel;

    @FXML
    private Label movieRatingLabel;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Label movieYearLabel;

    @FXML
    private Label movieGenreLabel;

    @FXML
    private Label movieTitleLabel;

    @FXML
    private Label movieDescriptionLabel;

    @FXML
    private JFXButton backButton;

    @FXML
    private Label genreLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label moviePremiereLabel;

    @FXML
    private Label descriptionLabel;
    
    
    private Movie movie;

    private Stage primaryStage;
    
    private Scene previousScene;
    
    private User user;
    
    

    
    @FXML
    void initialize(){
    	
    	// Set texts from properties
    	Locale lang = Language.getInstance().getLanguage();
    	ResourceBundle bundle = ResourceBundle.getBundle(TEXTS_PATH, lang);
    	
    	movieDetailLabel.setText(bundle.getString(MOVIE_DETAIL));
    	genreLabel.setText(bundle.getString(GENRES));
    	yearLabel.setText(bundle.getString(YEAR));
    	premiereLabel.setText(bundle.getString(PREMIERE));
    	descriptionLabel.setText(bundle.getString(DESCRIPTION));
    	minutesLabel.setText(bundle.getString(MINUTES_LONG));
    	ratingLabel.setText(bundle.getString(RATING));	
    	backButton.setText(bundle.getString(BACK));
    	addToFavouriteButton.setText(bundle.getString(ADD_TO_FAVOURITE));
    	
    	
    }
   
    
    public void initValues(){
    	
    	userNameLabel.setText(user.getLogin());
    	    	
    	movieDescriptionLabel.setText(movie.getDescription());
    	movieGenreLabel.setText(movie.getGenresAsString());
    	moviePremiereLabel.setText(movie.getPremiere().toString());
    	movieTitleLabel.setText(movie.getTitle());
    	movieRatingLabel.setText(movie.getRating().toString());
    	movieYearLabel.setText(movie.getYear().toString());
    	movieMinutesLabel.setText(movie.getMinutes().toString());    	
    }
    

    @FXML
    void addMovieToFavourites() {
    	
    	UserManagerRemote userManager = null;
    	try {
    		Context ctx = ContextLoader.createRemoteEjbContext();
    		userManager = (UserManagerRemote) ctx.lookup(USER_MANAGER);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	userManager.addFavouriteMovieToList(movie.getId(), user.getId());
    	

    }



    @FXML
    void getPreviousScene() {
    	// Load GUI
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/home.fxml"));
        AnchorPane root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        HomeController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setUser(user);
        try {
			controller.initValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Show first scene
        Scene scene = new Scene(root);
        controller.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setPrimaryStage(Stage stage){
    	this.primaryStage = stage;
    }
    
    public void setMovie(Movie movie){
    	this.movie = movie;
    }
    
    public void setPrieviousScene(Scene scene){
    	this.previousScene = scene;
    }
    
    public void setUser(User user){
    	this.user = user;
    	
    }

}
