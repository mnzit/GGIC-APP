package com.ggic.app.db.config;

public class PostgreSqlDatabaseConfig implements DatabaseConfig{

    @Override
    public String getDriverName() {
        return "org.postgresql.Driver";
    }

    @Override
    public String getUrl() {
        return "jdbc:postgresql://localhost:5432/ggic";
    }

    @Override
    public String getUsername() {
        return "root";
    }

    @Override
    public String getPassword() {
        return "root";
    }
}
