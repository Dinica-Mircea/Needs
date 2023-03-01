package com.example.examendubios.repository.db;

import com.example.examendubios.domain.Nevoie;
import com.example.examendubios.domain.OrasEnum;
import com.example.examendubios.domain.Persoana;
import com.example.examendubios.repository.Repository;
import com.example.examendubios.utils.DateUtils;
import com.example.examendubios.utils.JDBCUtils;

import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class NevoieDbRepository implements Repository<Long, Nevoie> {
    private JDBCUtils jdbcUtils = new JDBCUtils();
    private DateUtils dateUtils = new DateUtils();

    @Override
    public Optional<Nevoie> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Nevoie> findAll() {
        Set<Nevoie> nevoi = new HashSet<>();

        String query = "SELECT * from nevoi";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Long nevoie_iod = resultSet.getLong("nevoie_id");
                String titlu = resultSet.getString("titlu");
                String descriere = resultSet.getString("descriere");
                LocalDateTime deadline = dateUtils.convertToLocalDateTimeViaInstant(resultSet.getDate("deadline"));
                Long omInNevoie = resultSet.getLong("om_in_nevoie");
                Long omSalvator = resultSet.getLong("om_salvator");
                String status = resultSet.getString("status");

                Nevoie nevoie = new Nevoie(nevoie_iod, titlu, descriere, deadline, omInNevoie, omSalvator, status);
                nevoi.add(nevoie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nevoi;
    }

    @Override
    public Optional<Nevoie> save(Nevoie entity) {
        String query = "INSERT INTO nevoi(nevoie_id,titlu,descriere,deadline,om_in_nevoie,om_salvator,status) VALUES(?, ?,?,?,?,?,?)";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getTitlu());
            statement.setString(3, entity.getDescriere());
            statement.setDate(4, dateUtils.convertToSQLDate(entity.getDeadline()));
            statement.setLong(5, entity.getOmInNevoie());
            if (entity.getOmSalvator() == null) {
                statement.setNull(6, Types.BIGINT);
            } else {
                statement.setLong(6, entity.getOmSalvator());
            }
            statement.setString(7, entity.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(entity);
    }

    @Override
    public Optional<Nevoie> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Nevoie> update(Nevoie entity) {
        String query = "UPDATE nevoi SET om_salvator=?, status=? WHERE nevoie_id = ?";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setLong(1, entity.getOmSalvator());
            statement.setString(2, entity.getStatus());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(entity);
    }
}

