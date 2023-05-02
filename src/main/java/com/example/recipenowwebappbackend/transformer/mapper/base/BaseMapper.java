package com.example.recipenowwebappbackend.transformer.mapper.base;

import com.example.recipenowwebappbackend.dto.base.BaseDto;
import com.example.recipenowwebappbackend.entity.base.BaseEntity;
import org.mapstruct.MappingTarget;


public interface BaseMapper<Entity extends BaseEntity, Dto extends BaseDto> {

    Entity dtoToEntity(Dto dto);

    Dto entityToDto(Entity entity);

    void updateEntity(Dto dto, @MappingTarget Entity entity);
}
