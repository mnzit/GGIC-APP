package com.ggic.app.db.pool;

import com.ggic.app.db.config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolManager {
    private final static HikariConfig config = new HikariConfig();
    private static HikariDataSource hikariDataSource;
    private static volatile PoolManager helper;


    private PoolManager() {
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public static PoolManager getPoolManager(DatabaseConfig databaseConfig) {
        PoolManager localRef = helper;
        if (localRef == null) {
            synchronized (PoolManager.class) {
                localRef = helper;
                if (localRef == null) {
                    helper = localRef = new PoolManager();
                    configureDataSource(databaseConfig);
                }
            }
        }
        return localRef;
    }

    private static void configureDataSource(DatabaseConfig databaseConfig) {
        config.setJdbcUrl(databaseConfig.getUrl());
        config.setUsername(databaseConfig.getUsername());
        config.setPassword(databaseConfig.getPassword());
        config.setDriverClassName(databaseConfig.getDriverName());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(config);
    }
}
