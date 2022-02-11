package com.ggic.app.db.config;

public class PostgreSqlDatabaseConfig implements DatabaseConfig{

    @Override
    public String getDriverName() {
        return "org.postgresql.Driver";
    }

    @Override
    public String getUrl() {
        return "jdbc:postgresql://ec2-184-73-243-101.compute-1.amazonaws.com:5432/d7apf3vdfp7i9a";
    }

    @Override
    public String getUsername() {
        return "cabipwoyvryemt";
    }

    @Override
    public String getPassword() {
        return "858a6c10f3ce6505595f8e01d2e0b5d2e0c47ab59473f8daa1294ad3eb9e8a3b";
    }
}
