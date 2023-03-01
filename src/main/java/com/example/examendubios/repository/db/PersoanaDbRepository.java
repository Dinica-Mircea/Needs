package com.example.examendubios.repository.db;

import com.example.examendubios.domain.OrasEnum;
import com.example.examendubios.domain.Persoana;
import com.example.examendubios.repository.Repository;
import com.example.examendubios.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PersoanaDbRepository implements Repository<Long, Persoana> {
    private JDBCUtils jdbcUtils=new JDBCUtils();

    @Override
    public Optional<Persoana> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Persoana> findAll() {
        Set<Persoana> persoane = new HashSet<>();

        String query = "SELECT * from persoane";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Long persoana_id = resultSet.getLong("persoana_id");
                String nume=resultSet.getString("nume");
                String prenume=resultSet.getString("prenume");
                String username=resultSet.getString("username");
                String parola=resultSet.getString("parola");
                String oras=resultSet.getString("oras");
                String strada=resultSet.getString("strada");
                String numar_strada=resultSet.getString("numar_strada");
                String telefon=resultSet.getString("telefon");
                Persoana persoana=new Persoana(persoana_id,nume,prenume,username,parola,
                        OrasEnum.valueOf(oras),strada,numar_strada,telefon);

                persoane.add(persoana);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persoane;
    }

    @Override
    public Optional<Persoana> save(Persoana entity) {
        String query = "INSERT INTO persoane" +
                "(persoana_id, nume,prenume,username,parola,oras,strada,numar_strada,telefon) " +
                "VALUES(?, ?,?,?,?,?,?,?,?)";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getNume());
            statement.setString(3, entity.getPrenume());
            statement.setString(4, entity.getUsername());
            statement.setString(5, entity.getParola());
            statement.setString(6, entity.getOras().toString());
            statement.setString(7, entity.getStrada());
            statement.setString(8, entity.getNumarStrada());
            statement.setString(9, entity.getTelefon());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(entity);
    }

    @Override
    public Optional<Persoana> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Persoana> update(Persoana entity) {
        return Optional.empty();
    }
}
