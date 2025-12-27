package com.starter.dtxcollector.infrastructure.configuration.mapper;

import com.starter.dtxcollector.domain.model.base.DomainMarker;
import com.starter.dtxcollector.infrastructure.persistence.entity.base.EntityMarker;

public interface GenericMapper<E extends EntityMarker, D extends DomainMarker> {
    D toDomain(E entity);
    E toEntity(D dto);
}
