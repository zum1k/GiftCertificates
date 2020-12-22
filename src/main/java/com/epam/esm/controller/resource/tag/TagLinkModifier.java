package com.epam.esm.controller.resource.tag;

import com.epam.esm.controller.TagController;
import com.epam.esm.controller.UserController;
import com.epam.esm.controller.resource.DtoLinkModifier;
import com.epam.esm.entity.dto.TagDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagLinkModifier implements DtoLinkModifier<TagDto> {
    private static final TagController controller = WebMvcLinkBuilder.methodOn(TagController.class));
    @Override
    public TagDto withTagLocation(TagDto tagDto) {
        Link self = WebMvcLinkBuilder.linkTo(controller.findTagById(tagDto.getId()))
                .withSelfRel();
        tagDto.add(self);
        return tagDto;
    }

    @Override
    public ResponseEntity<List<TagDto>> allWithPagination(List<TagDto> dtos, Integer page, Integer pageSize) {
        dtos.forEach(this::withTagLocation);
        CollectionModel<TagDto> resources =  new ResponseEntity<List<TagDto>();

        Link self = WebMvcLinkBuilder.linkTo(controller.findAll(page, pageSize))
                .withSelfRel();
        resources.add(self);

        Link firstPage = ControllerLinkBuilder.linkTo(controller.getAllTags(1, pageSize))
                .withRel("first");
        resources.add(firstPage);

        int pageAmount = (int) service.count(pageSize);

        Link lastPage = ControllerLinkBuilder.linkTo(controller.getAllTags(pageAmount, pageSize))
                .withRel("last");
        resources.add(lastPage);

        if (page != 1) {
            Link prevPage = ControllerLinkBuilder.linkTo(controller.getAllTags(page - 1, pageSize))
                    .withRel("prev");
            resources.add(prevPage);
        }

        if (page != pageAmount) {
            Link nextPage = ControllerLinkBuilder.linkTo(controller.getAllTags(page + 1, pageSize))
                    .withRel("next");
            resources.add(nextPage);
        }
        return resources;
    }
}
