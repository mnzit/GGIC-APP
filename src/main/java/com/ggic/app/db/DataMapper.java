package com.ggic.app.db;

import java.sql.PreparedStatement;

public interface DataMapper {

    void map(PreparedStatement preparedStatement) throws Exception;
}
