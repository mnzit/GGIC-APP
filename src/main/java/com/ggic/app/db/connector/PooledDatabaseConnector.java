package com.ggic.app.db.connector;

import com.ggic.app.db.config.DatabaseConfig;
import com.ggic.app.db.pool.PoolManager;

import java.sql.Connection;

public class PooledDatabaseConnector extends DatabaseConnector {


    public PooledDatabaseConnector(DatabaseConfig databaseConfig) {
        super(databaseConfig);
    }

    @Override
    public void connect() throws Exception {
        PoolManager
                .getPoolManager(databaseConfig);
        this.connection = PoolManager
                .getConnection();
    }
}
