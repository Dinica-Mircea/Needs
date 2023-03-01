package com.example.examendubios;

import com.example.examendubios.domain.Nevoie;
import com.example.examendubios.domain.NevoieCuNume;
import com.example.examendubios.domain.Persoana;
import com.example.examendubios.utils.DateUtils;
import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.util.Arrays;

public class UserController extends Controller {
    public TableView<NevoieCuNume> tabelNevoi;
    public TableView<NevoieCuNume> nevoiFacuteTabel;
    public TextField titluTextField;
    public TextField descriereTextField;
    public DatePicker deadlineDatePicker;
    public Label nevoiOrasLabel;
    public Label nevoiFacuteDePersoanaLabel;
    public Button refreshButton;
    private Persoana user;

    private final DateUtils dateUtils = new DateUtils();

    public void setUser(Persoana user) {
        this.user = user;
    }

    @Override
    public void initializeEverything() {
        initializeTable(tabelNevoi, Arrays.asList("titlu", "descriere", "deadline", "status", "omInNevoie", "omSalvator"));
        tabelNevoi.setItems(FXCollections.observableArrayList(service.getAllNevoiOfOras(user)));
        initializeTable(nevoiFacuteTabel, Arrays.asList("titlu", "descriere", "deadline", "status", "omInNevoie", "omSalvator"));
        nevoiFacuteTabel.setItems(FXCollections.observableList(service.getAllNevoiFacuteDePersoana(user)));
        String nevoiOrasText = "Nevoile din orasul " + user.getOras();
        nevoiOrasLabel.setText(nevoiOrasText);
        String nevoiFacuteDePersoanaText = "Nevoile facute de " + user.getNume() + " " + user.getPrenume();
        nevoiFacuteDePersoanaLabel.setText(nevoiFacuteDePersoanaText);
    }

    public void doNevoie() {
        if (tabelNevoi.getSelectionModel().getSelectedItem().getOmSalvator() == null || tabelNevoi.getSelectionModel().getSelectedItem().getOmSalvator() == null) {
            System.out.println(tabelNevoi.getSelectionModel().getSelectedItem().getStatus());
            service.salveazaOm(user, service.fromNevoieCuNumeToNevoie(tabelNevoi.getSelectionModel().getSelectedItem()));
            tabelNevoi.getItems().clear();
            tabelNevoi.setItems(FXCollections.observableArrayList(service.getAllNevoiOfOras(user)));
            nevoiFacuteTabel.setItems(FXCollections.observableList(service.getAllNevoiFacuteDePersoana(user)));
            Alert message = new Alert(Alert.AlertType.CONFIRMATION);
            message.setHeaderText("CONFIRMARE!");
            message.setContentText("Ai salvat un om astazi!");
            message.showAndWait();
        } else {
            Alert message = new Alert(Alert.AlertType.WARNING);
            message.setHeaderText("WARNING!");
            message.setContentText("Nevoie salvata deja!");
            message.showAndWait();
        }
    }

    public void addNevoie() {
        if (titluTextField.getText() == "" || descriereTextField.getText()=="" || deadlineDatePicker.getValue()==null) {
            Alert message = new Alert(Alert.AlertType.WARNING);
            message.setHeaderText("WARNING!");
            message.setContentText("Nu ati completat toate campurile!");
            message.showAndWait();
        } else {
            Long nevoie_id = service.getMaxNevoieId();
            String titlu = titluTextField.getText();
            String descriere = descriereTextField.getText();
            LocalDateTime deadline = dateUtils.convertToLocalDateTime(deadlineDatePicker.getValue());
            String status = "Caut erou!";
            Long om_salvator = null;
            Nevoie nevoie = new Nevoie(nevoie_id, titlu, descriere, deadline, user.getId(), om_salvator, status);
            service.addNevoie(nevoie);
            tabelNevoi.setItems(FXCollections.observableList(service.getAllNevoiOfOras(user)));
        }
    }

    public void refresh() {
        tabelNevoi.setItems(FXCollections.observableArrayList(service.getAllNevoiOfOras(user)));
        nevoiFacuteTabel.setItems(FXCollections.observableList(service.getAllNevoiFacuteDePersoana(user)));
    }
}
