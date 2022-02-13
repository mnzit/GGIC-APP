package com.ggic.app.db.config;

import com.ggic.app.model.Student;

public class MySqlDatabaseConfig implements DatabaseConfig{

    @Override
    public String getDriverName() {
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public String getUrl() {
        return "jdbc:mysql://localhost:3306/GGIC?useSSL=false&allowPublicKeyRetrieval=true";
    }

    @Override
    public String getUsername() {
        return "root";
    }

    @Override
    public String getPassword() {
        return "Root@12345";
    }
}
