package com.epam.esm.controller;

import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/tags")
public class TagController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<TagDto> findAll() {
        log.info("get tags");
        return tagService.findAll();
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto addTag(@RequestBody TagDto dto) {
        log.info("add tag");
        return tagService.addTagIfNotExist(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TagDto findTagById(@PathVariable("id") final long id) {
        log.info("get tag {}", id);
        return tagService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TagDto deleteTagById(@PathVariable("id") final long id) {
        log.info("get tag {}", id);
        return tagService.remove(id);
    }
}
