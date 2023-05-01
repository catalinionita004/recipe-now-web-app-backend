package com.example.recipenowwebappbackend.dto.base.pagination;

import com.henry.bookrecommendationsystem.dto.base.request.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterPaginationRequest<Filtration> extends PaginationRequest {
    private Filtration criteria;
    private Boolean deletedRecords;
}
