package com.ggic.app.db.connector;

import com.ggic.app.db.config.DatabaseConfig;

import java.sql.DriverManager;

public class SingleDatabaseConnector extends DatabaseConnector {

    public SingleDatabaseConnector(DatabaseConfig databaseConfig) {
        super(databaseConfig);
        this.databaseConfig = databaseConfig;
    }

    @Override
    public void connect() throws Exception {
        // Object Pooling - > Connection Object Pooling
        Class.forName(databaseConfig.getDriverName());
        connection = DriverManager.getConnection(
                databaseConfig.getUrl(),
                databaseConfig.getUsername(),
                databaseConfig.getPassword()
        );
        System.out.println(connection);
    }
}
