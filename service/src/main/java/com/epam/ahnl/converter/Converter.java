package com.epam.ahnl.converter;

public interface Converter<D, E> {

    E toEntity(D dto);

    D toDTO(E entity);

}
