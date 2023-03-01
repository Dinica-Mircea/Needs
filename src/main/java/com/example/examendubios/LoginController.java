package com.example.examendubios;

import com.example.examendubios.domain.OrasEnum;
import com.example.examendubios.domain.Persoana;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginController extends Controller {
    @FXML
    public ComboBox<String> usernameComboBox;
    @FXML
    private TextField numarStradaTextField;
    @FXML
    private TextField telefonTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField prenumeTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField stradaTextField;
    @FXML
    private TextField numeTextField;

    @FXML
    private ComboBox<String> orasComboBox;

    public void registerNewClient() throws IOException {
        if (numeTextField.getText()=="" ||
                usernameTextField.getText() == "" ||
                passwordTextField.getText()=="" ||
                prenumeTextField.getText()=="" ||
                stradaTextField.getText()=="" ||
                numarStradaTextField.getText()==""||
                telefonTextField.getText()==""||
                orasComboBox.getValue()==null) {
            Alert message = new Alert(Alert.AlertType.WARNING);
            message.setHeaderText("WARNING!");
            message.setContentText("Nu ati completat toate campurile!");
            message.showAndWait();
        } else {
            String nume = numeTextField.getText();
            String strada = stradaTextField.getText();
            String username = usernameTextField.getText();
            String prenume = prenumeTextField.getText();
            String password = passwordTextField.getText();
            String telefon = telefonTextField.getText();
            String numarStrada = numarStradaTextField.getText();
            OrasEnum oras = OrasEnum.valueOf(orasComboBox.getValue());
            Long persoana_id = service.getMaxPersoanaId();
            Persoana persoana = new Persoana(persoana_id, nume, prenume, username, password,
                    oras, strada, numarStrada, telefon);
            service.savePersoana(persoana);
            usernameComboBox.setItems(FXCollections.observableArrayList(service.getAllUsernames()));
            //login();
        }

    }

    @Override
    public void initializeEverything() {
        List<String> orase = Arrays.asList("Cluj", "Galati", "Bucuresti");
        orasComboBox.setItems(FXCollections.observableList(orase));
        usernameComboBox.setItems(FXCollections.observableArrayList(service.getAllUsernames()));
    }

    public void login() throws IOException {
        if (usernameComboBox.getValue() != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            Stage stage = new Stage();
            stage.setTitle(service.getUser(usernameComboBox.getValue()).getNume());
            stage.setScene(scene);
            stage.setHeight(400);
            stage.setWidth(600);
            UserController userController = fxmlLoader.getController();
            stage.show();
            userController.setService(service);
            userController.setUser(service.getUser(usernameComboBox.getValue()));
            userController.initializeEverything();
            usernameComboBox.getSelectionModel().clearSelection();
        }
    }


}