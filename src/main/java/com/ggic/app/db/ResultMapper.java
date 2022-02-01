package com.ggic.app.db;

import java.sql.ResultSet;

public interface ResultMapper<T> {

    T map(ResultSet resultSet) throws Exception;
}
