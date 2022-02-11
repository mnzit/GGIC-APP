package com.ggic.app.db.config;

public class PostgreSqlDatabaseConfig implements DatabaseConfig {

    @Override
    public String getDriverName() {
        return "org.postgresql.Driver";
    }

    @Override
    public String getUrl() {
        return String.format("jdbc:postgresql://%s:%s/%s", System.getenv("HOST"), System.getenv("PORT"), System.getenv("DATABASE"));
    }

    @Override
    public String getUsername() {
        return System.getenv("USERNAME");
    }

    @Override
    public String getPassword() {
        return System.getenv("PASSWORD");
    }
}
