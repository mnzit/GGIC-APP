package com.ggic.app.db.mapper;

import java.sql.PreparedStatement;

public interface DataMapper {

    void map(PreparedStatement preparedStatement) throws Exception;
}
