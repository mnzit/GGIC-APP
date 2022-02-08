package com.ggic.app.db.connector;

import com.ggic.app.db.pool.PoolManager;

import java.sql.Connection;

public class MySqlPooledDatabaseConnector extends DatabaseConnector {

    public MySqlPooledDatabaseConnector() {
    }

    @Override
    public void connect() throws Exception {
        Connection connection = PoolManager
                .getConnection();
        System.out.println(connection);
        this.connection = connection;
    }
}
