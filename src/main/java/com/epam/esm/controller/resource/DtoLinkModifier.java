package com.epam.esm.controller.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DtoLinkModifier<T extends RepresentationModel<T>> {
    T withTagLocation(T t);

    ResponseEntity<List<T>> allWithPagination(List<T> dtos, Integer page, Integer pageSize);
}
