package com.example.examendubios;


import com.example.examendubios.service.Service;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public abstract class Controller {
    protected Service service;

    protected static <E> void initializeTable(TableView<E> tabel, List<String> columnNames) {
        for (String columnName : columnNames) {
            TableColumn<E, String> column = new TableColumn<>(columnName);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tabel.getColumns().add(column);
        }
    }

    public void setService(Service service) {
        this.service = service;
    }

    public abstract void initializeEverything();


}

