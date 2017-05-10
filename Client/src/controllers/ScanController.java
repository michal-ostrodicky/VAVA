package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import lang.Language;
import model.Movie;
import model.User;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScanController {
	
	
	public static final String TEXTS_PATH = "language/texts";
	
	public static final String DETAIL_VIEW_PATH = "fxml/movie_detail.fxml";
	public static final String APP_NAME = "applicationName";
	public static final String BACK = "back";
	public static final String SCAN = "scanQR";
	public static final String SCAN_INFO = "infoAboutScan";
	public static final String SCAN_ERROR = "errorAboutScan";

    @FXML
    private Label infoLabel;

    @FXML
    private JFXButton scanButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Label errorLabel;
    
    
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
    	backButton.setText(bundle.getString(BACK));
    	scanButton.setText(bundle.getString(SCAN));
    	infoLabel.setText(bundle.getString(SCAN_INFO));
    	errorLabel.setText(null);
    	
    }
    
    
    @FXML
    void getPreviousScene() {
    	   	
    }

    @FXML
    void scan() throws IOException {
    	Webcam webcam = Webcam.getDefault();
    	
    	
    	
    	webcam.open();
    	
    	BufferedImage image = webcam.getImage();
    	
    	ImageIO.write(image, "PNG", new File("etc/test.png"));

    	
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
        mtc.initValues();
        
        Scene newScene = new Scene(moviePane);
        primaryStage.setScene(newScene);
        primaryStage.show();
        
    }
    
    public void setPrimaryStage(Stage stage){
    	this.primaryStage = stage;    	
    }
    
    
    public void setUser(User user){
    	this.user = user;
    	
    }

}
