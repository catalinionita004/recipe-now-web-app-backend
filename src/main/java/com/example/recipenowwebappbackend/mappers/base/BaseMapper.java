package com.example.recipenowwebappbackend.mappers.base;

import org.codehaus.plexus.component.annotations.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface BaseMapper<Dto,Model>{

    Dto modelToDto (Model model);
    Model dtoToModel(Dto dto);
    List<Dto> modelsToDtos(Set<Model> models);
    Set<Model> dtosToModels(List<Dto> dtos);
}
