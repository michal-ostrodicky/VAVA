package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import lang.Language;
import model.Movie;
import model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;




public class FavouriteListController {
	
	
	public static final String TEXTS_PATH = "language/texts";
	
	public static final String APP_NAME = "applicationName";
	public static final String FAV_LIST = "favouriteList";
	public static final String BACK = "back";
	
	
	
	@FXML
    private FlowPane containerFlowPane;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Label newFilmsLabel;

    @FXML
    private Button backButton;
    
    
    private User user;
    
    // Switching Scenes
    private Stage primaryStage;
    
    
    
    @FXML
    void initialize(){
    	// Set texts from properties
    	Locale lang = Language.getInstance().getLanguage();
    	ResourceBundle bundle = ResourceBundle.getBundle(TEXTS_PATH, lang);

    	// Intialize text fields
    	appTitleLabel.setText(bundle.getString(APP_NAME));
    	newFilmsLabel.setText(bundle.getString(FAV_LIST));
    	backButton.setText(bundle.getString(BACK));
    }
    
    
    void initValues(){
    	
    	userNameLabel.setText(user.getLogin());
    	
    	List<Movie> favMovies = user.getFavouriteMovies().getMovies();
    	
    	containerFlowPane.getChildren().clear();
    	
    	
    	for(Movie movie : favMovies){
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("fxml/movie_thumbnail.fxml"));
            AnchorPane moviePane = null;
			try {
				moviePane = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            MovieThumbnailController mtc = loader.getController();
            mtc.setMovie(movie);
            mtc.setPrimaryStage(primaryStage);
            mtc.setUser(user);
            mtc.initValues();
            
            containerFlowPane.getChildren().add(moviePane);
    	}
    	
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
        controller.setUser(user);
        controller.setPrimaryStage(primaryStage);
        try {
			controller.initValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Show first scene
        primaryStage.setTitle("AATM - home");
        Scene scene = new Scene(root);
        controller.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    public void setPrimaryStage(Stage stage){
    	this.primaryStage = stage;    	
    }
    
    
    public void setUser(User user){
    	this.user = user;
    	
    }
    
}
