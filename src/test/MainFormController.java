package test;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


public class MainFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor1;

    @FXML
    public TextArea resultField;

    @FXML
    public TextArea queryField;

    @FXML
    private Button btn1;

    @FXML
    void btn1Action(ActionEvent event) {
        String sql = "SELECT * FROM \"user\"";

        DBConnector connector = new DBConnector("jdbc:sqlite:identifier.sqlite");

        resultField.appendText(connector.query(queryField.getText()));
        resultField.appendText("----End Of Query----\n");
    }

    @FXML
    void initialize() {
        assert anchor1 != null : "fx:id=\"anchor1\" was not injected: check your FXML file 'mainForm.fxml'.";
        assert btn1 != null : "fx:id=\"btn1\" was not injected: check your FXML file 'mainForm.fxml'.";
        assert queryField != null : "fx:id=\"txtArea1\" was not injected: check your FXML file 'mainForm.fxml'.";
        assert resultField != null : "fx:id=\"txtArea1\" was not injected: check your FXML file 'mainForm.fxml'.";
    }
}
