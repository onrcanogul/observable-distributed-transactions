package com.starter.dtxcollector.infrastructure.configuration.mapper;

import com.starter.dtxcollector.domain.model.base.DomainMarker;
import com.starter.dtxcollector.infrastructure.persistence.entity.base.EntityMarker;
import org.modelmapper.ModelMapper;

public class Mapper<E extends EntityMarker, D extends DomainMarker> implements GenericMapper<E, D> {
    private final ModelMapper modelMapper = new ModelMapper();
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    public Mapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public D toDomain(E entity) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public E toEntity(D dto) {
        return modelMapper.map(dto, entityClass);
    }
}
