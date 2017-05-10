package controllers;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import remotes.MovieManagerRemote;
import remotes.UserManagerRemote;

import com.jfoenix.controls.JFXButton;

import context.ContextLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lang.Language;
import model.User;

public class LoginController {
	
	
	public static final String TEXTS_PATH = "language/texts";
	
	public static final String APP_NAME = "applicationName";
	public static final String LOGIN = "login";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String WRONG_CREDENTIALS = "wrongCredentials";
	
	public static final String USER_MANAGER = "ejb:AATM/Logic/UserManager!remotes.UserManagerRemote";
	
	
	
    @FXML
    private Label infoLabel;

    @FXML
    private JFXButton loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginField;

    @FXML
    private Label passwordLabel;
    
    private Stage primaryStage;
    
    
    
    @FXML
    void intialize(){
    	// Set texts from properties
    	Locale lang = Language.getInstance().getLanguage();
    	ResourceBundle bundle = ResourceBundle.getBundle(TEXTS_PATH, lang);

    	// Intialize text fields
    	appTitleLabel.setText(bundle.getString(APP_NAME));
    	loginLabel.setText(bundle.getString(USERNAME));
    	loginButton.setText(bundle.getString(LOGIN));
    	passwordLabel.setText(bundle.getString(PASSWORD));
    	infoLabel.setText(null);
    }


    @FXML
    void login() {
    	UserManagerRemote userManager = null;
    	try {
    		Context ctx = ContextLoader.createRemoteEjbContext();
    		userManager = (UserManagerRemote) ctx.lookup(USER_MANAGER);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int id = userManager.login(loginField.getText(), passwordField.getText());
    	
    	if(id >= 0){
    		User user = userManager.getUser(id);
    		
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
    		
    		
    	} else {
    		    		
    		// Set texts from properties
        	Locale lang = Language.getInstance().getLanguage();
        	ResourceBundle bundle = ResourceBundle.getBundle(TEXTS_PATH, lang);
    		infoLabel.setText(bundle.getString(WRONG_CREDENTIALS));
    		
    	}	

    }
    
    
    public void setPrimaryStage(Stage stage){
    	primaryStage = stage;
    }

}
