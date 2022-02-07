package com.ggic.app.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplate<T> {

    private final DatabaseHelper databaseHelper = new DatabaseHelper();

    public List<T> getAll(String sql, ResultMapper<T> resultMapper) {
        try {
            databaseHelper.connect();
            databaseHelper.initialize(sql);
            ResultSet resultSet = databaseHelper.fetch();
            List<T> rows = new ArrayList<>();
            while (resultSet.next()) {
                rows.add(resultMapper.map(resultSet));
            }
            return rows;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                databaseHelper.close();
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
        return null;
    }
    public Optional<List<T>> getAllOptional(String sql, ResultMapper<T> resultMapper) {
        return Optional.ofNullable(getAll(sql,resultMapper));
    }

    public T getOneByObject(String sql, ResultMapper<T> resultMapper, Object[] parameters) {
        try {
            databaseHelper.connect();
            PreparedStatement preparedStatement = databaseHelper.initialize(sql);
            addParameter(preparedStatement, parameters);
            ResultSet resultSet = databaseHelper.fetch();
            while (resultSet.next()) {
                return resultMapper.map(resultSet);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                databaseHelper.close();
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
        return null;
    }

    public Optional<T> getOptionalOneByObject(String sql, ResultMapper<T> resultMapper, Object[] parameters) {
        return Optional.ofNullable(getOneByObject(sql, resultMapper,parameters));
    }

    public int update(String sql, Object[] parameters) {
        try {
            databaseHelper.connect();
            PreparedStatement preparedStatement = databaseHelper.initialize(sql);
            addParameter(preparedStatement, parameters);
            return databaseHelper.update();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            try {
                databaseHelper.close();
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
        return -1;
    }

    public void addParameter(PreparedStatement preparedStatement, Object[] parameters) throws Exception {
        int index = 1;
        for (Object parameter : parameters) {
            preparedStatement.setObject(index++, parameter);
        }
    }
}
