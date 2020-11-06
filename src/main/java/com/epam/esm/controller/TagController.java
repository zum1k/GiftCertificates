package com.epam.esm.controller;

import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ComponentScan
@RestController
@RequestMapping(value = "/tags")
public class TagController {
    private final TagService tagService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<TagDto> getTags() {
        log.info("get tags");
        return tagService.findAll();
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTag(@RequestBody TagDto dto) {
        log.info("add tag");
        tagService.add(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TagDto getTag(@PathVariable("id") final long id) {
        log.info("get tag {}", id);
        return tagService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
    public void deleteTagById(@PathVariable("id") final long id) {
        tagService.remove(id);
    }

}
