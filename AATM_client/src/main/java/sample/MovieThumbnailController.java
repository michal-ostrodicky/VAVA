package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MovieThumbnailController {

    @FXML
    private Label movieTitleLabel;

    @FXML
    private AnchorPane backgroundPane;

    @FXML
    private Label movieRatingLabel;

    @FXML
    private Label movieGenreLabel;


    public void initValues(String title, String genre, Double rating) {
        movieTitleLabel.setText(title);
        movieGenreLabel.setText(genre);
        movieRatingLabel.setText(rating + "");
        backgroundPane.setStyle("-fx-background-color: #e9b649");
    }

}
