package com.epam.ahnl.converter;

import org.modelmapper.ModelMapper;

public abstract class BaseConverter<D,E> implements Converter<D,E>{

    protected ModelMapper modelMapper;

    protected BaseConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

}
