package com.example.recipenowwebappbackend.mappers;

import com.example.recipenowwebappbackend.dtos.TagDto;
import com.example.recipenowwebappbackend.mappers.base.BaseMapper;
import com.example.recipenowwebappbackend.models.Tag;
import com.example.recipenowwebappbackend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TagMapper implements BaseMapper<TagDto, Tag> {

    @Autowired
    TagRepository tagRepository;

    @Override
    public TagDto modelToDto(Tag tag) {
        return new TagDto(
                tag.getId(),
                tag.getName()
        );
    }

    @Override
    public Tag dtoToModel(TagDto tagDto) {
        if (tagDto.getId() != null) {
            Optional<Tag> optionalTag = tagRepository.findById(tagDto.getId());
            if (optionalTag.isPresent())
                return optionalTag.get();
        }
        return new Tag(tagDto.getName());
    }

    @Override
    public List<TagDto> modelsToDtos(Set<Tag> tags) {
        return tags.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public Set<Tag> dtosToModels(List<TagDto> tagDtos) {
        return tagDtos.stream().map(this::dtoToModel).collect(Collectors.toSet());
    }
}
