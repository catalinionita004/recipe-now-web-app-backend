package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.TagDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TagMapper implements BaseMapper<TagDto, Tag> {
    @Override
    public TagDto modelToDto(Tag tag) {
        return new TagDto(
                tag.getId(),
                tag.getName()
        );
    }

    @Override
    public Tag dtoToModel(TagDto tagDto) {
        return null;
    }

    @Override
    public List<TagDto> modelsToDtos(Set<Tag> tags) {
        return tags.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<Tag> dtosToModels(List<TagDto> tagDtos) {
        return null;
    }
}
