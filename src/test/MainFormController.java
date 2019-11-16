package test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


public class MainFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private Button btn1;

    @FXML
    private TextArea txtArea1;


    @FXML
    void btn1Action(ActionEvent event) {
        txtArea1.insertText(0,"a");
    }

    @FXML
    void initialize() {
        assert anchor1 != null : "fx:id=\"anchor1\" was not injected: check your FXML file 'mainForm.fxml'.";
        assert btn1 != null : "fx:id=\"btn1\" was not injected: check your FXML file 'mainForm.fxml'.";
        assert txtArea1 != null : "fx:id=\"txtArea1\" was not injected: check your FXML file 'mainForm.fxml'.";


    }

}
