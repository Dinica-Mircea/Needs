package com.example.examendubios;

import com.example.examendubios.domain.Nevoie;
import com.example.examendubios.domain.OrasEnum;
import com.example.examendubios.domain.Persoana;
import com.example.examendubios.repository.Repository;
import com.example.examendubios.repository.db.NevoieDbRepository;
import com.example.examendubios.repository.db.PersoanaDbRepository;
import com.example.examendubios.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //test();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        Repository<Long, Persoana> repoPersoane=new PersoanaDbRepository();
        Repository<Long, Nevoie> repoNevoi=new NevoieDbRepository();
        LoginController loginController=fxmlLoader.getController();
        loginController.setService(new Service(repoPersoane,repoNevoi));
        loginController.initializeEverything();
        stage.show();
    }

    public void test(){
        Persoana persoana=new Persoana((long)1,"n1","p1","u1","p1",
                OrasEnum.valueOf("Galati"),"st1","nr1","tlf1");
        Repository<Long, Persoana> repoPersoane=new PersoanaDbRepository();
        repoPersoane.save(persoana);
    }

    public static void main(String[] args) {
        launch();
    }
}