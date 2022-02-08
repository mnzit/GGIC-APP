package com.ggic.app.db.pool;

import com.ggic.app.db.config.DatabaseConfig;
import com.ggic.app.db.config.MySqlDatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolManager {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static DatabaseConfig databaseConfig = new MySqlDatabaseConfig();
    static {
        config.setJdbcUrl(databaseConfig.getUrl());
        config.setUsername(databaseConfig.getUsername());
        config.setPassword(databaseConfig.getPassword());
        config.setDriverClassName(databaseConfig.getDriverName());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private PoolManager() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
