package com.ggic.app.db.connector;

import com.ggic.app.db.mapper.DataMapper;
import com.ggic.app.db.mapper.ResultMapper;
import com.ggic.app.db.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseConnector {

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected DatabaseConfig databaseConfig;

    public DatabaseConnector() {
    }

    public DatabaseConnector(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public abstract void connect() throws Exception;

    public PreparedStatement initialize(String sql, DataMapper dataMapper) throws Exception {
        preparedStatement = connection.prepareStatement(sql);
        dataMapper.map(preparedStatement);
        return preparedStatement;
    }

    public PreparedStatement initialize(String sql) throws Exception {
        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement;
    }

    public int update() throws Exception {
        return preparedStatement.executeUpdate();
    }

    public ResultSet fetchMultiple() throws Exception {
        return preparedStatement.executeQuery();
    }

    public <T> List<T> fetchMultiple(ResultMapper<T> resultMapper, Class<T> classz) throws Exception {
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            T object = resultMapper.map(resultSet);
            result.add(object);
        }
        return result;
    }

    public <T> T fetchOne(ResultMapper<T> resultMapper, Class<T> classz) throws Exception {
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        T result = resultMapper.map(resultSet);
        if (result != null) {
            return result;
        }
        return null;
    }

    public ResultSet fetch() throws Exception {
        return preparedStatement.executeQuery();
    }

    public void close() throws Exception {
        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
            preparedStatement = null;
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }
}
