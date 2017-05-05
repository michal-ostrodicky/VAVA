package sample;

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


public class Controller {

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
    private Scene previousScene;


    @FXML
    void initialize() throws IOException {


    }

    void displayMovies() throws IOException {


        List<String> titles = new ArrayList<>();
        titles.add("Batman Begins");
        titles.add("Batman Dark Knight Rises Very Long");
        titles.add("Batman Dark Knight");
        titles.add("Hello Dolly !");
        titles.add("Za plotom");
        titles.add("Bear Brothers");
        titles.add("Kong : Skull Island");
        titles.add("I Tried To Kill Myself With Apple In My Hand");
        titles.add("Batman Begins");
        titles.add("Batman Dark Knight Rises Very Long");
        titles.add("Batman Dark Knight");
        titles.add("Hello Dolly !");
        titles.add("Za plotom");
        titles.add("Bear Brothers");
        titles.add("Kong : Skull Island");
        titles.add("I Tried To Kill Myself With Apple In My Hand");
        titles.add("Batman Begins");
        titles.add("Batman Dark Knight Rises Very Long");
        titles.add("Batman Dark Knight");
        titles.add("Hello Dolly !");
        titles.add("Za plotom");
        titles.add("Bear Brothers");
        titles.add("Kong : Skull Island");
        titles.add("I Tried To Kill Myself With Apple In My Hand");
        titles.add("Batman Begins");
        titles.add("Batman Dark Knight Rises Very Long");
        titles.add("Batman Dark Knight");
        titles.add("Hello Dolly !");
        titles.add("Za plotom");
        titles.add("Bear Brothers");
        titles.add("Kong : Skull Island");
        titles.add("I Tried To Kill Myself With Apple In My Hand");


        for (String s : titles) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("movie_thumbnail.fxml"));
            AnchorPane movie = loader.load();
            MovieThumbnailController mtc = loader.getController();
            mtc.initValues(s, "Comedy, Drama", 7.6);
            containerFlowPane.getChildren().add(movie);
        }
    }


    @FXML
    void showFavouriteList(ActionEvent event) {

    }

    @FXML
    void scanQRcode(ActionEvent event) {

    }

}
