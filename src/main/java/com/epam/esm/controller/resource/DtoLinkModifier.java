package com.epam.esm.controller.resource;

import com.epam.esm.entity.dto.RequestParametersDto;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public interface DtoLinkModifier<T extends RepresentationModel<T>> {
  T withTagLocation(T t);

  List<T> allWithPagination(List<T> dtos, RequestParametersDto dto);
}
