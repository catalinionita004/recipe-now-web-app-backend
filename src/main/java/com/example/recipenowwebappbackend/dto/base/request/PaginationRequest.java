package com.example.recipenowwebappbackend.dto.base.request;

import com.example.recipenowwebappbackend.dto.base.pagination.Page;
import com.example.recipenowwebappbackend.dto.base.pagination.SortingBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationRequest extends Page {
    private List<SortingBy> sortingByList;
}
