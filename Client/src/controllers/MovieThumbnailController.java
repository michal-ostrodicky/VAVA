package controllers;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lang.Language;
import model.Movie;
import model.User;

public class MovieThumbnailController {
	
	public static final String TEXTS_PATH = "language/texts";
	public static final String RATING = "rating";
	public static final String DETAIL_VIEW_PATH = "fxml/movie_detail.fxml";
	

    @FXML
    private Label movieTitleLabel;

    @FXML
    private AnchorPane backgroundPane;

    @FXML
    private Label movieRatingLabel;

    @FXML
    private Label movieGenreLabel;
    
    @FXML
    private Label textRatingLabel;
    
    
    private Movie movie;
    
    private Stage primaryStage;
    
    private Scene previousScene;
    
    private User user;
    
    
    
    @FXML
    void initialize(){
    	// Set texts from properties
    	Locale lang = Language.getInstance().getLanguage();
    	ResourceBundle bundle = ResourceBundle.getBundle(TEXTS_PATH, lang);
    	textRatingLabel.setText(bundle.getString(RATING));
    	
    	// Initialize Double click on movie pane and action
    	backgroundPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	    @Override
    	    public void handle(MouseEvent click) {
    	        if (click.getClickCount() == 2) {
    	           showMovieDetail(movie);
    	        }
    	    }
    	});
    }
    
    
    private void showMovieDetail(Movie movie){
    	
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(DETAIL_VIEW_PATH));
        AnchorPane moviePane = null;
        try {
        	moviePane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        MovieDetailController mtc = loader.getController();
        mtc.setMovie(movie);
        mtc.setPrimaryStage(primaryStage);
        mtc.setUser(user);
        mtc.setPrieviousScene(previousScene);
        mtc.initValues();
        
        Scene newScene = new Scene(moviePane);
        primaryStage.setScene(newScene);
        primaryStage.show();
        
    }


    public void initValues() {
        movieTitleLabel.setText(movie.getTitle());
        movieGenreLabel.setText(movie.getGenresAsString());
        movieRatingLabel.setText(movie.getRating().toString());
    }
    
    public void setMovie(Movie movie){
    	this.movie = movie;
    }
    
    public void setPrimaryStage(Stage stage){
    	this.primaryStage = stage;
    }
    
    public void setMainScene(Scene scene){
    	this.previousScene = scene;
    }

    public void setUser(User user){
    	this.user = user;
    	
    }
    
}
