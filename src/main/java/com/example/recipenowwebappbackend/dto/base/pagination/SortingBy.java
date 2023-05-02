package com.example.recipenowwebappbackend.dto.base.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortingBy {
    private String fieldName;
    private SortingDirection direction = SortingDirection.ASC;
    private Boolean isNumber = false;

    public SortingBy(String fieldName, SortingDirection direction) {
        this.direction = direction;
        this.fieldName = fieldName;
    }
}
