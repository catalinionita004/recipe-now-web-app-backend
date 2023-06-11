package com.example.recipenowwebappbackend.services;

import com.example.recipenowwebappbackend.dtos.IngredientDto;
import com.example.recipenowwebappbackend.dtos.TagDto;
import com.example.recipenowwebappbackend.exceptions.ResourceNotFoundException;
import com.example.recipenowwebappbackend.mappers.IngredientMapper;
import com.example.recipenowwebappbackend.mappers.TagMapper;
import com.example.recipenowwebappbackend.repositories.IngredientRepository;
import com.example.recipenowwebappbackend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class TagService {

    @Autowired
    TagMapper tagMapper;

    @Autowired
    TagRepository tagRepository;

    public List<TagDto> findTagByName(String name) {
        return tagMapper.modelsToDtos(new HashSet<>(tagRepository.findByNameStartingWith(name, PageRequest.of(0, 10))));
    }

    public TagDto findTagById(Long id){
        return tagMapper.modelToDto(tagRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tag not found with id " + id) ));

    }

}
