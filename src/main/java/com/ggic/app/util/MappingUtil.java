package com.ggic.app.util;

import org.modelmapper.ModelMapper;

public class MappingUtil {

    private final static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    public static <T> T map(Object source, Class<T> destination) throws Exception {
        return modelMapper.map(source, destination);
    }
}
